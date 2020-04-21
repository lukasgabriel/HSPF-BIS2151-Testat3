/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim;

;
import de.hspforzheim.items.DishManager;
import de.hspforzheim.items.FlightManager;
import de.hspforzheim.menues.DishMenu;
import de.hspforzheim.menues.FlightMenu;
import de.hspforzheim.menues.Menu;

/**
 *
 * @author Cedric Jansen
 */


public class Eaf {

    private static Menu menu;
    private static FlightMenu flightMenu;
    private static DishMenu dishMenu;
    private static FlightManager flightManager;
    private static DishManager dishManager;

    // Program entry point
    public static void main(String[] args) {
        flightManager = new FlightManager();
        dishManager = new DishManager();
        menu = new Menu();
        flightMenu = new FlightMenu();
        dishMenu = new DishMenu();
        menu.start();
    }

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
