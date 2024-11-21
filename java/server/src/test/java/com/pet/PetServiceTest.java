package com.pet;

import static org.junit.jupiter.api.Assertions.*;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.*;
import com.pet.proto.PetOuterClass;
import com.pet.proto.PetOuterClass.RegisterPetRequest;
import com.pet.proto.PetOuterClass.SearchPetsRequest;
import com.pet.proto.PetOuterClass.SearchPetsResponse;
import com.pet.proto.PetServiceGrpc;
import java.util.concurrent.TimeUnit;

public class PetServiceTest {

    private ManagedChannel channel;
    private PetServiceGrpc.PetServiceBlockingStub stub;

    @BeforeEach
    void setUp() throws Exception {
        // Create an in-process gRPC server
        String serverName = InProcessServerBuilder.generateName();
        InProcessServerBuilder.forName(serverName)
                .directExecutor() // Execute tasks on the calling thread
                .addService(new PetServiceImpl())
                .build()
                .start();

        // Create a channel for communication
        channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        stub = PetServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    void tearDown() throws Exception {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

   
    @Test
    void testSearchPetsByName() {
        // Step 1: Register a pet named "Buddy"
        PetOuterClass.Pet buddy = PetOuterClass.Pet.newBuilder()
                .setId("1")
                .setName("Buddy")
                .setGender("Male")
                .setAge(3)
                .setBreed("Labrador")
                .build();

        PetOuterClass.RegisterPetRequest registerRequest = PetOuterClass.RegisterPetRequest.newBuilder()
                .setPet(buddy)
                .build();

        stub.registerPet(registerRequest);  // Ensure the pet is registered

        // Step 2: Search for the pet by name
        SearchPetsRequest searchRequest = SearchPetsRequest.newBuilder().setName("Buddy").build();
        SearchPetsResponse response = stub.searchPets(searchRequest);

        // Step 3: Validate the search results
        assertFalse(response.getPetsList().isEmpty(), "Expected to find at least one pet.");
        assertEquals("Buddy", response.getPets(0).getName());
    }

    @Test
    void testRegisterPetWithDuplicateId() {
        PetOuterClass.Pet pet = PetOuterClass.Pet.newBuilder()
                .setId("1")
                .setName("Buddy")
                .setGender("Male")
                .setAge(3)
                .setBreed("Labrador")
                .build();

        // Register the first pet
        RegisterPetRequest request = RegisterPetRequest.newBuilder().setPet(pet).build();
        stub.registerPet(request);

        // Try to register the same pet again
        StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> {
            stub.registerPet(request);
        });

        assertEquals("ALREADY_EXISTS: Pet with this ID already exists.", exception.getMessage());
    }


    @Test
    void testSearchPetsByGender() {
        // Register a pet with specific gender
        PetOuterClass.Pet bella = PetOuterClass.Pet.newBuilder()
                .setId("2")
                .setName("Bella")
                .setGender("Female")
                .setAge(2)
                .setBreed("Poodle")
                .build();

        stub.registerPet(RegisterPetRequest.newBuilder().setPet(bella).build());

        // Search for pets by gender "Female"
        SearchPetsRequest searchRequest = SearchPetsRequest.newBuilder().setGender("Female").build();
        SearchPetsResponse response = stub.searchPets(searchRequest);

        assertFalse(response.getPetsList().isEmpty(), "Expected to find at least one pet.");
        assertEquals("Bella", response.getPets(0).getName());
        assertEquals("Female", response.getPets(0).getGender());
    }

    @Test
    void testSearchForNonExistentPet() {
        // Search for a pet with a name that doesn't exist
        SearchPetsRequest searchRequest = SearchPetsRequest.newBuilder().setName("Unknown").build();
        SearchPetsResponse response = stub.searchPets(searchRequest);

        assertTrue(response.getPetsList().isEmpty(), "Expected no pets to be found.");
        assertEquals("No pets found matching the criteria.", response.getMessage());
    }


    @Test
    void testRegisterMultiplePetsAndRetrieveAll() {
        // Register multiple pets
        PetOuterClass.Pet max = PetOuterClass.Pet.newBuilder()
                .setId("3")
                .setName("Max")
                .setGender("Male")
                .setAge(4)
                .setBreed("German Shepherd")
                .build();

        PetOuterClass.Pet luna = PetOuterClass.Pet.newBuilder()
                .setId("4")
                .setName("Luna")
                .setGender("Female")
                .setAge(1)
                .setBreed("Golden Retriever")
                .build();

        stub.registerPet(RegisterPetRequest.newBuilder().setPet(max).build());
        stub.registerPet(RegisterPetRequest.newBuilder().setPet(luna).build());

        // Search without filters to retrieve all pets
        SearchPetsRequest searchRequest = SearchPetsRequest.newBuilder().build();
        SearchPetsResponse response = stub.searchPets(searchRequest);

        assertEquals(2, response.getPetsCount(), "Expected two pets to be found.");
        assertEquals("Max", response.getPets(0).getName());
        assertEquals("Luna", response.getPets(1).getName());
    }

}
