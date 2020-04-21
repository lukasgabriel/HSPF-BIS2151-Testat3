/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.items;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Cedric Jansen
 */

// Base class for all items. 
public class Item {
    
    // References storing all items in ArrayLists
    private static ArrayList<Item> allItems = new ArrayList<>();
    protected static ArrayList<Dish> allDishes = new ArrayList<>();
    protected static ArrayList<Flight> allFlights = new ArrayList<>();
    
    protected String id;
    protected String name;
    
    public Item(String name) {
        this.name = name;
        generateUniqueId();
        allItems.add(this);
    }
    
    // Delete this item from the ArrayList.
    // Garbage collector will handle this object if all references are deleted.
    public void delete() {
        allItems.remove(this); 
    }
    
    @Override
    public String toString() {
        return "[Item: [ID:" + this.id + "] " + this.name + "]"; 
    }
    

    // Generates a unique id for each item with the length
    // of 6 characters.
    private void generateUniqueId() {
        UUID idGen = UUID.randomUUID();
        id = idGen.toString().substring(0,6);
    }
    
    // Object getters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    
    // Static getters
    
    public static ArrayList<Item> gettAllItems() {
        return allItems;
    }
    
    public static ArrayList<Flight> getAllFlights() {
        return allFlights;
    }
    
    public static ArrayList<Dish> getAllDishes() {
        return allDishes;
    }
}
