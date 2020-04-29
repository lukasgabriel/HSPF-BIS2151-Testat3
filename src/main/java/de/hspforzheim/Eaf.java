/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim;

;
import de.hspforzheim.items.DishManager;
import de.hspforzheim.items.Flight;
import de.hspforzheim.items.FlightManager;
import de.hspforzheim.menues.DishMenu;
import de.hspforzheim.menues.FlightMenu;
import de.hspforzheim.menues.Menu;

/**
 *
 * @author Cedric Jansen
 */

// Start of the whole program. Implements the
// public static void main() method.

public class Eaf {

    // The reference for the main menu. Handles console out/ and input.
    private static Menu menu;
    // The reference for the flight menu. Handles console out/ and input. Extends the main menu.
    private static FlightMenu flightMenu;
    // The reference for the dish menu. Handles console out/ and input. Extends the main menu.
    private static DishMenu dishMenu;
    
    // The reference for the flight manager. Handles the creation, update, deletion and displaying of flights.
    private static FlightManager flightManager;
    
    //The reference for the dish manager. Handles the creation, update, deletion and displaying of dishes.
    private static DishManager dishManager;

    // Program entry point
    public static void main(String[] args) {
        // Initialise all needed objects.
        // Initialise the managers before initialising the menues.
        flightManager = new FlightManager();
        dishManager = new DishManager();
        
        
        menu = new Menu();
        flightMenu = new FlightMenu();
        dishMenu = new DishMenu();
        
        Flight f1 = new Flight("erster", "LFT", 150, 2);
        Flight f2 = new Flight("zweiter", "LFT", 150, 1);
        Flight f3 = new Flight("dritter", "LFT", 100, 5);
        
        
        // Start the main menu.
        menu.start();
    }

    
    // Getters and setters.
    public static void setFlightMenu(FlightMenu flightMenuToSet) {
        flightMenu = flightMenuToSet;
    }

    public static FlightMenu getFlightMenu() {
        return flightMenu;
    }

    public static DishMenu getDishMenu() {
        return dishMenu;
    }

    public static FlightManager getFlightManager() {
        return flightManager;
    }

    public static DishManager getDishManager() {
        return dishManager;
    }

}
