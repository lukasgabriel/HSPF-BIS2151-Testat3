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
public class Item {
    
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
    
    public void delete() {
        allItems.remove(this);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    private void generateUniqueId() {
        UUID idGen = UUID.randomUUID();
        id = idGen.toString().substring(0,6);
    }
    
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
