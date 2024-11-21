package com.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.example.UserOuterClass.*;

public class Client {
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public Client(ManagedChannel channel) {
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public void saveUser(int id, String username, String fullname) {
        User user = User.newBuilder()
                .setId(id)
                .setUsername(username)
                .setFullname(fullname)
                .build();

        Response response;
        try {
            response = blockingStub.saveUser(user);
            System.out.println("Response from server: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
        }
    }

    public void getUserById(int id) {
        UserId userId = UserId.newBuilder()
                .setId(id)
                .build();

        UserInfo userInfo;
        try {
            userInfo = blockingStub.getUserById(userId);
            System.out.println("User Info: " + userInfo.getUsername() + ", " + userInfo.getFullname());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- User Management Menu ---");
            System.out.println("1. Add New User");
            System.out.println("2. Search User by ID");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Full Name: ");
                    String fullname = scanner.nextLine();
                    saveUser(id, username, fullname);
                    break;
                case 2:
                    System.out.print("Enter User ID to search: ");
                    int searchId = scanner.nextInt();
                    getUserById(searchId);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        Client client = new Client(channel);
        client.showMenu();

        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
