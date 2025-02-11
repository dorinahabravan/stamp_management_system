/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravan.stampmanagementsystem;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dorina
 */
public class Category {

    //The static list containing predefined categories of stamps.
    private static final List<String> stampCategories = Arrays.asList("Definitive", "Commemorative",
            "Used", "Mint");

    //The mdethod returns available stamps.
    public static List<String> getStampCategories() {
        return stampCategories;

    }
}
