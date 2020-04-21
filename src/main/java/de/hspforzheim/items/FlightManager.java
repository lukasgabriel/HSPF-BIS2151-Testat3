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
    
    private static final String ASK_FOR_FLIGHT_NAME = "Please enter a flight name:";
    private static final String ASK_FOR_FLIGHT_NUMBER = "Please enter a flight number (any 3 chars):";
    private static final String ASK_FOR_DISH_CAPACITY = "Please enter a dish capacity (1-5):";
    
    public void addFlight() {
        Scanner scanner = new Scanner(System.in);
        String name = checkName(scanner);
        String flightNumber = checkFlightNumber(scanner);
        int dishCapacity = checkDishCapacity(scanner);
        Flight flight = new Flight(name, flightNumber, dishCapacity);
    }
    
     private int checkDishCapacity(Scanner scanner) {
         int dishCapacity = 0;
         while(dishCapacity < 1 || dishCapacity > 5) {
            System.out.println(ASK_FOR_DISH_CAPACITY);
            try {
                dishCapacity = scanner.nextInt(); 
            } catch( InputMismatchException e) {
                dishCapacity = 0;
                scanner.next();
            }
   
        }
        return dishCapacity;
    }
    
    
     private String checkFlightNumber(Scanner scanner) {
         String flightNumber = "";
         while(flightNumber.length() != 3) {
            System.out.println(ASK_FOR_FLIGHT_NUMBER);
            try {
                flightNumber = scanner.nextLine(); 
            } catch( InputMismatchException e) {
                flightNumber = "";
                scanner.next();
            }
            flightNumber = flightNumber.trim();
            flightNumber = flightNumber.toUpperCase();
        }
        return flightNumber;
    }
    
    private String checkName(Scanner scanner) {
         String name = "";
         while(name.length() <= 5 || name.length() >= 64) {
            System.out.println(ASK_FOR_FLIGHT_NAME);
            try {
                name = scanner.nextLine(); 
            } catch( InputMismatchException e) {
                name = "";
                scanner.next();
            }
            name = name.trim();
        }
        return name;
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
        super.delete(this);  
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
