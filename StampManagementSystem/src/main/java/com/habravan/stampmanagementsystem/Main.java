package com.habravan.stampmanagementsystem;

import com.opencsv.exceptions.CsvValidationException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CsvValidationException {
        // Create single instances for login and stamp management.
        LoginSystem loginSystem = new LoginSystem();
        StampManagement stampManagement = new StampManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("Select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Your option:");
            
            String option = scanner.nextLine().trim();
            
            if (option.equals("1")) {
                // Registration path
                System.out.println("\n=== Register Your Account ===");
                System.out.println("Enter your username:");
                String username = scanner.nextLine().trim();
                System.out.println("Enter your password:");
                String password = scanner.nextLine().trim();
                loginSystem.register(username, password);
                System.out.println("Registration complete. You can now login.");
                
            } else if (option.equals("2")) {
                // Login path
                System.out.println("\n=== Login to Your Account ===");
                System.out.println("Enter your username:");
                String username = scanner.nextLine().trim();
                System.out.println("Enter your password:");
                String password = scanner.nextLine().trim();
                System.out.println("Attempting user login: " + username);
                
                if (loginSystem.login(username, password)) {
                    System.out.println("Login succeeded for user " + username + "!");
                    // Once the user is logged in, allow stamp management.
                    stampManagementMenu(stampManagement, scanner);
                } else {
                    System.out.println("Login failed. Please check your credentials and try again.");
                }
                
            } else if (option.equals("3")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
                
            } else {
                System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    //The method allows user to handle stamp management once is logged in.
    private static void stampManagementMenu(StampManagement stampManagement, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Stamp Management Menu ---");
            System.out.println("Select an option:");
            System.out.println("1. List Stamps");
            System.out.println("2. Add Stamp");
            System.out.println("3. Logout");
            System.out.println("Your option:");
            String choice = scanner.nextLine().trim();
            
            if (choice.equals("1")) {
                stampManagement.listStamps();
            } else if (choice.equals("2")) {
                System.out.println("You have chosen to add a new stamp.");
                System.out.println("Enter stamp name:");
                String name = scanner.nextLine().trim();
                System.out.println("Enter stamp year:");
                int year = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter stamp category:");
                String category = scanner.nextLine().trim();
       
                 System.out.println("Enter stamp image path:");
                String imagePath = scanner.nextLine().trim();
                
                 System.out.println("Enter stamp description:");
                String description = scanner.nextLine().trim();
       
       
                
                
                System.out.println("Enter stamp country:");
                String country = scanner.nextLine().trim();
                System.out.println("Enter stamp owner:");
                String owner = scanner.nextLine().trim();
                
                 System.out.println("Enter stamp price:");
                String price = scanner.nextLine().trim();
       
                       
                       
                // Create a new stamp 
      Stamp stamp = new Stamp(name, year, country, imagePath, description, category, owner, price);

                stampManagement.addStamp(stamp);
                System.out.println("Stamp added successfully.");
            } else if (choice.equals("3")) {
                System.out.println("Logging out from stamp management.");
                break;// The process of logout and go back to main menu.
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
