package com.habravan.stampmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginSystem {
    private static final String CSV_FILE = "users.csv";//CSV file where users are stored.
    private Map<String, String> userLoginCredentials;//username and ppassword.

    
    //The special method which initialises the login system.
    public LoginSystem() {
        this.userLoginCredentials = new HashMap<>();
        System.out.println("Initializing LoginSystem...");
        loadUsersFromCSV();  // Load existing users, if any
    }
    
    //Registering a new user 
    public void register(String username, String password) {
        // Remove extra spaces from the input
        username = username.trim();
        password = password.trim();
        System.out.println("Attempting to register user: " + username);
        if (!userLoginCredentials.containsKey(username)) {
            userLoginCredentials.put(username, password);
            System.out.println("User " + username + " registered successfully.");
            saveUsersToCSV();  // Save immediately after registration
        } else {
            System.out.println("Registration failed: User " + username + " already exists.");
        }
    }

    
    
    
    //method checks if username and password match the stored credentials.
    public boolean login(String username, String password) {
        username = username.trim();
        password = password.trim();
        System.out.println("Attempting user login: " + username);
        boolean success = userLoginCredentials.containsKey(username) &&
                          userLoginCredentials.get(username).equals(password);
        System.out.println("Login " + (success ? "succeeded" : "failed") + " for user " + username);
        return success;
    }

    
    //Loads existing users from CSV file 
    private void loadUsersFromCSV() {
        File csvFile = new File(CSV_FILE);
        System.out.println("Loading users from CSV file: " + csvFile.getAbsolutePath());
        try (BufferedReader bReader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int count = 0;
            while ((line = bReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String user = parts[0].trim();
                    String pass = parts[1].trim();
                    userLoginCredentials.put(user, pass);
                    count++;
                    System.out.println("Loaded user: " + user);
                }
            }
            System.out.println("Total users loaded: " + count);
        } catch (IOException e) {
            System.out.println("Users not found or error reading CSV. Starting with an empty user list.");
        }
    }

    
    //The method saves the list of users to CSV file "users.csv"
    private void saveUsersToCSV() {
        File csvFile = new File(CSV_FILE);
        System.out.println("Saving users to CSV file: " + csvFile.getAbsolutePath());
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(csvFile))) {
            for (Map.Entry<String, String> entry : userLoginCredentials.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                System.out.println("Writing to CSV: " + line);
                bWriter.write(line);
                bWriter.newLine();
            }
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users to CSV:");
            e.printStackTrace();
        }
    }
}
