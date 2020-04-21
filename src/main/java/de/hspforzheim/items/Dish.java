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
public class Dish extends Item{
    
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
    public void delete() {
        super.delete();
        allDishes.remove(this);
    }
    
     @Override
    public String toString() {
        return "[Dish: [ID:" + this.id + "] " + this.name + "]"; 
    }
    
    
    
}
