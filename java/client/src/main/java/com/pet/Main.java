package com.pet;

import com.google.protobuf.ByteString;
import com.pet.proto.PetOuterClass;
import com.pet.proto.PetServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

class CommonResFileManager {

    // Ensure the specified directory exists, creating it if necessary
    public static Path ensureDirExists(Path dir) {
        try {
            // Create the directory if it does not exist
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
                System.out.println("Created directory: " + dir.toString());
            } else {
                System.out.println("Directory already exists: " + dir.toString());
            }
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + dir.toString());
            e.printStackTrace();
        }
        return dir;
    }
}

public class Main {

    private static PetServiceGrpc.PetServiceBlockingStub petServiceStub;

    public static void main(String[] args) throws Exception {

        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress("server", 50051)
                    .usePlaintext()
                    .build();
            petServiceStub = PetServiceGrpc.newBlockingStub(channel);
            System.out.println("Connected to gRPC service successfully. V2.9");
        } catch (Exception e) {
            System.err.println("Failed to connect to gRPC service: " + e.getMessage());
            e.printStackTrace(System.err);
        }

        // Create the server and context
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Register HelloServlet
        context.addServlet(new ServletHolder(new HelloServlet()), "/");

        // Register RegisterPetServlet with multipart config
        ServletHolder servletHolder = new ServletHolder(RegisterPetServlet.class);
        servletHolder.setInitOrder(1); // Set order if needed

        // Set up multipart configuration
        Path multipartTmpDir = Paths.get("target", "multipart-tmp");
        multipartTmpDir = CommonResFileManager.ensureDirExists(multipartTmpDir);

        String location = multipartTmpDir.toString();
        long maxFileSize = 10 * 1024 * 1024; // 10 MB
        long maxRequestSize = 10 * 1024 * 1024; // 10 MB
        int fileSizeThreshold = 64 * 1024; // 64 KB

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                location,
                maxFileSize,
                maxRequestSize,
                fileSizeThreshold
        );

        servletHolder.getRegistration().setMultipartConfig(multipartConfig);
        context.addServlet(servletHolder, "/register");

        // Register SearchPetsServlet
        context.addServlet(new ServletHolder(new SearchPetsServlet()), "/search");

        // Start the server
        server.start();
        System.out.println("Server started successfully.");
        server.join();
    }


    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            System.out.println("Serving index page.");

            resp.setContentType("text/html");
            resp.getWriter().write(
                    new String(getClass().getClassLoader().getResourceAsStream("webapp/index.html").readAllBytes())
            );
        }
    }

    @MultipartConfig
    public static class RegisterPetServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("Received request to register a pet.");

            // Ensure the request is parsed as multipart
            req.setCharacterEncoding("UTF-8");

            // Retrieve parameters from the form data
            String id = getFormField(req, "id");
            String name = getFormField(req, "name");
            String breed = getFormField(req, "breed");
            String gender = getFormField(req, "gender");
            int age = Integer.parseInt(getFormField(req, "age"));

            System.out.println("Registering pet with ID: " + id);

            // Retrieve the uploaded picture
            Part picturePart = req.getPart("picture");
            byte[] pictureBytes = picturePart != null ? picturePart.getInputStream().readAllBytes() : new byte[0];

            PetOuterClass.Pet pet = PetOuterClass.Pet.newBuilder()
                    .setId(id)
                    .setName(name)
                    .setBreed(breed)
                    .setGender(gender)
                    .setAge(age)
                    .setPicture(ByteString.copyFrom(pictureBytes))
                    .build();

            PetOuterClass.RegisterPetRequest registerRequest = PetOuterClass.RegisterPetRequest.newBuilder()
                    .setPet(pet)
                    .build();

            try {
                PetOuterClass.RegisterPetResponse response = petServiceStub.registerPet(registerRequest);
                System.out.println("Pet registered successfully: " + response.getMessage());

                resp.setContentType("application/json");
                resp.getWriter().write("{\"message\":\"" + response.getMessage() + "\"}");
            } catch (StatusRuntimeException e) {
                System.err.println("Error registering pet: " + e.getMessage());
                e.printStackTrace(System.err);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            }
        }

        // Helper method to extract form fields from multipart requests
        private String getFormField(HttpServletRequest req, String fieldName) throws IOException, ServletException {
            Part part = req.getPart(fieldName);
            if (part != null) {
                return new String(part.getInputStream().readAllBytes(), "UTF-8");
            }
            return null;
        }
    }

    public static class SearchPetsServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            System.out.println("Received search request.");

            String name = req.getParameter("name");
            String breed = req.getParameter("breed");
            String gender = req.getParameter("gender");
            int age = req.getParameter("age") != null && !req.getParameter("age").isEmpty()
                    ? Integer.parseInt(req.getParameter("age"))
                    : 0;

            System.out.println("Searching pets with parameters - Name: " + name + ", Breed: " + breed +
                    ", Gender: " + gender + ", Age: " + age);

            PetOuterClass.SearchPetsRequest.Builder searchRequestBuilder = PetOuterClass.SearchPetsRequest.newBuilder();
            if (name != null) searchRequestBuilder.setName(name);
            if (breed != null) searchRequestBuilder.setBreed(breed);
            if (gender != null) searchRequestBuilder.setGender(gender);
            if (age > 0) searchRequestBuilder.setAge(age);

            try {
                PetOuterClass.SearchPetsResponse response = petServiceStub.searchPets(searchRequestBuilder.build());
                List<String> pets = response.getPetsList().stream()
                        .map(pet -> {
                            String pictureBase64 = Base64.getEncoder().encodeToString(pet.getPicture().toByteArray());
                            return String.format(
                                    "{\"id\":\"%s\", \"name\":\"%s\", \"breed\":\"%s\", \"age\":%d, \"gender\":\"%s\", \"picture\":\"%s\"}",
                                    pet.getId(), pet.getName(), pet.getBreed(), pet.getAge(), pet.getGender(), pictureBase64
                            );
                        })
                        .collect(Collectors.toList());

                System.out.println("Search completed. Found " + pets.size() + " pets.");

                resp.setContentType("application/json");
                resp.getWriter().write("{\"pets\":[" + String.join(",", pets) + "]}");
            } catch (io.grpc.StatusRuntimeException e) {
                System.err.println("Error during pet search: " + e.getStatus().getDescription());
                e.printStackTrace(System.err);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"" + e.getStatus().getDescription() + "\"}");
            }
        }
    }
}
