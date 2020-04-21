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
public class Flight extends Item{
    

    private String flightNumber;
    private int dishCapacity;
    
    private ArrayList<Dish> storedDishes = new ArrayList<>();
    
    // Create this object.
    // 1. Call base constructor, 2. assign name variable, 3. create unique id
    // 4. add to static Item ArrayList
    // 5. back to Flight() constructor. Add this Flight to the static ArrayList containing all flights.
    public Flight(String name) {
       super(name);
       ArrayList<Flight> flightList = Item.getAllFlights();
       flightList.add(this);
    }
    
    public Flight(String name, String flightNumber, int dishCapacity) {
      this(name);
      this.dishCapacity = dishCapacity;
    }
    
    
    // Item delete() deletes reference from static ArrayList containig Items
    // if afterwards reference stored inside the static ArrayList containing all
    // flights is deleted, this object will be garbage collected.
    @Override
    public void delete() {
        super.delete();
        allFlights.remove(this);
    }
    
    @Override
    public String toString() {
        return "[Flight: [ID:" + this.id + "] " + this.name + "]"; 
    }
    
    
    

}
