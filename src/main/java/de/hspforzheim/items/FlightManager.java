/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.items;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cedric Jansen
 */
public class FlightManager extends Manager{
    
    
    
    public void addFlight(String name) {
        Flight flight = new Flight(name);
    }
    
    public void listFlights() {
        ArrayList<Flight> flights = Item.getAllFlights();
        if(flights.isEmpty()) {
            System.out.println("No flights to display.");
        }
        else {
           for( Flight flight : flights) {
               System.out.println("[Flight id:] " + flight.getId() + " --> " + flight.getName());
           }
        }
    }
    
    public void updateFlight() {
        System.out.println("Update flight.");
    }
    
    public void deleteFlight() {
        super.delete();  
    }

    @Override
    protected void deleteWithId(String id) {
       ArrayList<Flight> flights = Item.getAllFlights(); 
       for( Flight flight : flights) {
           if(flight.getId().equals(id)) {
               flight.delete();
               System.out.println("Deleted flight: " + flight);
               return;
           }
       }
       System.out.println("No Flight with given id: " + id + "was found.");
    }

    @Override
    protected void deleteAtIndex(int index) {
      ArrayList<Flight> flights = Item.getAllFlights();
      System.out.println(flights.get(index) + "was deleted."); 
      flights.get(index).delete();
      
        
    }

    @Override
    protected void showItemIds() {
        ArrayList<Flight> flights = Item.getAllFlights();
        for( Flight flight : flights) {
            System.out.println( "[Flight ID:] " + flight.getId() + " --> " + flight.getName());
        }
    }

    @Override
    protected void showItemIndexes() {
        ArrayList<Flight> flights = Item.getAllFlights();
        for(int i = 0; i < flights.size(); i++) {
            System.out.println("[Flight INDEX:] " + i + " --> " + flights.get(i).getName());
        }
    }
}
