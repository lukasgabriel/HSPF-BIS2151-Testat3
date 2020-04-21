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
    
    
}
