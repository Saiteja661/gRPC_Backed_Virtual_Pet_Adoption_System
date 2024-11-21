package com.pet;

import com.pet.proto.PetOuterClass;
import com.pet.proto.PetServiceGrpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetServiceImpl extends PetServiceGrpc.PetServiceImplBase {

    // In-memory storage for pets
    private final Map<String, PetOuterClass.Pet> petsStorage = new HashMap<>();

    @Override
    public void registerPet(PetOuterClass.RegisterPetRequest request, 
                            StreamObserver<PetOuterClass.RegisterPetResponse> responseObserver) {
        PetOuterClass.Pet pet = request.getPet();
    
        // Check if the pet with the same ID already exists
        if (petsStorage.containsKey(pet.getId())) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Pet with this ID already exists.")
                    .asRuntimeException());
            return;
        }
    
        // Store the pet
        petsStorage.put(pet.getId(), pet);
        PetOuterClass.RegisterPetResponse response = PetOuterClass.RegisterPetResponse.newBuilder()
                .setMessage("Pet registered successfully.")
                .setPet(pet)
                .build();
    
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void searchPets(PetOuterClass.SearchPetsRequest request, StreamObserver<PetOuterClass.SearchPetsResponse> responseObserver) {
        List<PetOuterClass.Pet> matchingPets = new ArrayList<>();

        // Filter pets based on request criteria
        for (PetOuterClass.Pet pet : petsStorage.values()) {
            boolean matches = true;

            if (!request.getName().isEmpty() && !pet.getName().toLowerCase().contains(request.getName().toLowerCase())) {
                matches = false;
            }
            if (!request.getGender().isEmpty() && !pet.getGender().equalsIgnoreCase(request.getGender())) {
                matches = false;
            }
            if (request.getAge() != 0 && pet.getAge() != request.getAge()) {
                matches = false;
            }
            if (!request.getBreed().isEmpty() && !pet.getBreed().equalsIgnoreCase(request.getBreed())) {
                matches = false;
            }

            if (matches) {
                matchingPets.add(pet);
            }
        }

        // Create the response
        PetOuterClass.SearchPetsResponse response = PetOuterClass.SearchPetsResponse.newBuilder()
                .addAllPets(matchingPets)
                .setMessage(matchingPets.isEmpty() ? "No pets found matching the criteria." : "Pets retrieved successfully.")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
