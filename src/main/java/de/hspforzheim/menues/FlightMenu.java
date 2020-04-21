/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.menues;

import de.hspforzheim.Eaf;
import de.hspforzheim.commands.MenuOperation;
import de.hspforzheim.commands.SubMenuOperation;
import de.hspforzheim.items.FlightManager;

/**
 *
 * @author Cedric Jansen
 */
public class FlightMenu extends Menu{
    
    // Boolean that determines if the menu should be exited.
    private boolean isInFlightMenu;
    
    // Reference to the FlightManager object
    private static FlightManager flightManager = Eaf.getFlightManager();
    
    // Similar to MenuOperation, SubMenuOperation determines all commands that can be
    // exectued within this menu.
    private SubMenuOperation backToMenu = new SubMenuOperation(0, "Back to main menu");
    private SubMenuOperation createFlight = new SubMenuOperation(1, "Create new flight");
    private SubMenuOperation updateFlight = new SubMenuOperation(2, "Update a flight");
    private SubMenuOperation deleteFlight = new SubMenuOperation(3, "Delete a flight");
    private SubMenuOperation showFlights = new SubMenuOperation(4, "Show flight list");
    
    // Array of all subMenuOperations that this object can access. backToMenu should be the last element.
    private final SubMenuOperation[] subMenuOperations = new SubMenuOperation[] { 
                                                                createFlight, updateFlight, 
                                                                deleteFlight, showFlights, 
                                                                backToMenu
                                                              };
    
    
    public FlightMenu() {
        super(); // Call the super constructor --> Menu(), sets title, subtitle and enterChoice
        this.title = "MANAGING FLIGHTS MENU";      // Change this object's title.
    }
    
    
    // Starts the flight menu. Loops as long as isInFlightMenu is set to true.
    // Inherited from the Menu class.
    @Override
    public void start() {
        isInFlightMenu = true;
        while(isInFlightMenu) {
            printFullHeader();
            printSubHeader();
            printCommands();
            printChoiceQuestion();
            userInput();
            handleUserInput();
        }  
    }
    
    // Handle the user input within the flight menu.
    // 0: --> Exit the flight menu, boolean inFlightMenu gets set to false,
    //        jump back to the top of  while loop inside start(), check condition, exit loop
    // 1: --> add flight
    // 2: --> update certain flight.
    // 3: --> delete flight
    // 4: --> list flights
    @Override
    protected void handleUserInput() {
        switch(userInput) {
            case 0:  // user input is 0
                isInFlightMenu = false;
                break;
            case 1: // user input is 1
                flightManager.addFlight();
                break;
            case 2: // user input is 2
                flightManager.updateFlight();
                break;
            case 3: // user input is 3
                flightManager.deleteFlight();
                break;
            case 4: // user input is 4
                flightManager.listFlights();
                break;
            default: // User input is anything but 0, 1, 2, 3, 4
                     // Displays the flight menu again.
                break;
        }
    }
    
    
    // Overrides printCommands() inherited from the Menu class.
    // Same function but iterates over the subMenuOperations.
    @Override
    protected void printCommands() {
        for(MenuOperation op : subMenuOperations) {
            if(op.getKey() == 0) {
                System.out.printf("\n%-18s%6s\n", op.getConsoleName(), op.getKey());
            }
            else {
                 System.out.printf("%-18s%6s\n", op.getConsoleName(), op.getKey());
            }
        }
    }
    
    // If isInFlight is set to false, the menu is exited.
    public void setIsInFlightMenu( boolean valueToSet) {
        isInFlightMenu = valueToSet;
    }
    
}
