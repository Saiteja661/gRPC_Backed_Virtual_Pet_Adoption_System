package com.pet;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PetServer {
    public static void main(String[] args) {
        // Create a thread pool for the server
        ExecutorService threadPool = Executors.newFixedThreadPool(10); // 10 threads

        try {
            // Create the gRPC server
            Server server = ServerBuilder.forPort(50051)
                    .addService(new PetServiceImpl())
                    .executor(threadPool)  // Use the custom thread pool
                    .build();

            // Start the server
            server.start();
            System.out.println("Server started on port 50051");

            // Add a shutdown hook to gracefully stop the server
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down server...");
                server.shutdown();
                threadPool.shutdown();
                System.out.println("Server shut down successfully.");
            }));

            // Wait for clients to connect
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
