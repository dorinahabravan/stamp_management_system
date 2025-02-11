/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravan.stampmanagementsystem;

import java.io.File;

/**
 *
 * @author Dorina
 */
public class Stamp {

    //Stamp attributes
    private String stampName;
    private int stampYear;
    private String stampCountry;
    private String stampCategory;
    private String imagePath;
    private String description;
    private String stampOwner;
    private String  price;

    //The special method which initialises a new stamp object.
    public Stamp(String stampName, int stampYear, String stampCountry, String imagePath, String description,String stampCategory,String stampOwner, String price) {
        this.stampName = stampName;
        this.stampYear = stampYear;
        this.stampCountry = stampCountry;
         this.imagePath = imagePath;
        this.description = description;
        this.stampCategory = stampCategory;
        this.stampOwner = stampOwner;
        this.price = price;
    }
    
    
// Encapsulation priciple- getters and setters for the stamp attributes
    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    public int getStampYear() {
        return stampYear;
    }

    public void setStampYear(int stampYear) {
        this.stampYear = stampYear;
    }

    public String getStampCountry() {
        return stampCountry;
    }

    public void setStampCountry(String stampCountry) {
        this.stampCountry = stampCountry;
    }

    public String getStampCategory() {
        return stampCategory;
    }

    public void setStampCategory(String stampCategory) {
        this.stampCategory = stampCategory;
    }

public String getImagePath() {
 
    File imageFile = new File(imagePath);

    if (imageFile.exists()) {
        System.out.println("✅ Image FOUND: " + imageFile.getAbsolutePath());
        return imageFile.getAbsolutePath();
    } else {
        System.out.println("❌ ERROR: Image NOT FOUND at: " + imagePath);
    }

    // Verify the  absolute image path dynamically
    String fullPath = System.getProperty("user.dir") + File.separator + imagePath;
    imageFile = new File(fullPath);

    if (imageFile.exists()) {
        System.out.println("✅ Image FOUND (absolute path fixed): " + fullPath);
        return fullPath;
    } else {
        System.out.println("❌ Still can't find image: " + fullPath);
    }

   
    fullPath = "C:/Users/Dorina/Documents/NetBeansProjects/StampManagementSystem/images/stamp.png";
    imageFile = new File(fullPath);

    if (imageFile.exists()) {
        System.out.println("✅ Image FOUND (hardcoded absolute path): " + fullPath);
        return fullPath;
    } else {
        System.out.println("❌ Still missing: " + fullPath);
    }


    String defaultImage = "C:/Users/Dorina/Documents/NetBeansProjects/StampManagementSystem/images/default.jpg";
    File defaultFile = new File(defaultImage);

    if (defaultFile.exists()) {
        System.out.println("✅ Default image FOUND: " + defaultImage);
        return defaultImage;
    } else {
        System.out.println("❌ Default image also missing!");
    }

    return null;  // ✅ Prevent NullPointerException
}

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    //Getters and setters for the remaining stamp attributes.
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStampOwner() {
        return stampOwner;
    }

    public void setStampOwner(String stampOwner) {
        this.stampOwner = stampOwner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stamp{" + "stampName=" + stampName + ", stampYear=" + stampYear + ", stampCountry=" + stampCountry + ", stampCategory=" + stampCategory + ", imagePath=" + imagePath + ", description=" + description + ", stampOwner=" + stampOwner + ", price=" + price + '}';
    }

    

    
    }


    



