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
public class Dish extends Item {
    
    
    private ArrayList<Flight> flightsThatContainDish = new ArrayList<Flight>();
    // Create this object.
    // 1. Call base constructor, 2. assign name variable, 3. create unique id
    // 4. add to static Item ArrayList
    // 5. back to Dish() constructor. Add this dish to the static ArrayList containing all dishes.
    public Dish(String name) {
       super(name);
       ArrayList<Dish> dishList = Item.getAllDishes();
       dishList.add(this);
    }
    
    
    @Override
    public void add(Object object) {
        super.add(object);
        if(!(object instanceof Flight)) {
            System.out.println("Cannot add " + object.toString() + " to a dish");
        }
        else {
            Flight flight = (Flight) object;
            flightsThatContainDish.add(flight);
        } 
    }
    
    @Override
    public void delete() {
        super.delete();
        ArrayList<Flight> flights = Flight.getAllFlights();
        for(Flight flight : flights) {
            ArrayList<Dish> listOfDishesInFlight= flight.getDishes();
            if(listOfDishesInFlight.contains(this)) {
                listOfDishesInFlight.remove(this);
            }
        }
        allDishes.remove(this);
    }
    
    
    public ArrayList<Flight> getFlights() {
        return flightsThatContainDish;
    }
    
     @Override
    public String toString() {
        return "[Dish: [ID:" + this.id + "] " + this.name + "]"; 
    }
    
    
    
}
