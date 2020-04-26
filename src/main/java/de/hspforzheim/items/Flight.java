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
    

    public Flight(String name) {
       super(name);
       ArrayList<Flight> flightList = Item.getAllFlights();
       flightList.add(this);
    }
    
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
