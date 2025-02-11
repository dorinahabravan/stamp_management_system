package com.habravan.stampmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StampManagement {

    private static final String CSV_FILE = "stamps.csv";//CSV file for stamps.
    private List<Stamp> stamps;

    
    //Constructor to initialise the stamp list
    public StampManagement() {
        stamps = new ArrayList<>();
        loadStampsFromCSV();
    }

    
    //Adding a stamp to the list and saving the update list to CSV
    public void addStamp(Stamp stamp) {
        stamps.add(stamp);
        saveStampsToCSV();
    }

    //Shows all the stamps in the system.
    public void listStamps() {
        if (stamps.isEmpty()) {
            System.out.println("No stamps available.");
        } else {
            System.out.println("Available Stamps:");
            for (Stamp stamp : stamps) {
                System.out.println(stamp);
            }
        }
    }

    
    //Verify if the list of stamps is not null.
    public List<Stamp> getStamps() {
        System.out.println("🟡 getStamps() method is called");

        if (stamps == null) {
            System.out.println("❌ ERROR: 'stamps' list is NULL!");
        } else if (stamps.isEmpty()) {
            System.out.println("❌ ERROR: 'stamps' list is EMPTY!");
        } else {
            System.out.println("✅ Stamps loaded:");
            for (Stamp stamp : stamps) {
                System.out.println("   ➤ " + stamp.getStampName() + " (" + stamp.getStampYear() + ")");
            }
        }

        return stamps;
    }


//Loads the stamps from CSV file.
public void loadStampsFromCSV() {
    stamps.clear(); // ✅ Clear the list before loading new stamps
    System.out.println("DEBUG: Loading stamps from CSV...");

    try (BufferedReader br = new BufferedReader(new FileReader("stamps.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("✅ CSV Line Read: " + line);
            String[] parts = line.split(",", -1); // ✅ Ensures empty fields don't shift data

            if (parts.length == 8) { // ✅ Expected 8 columns (including image path)
                // ✅ Remove full Windows path if present
                String imagePath = parts[3];
                if (imagePath.startsWith("C:\\")) { 
                    imagePath = imagePath.substring(imagePath.lastIndexOf("images"));
                }
                if (imagePath.equals("null")) { // ✅ Handle missing images
                    imagePath = "";
                }

                Stamp stamp = new Stamp(
                        parts[0], // Name
                        Integer.parseInt(parts[1]), // Year
                        parts[2], // Country
                        imagePath, // ✅ Use relative path
                        parts[4], // Description
                        parts[5], // Category
                        parts[6], // Owner
                        parts[7]
                );
                stamps.add(stamp);
                System.out.println("✅ Stamp Added: " + stamp.getStampName() + " | Image: " + stamp.getImagePath());
            } else {
                System.out.println("❌ ERROR: Incorrect CSV format. Skipping -> " + line);
            }
        }
    } catch (IOException e) {
        System.out.println("❌ ERROR: Could not read stamps.csv!");
        e.printStackTrace();
    } catch (NumberFormatException e) {
        System.out.println("❌ ERROR: Price format is incorrect in CSV!");
        e.printStackTrace();
    }

    if (stamps.isEmpty()) {
        System.out.println("❌ ERROR: 'stamps' list is EMPTY!");
    } else {
        System.out.println("✅ Successfully loaded " + stamps.size() + " stamps.");
    }
}



    // Save stamps to the CSV file.
   public void saveStampsToCSV() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("stamps.csv"))) {
        for (Stamp stamp : stamps) {
            String line = stamp.getStampName() + "," +
                          stamp.getStampYear() + "," +
                          stamp.getStampCountry() + "," +
                          (stamp.getImagePath().isEmpty() ? "null" : stamp.getImagePath()) + "," +
                          stamp.getDescription() + "," +
                          stamp.getStampCategory() + "," +
                          stamp.getStampOwner() + "," +
                          stamp.getPrice(); //Checking if the  price is saved

            bw.write(line);
            bw.newLine();
        }
        System.out.println("✅ Stamps successfully saved to CSV.");
    } catch (IOException e) {
        System.out.println("❌ ERROR: Could not save stamps to CSV!");
        e.printStackTrace();
    }
}


}
