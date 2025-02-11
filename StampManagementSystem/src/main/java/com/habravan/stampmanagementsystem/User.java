/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravan.stampmanagementsystem;

import java.util.List;

/**
 *
 * @author Dorina
 */
public class User {

    private String username;
    private String password;
    private List<Stamp> ownershipList;
    private List<Stamp> wishlist;

    public User(String username, String password, List<Stamp> ownershipList, List<Stamp> wishlist) {
        this.username = username;
        this.password = password;
        this.ownershipList = ownershipList;
        this.wishlist = wishlist;
    }
    
    
 //Compile- time polymorphism.
    public User(String username) {
        this.username = username;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Stamp> getOwnershipList() {
        return ownershipList;
    }

    public void setOwnershipList(List<Stamp> ownershipList) {
        this.ownershipList = ownershipList;
    }

    public List<Stamp> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Stamp> wishlist) {
        this.wishlist = wishlist;
    }

    public void addStampToOwnershipList(Stamp stamp) {
        ownershipList.add(stamp);

    }

    public void removeStampFromOwnership(Stamp stamp) {
        ownershipList.remove(stamp);

    }

    public void addStampToWhishlist(Stamp stamp) {
        wishlist.add(stamp);

    }

    public void removeStampFromWhishlist(Stamp stamp) {
        wishlist.remove(stamp);
    }
}
