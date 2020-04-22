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
public class DishManager extends Manager {
    
    
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
               System.out.println("[Dish id:] " + dish.getId() + " --> " + dish.getName());
           }
        }
    }
    
    public void updateDish() {
        System.out.println("Update dish.");
    }
    
    public void deleteDish() {
        super.delete(this);
    }

    @Override
    protected void deleteWithId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void deleteAtIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void showItemIds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void showItemIndexes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
