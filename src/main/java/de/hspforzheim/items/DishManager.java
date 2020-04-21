/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.items;

import java.util.ArrayList;

/**
 *
 * @author Cedric Jansen
 */
public class DishManager {
    
    
    public void addDish(String name) {
        Dish dish = new Dish(name);
    }
    
    public void listDishes() {
        ArrayList<Dish> dishList = Item.getAllDishes();
        if(dishList.isEmpty()) {
            System.out.println("No dishes to display.");
        }
        else {
           for( Dish dish : dishList) {
               System.out.println("Flight id: " + dish.getId());
           }
        }
    }
    
    public void updateDish() {
        System.out.println("Update flight.");
    }
    
    public void deleteDish() {
        System.out.println("Delete flight.");
    }
}
