/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.menues;

import de.hspforzheim.Eaf;
import de.hspforzheim.commands.MenuOperation;
import de.hspforzheim.commands.SubMenuOperation;
import de.hspforzheim.commands.SubOperationType;
import de.hspforzheim.items.FlightManager;

/**
 *
 * @author Cedric Jansen
 */
public class FlightMenu extends Menu{
    
   
    private boolean isInFlightMenu;
    
    private static FlightManager flightManager = Eaf.getFlightManager();
    
    private SubMenuOperation backToMenu = new SubMenuOperation(0, "Back to main menu");
    private SubMenuOperation createFlight = new SubMenuOperation(1, "Create new flight");
    private SubMenuOperation updateFlight = new SubMenuOperation(2, "Update a flight");
    private SubMenuOperation deleteFlight = new SubMenuOperation(3, "Delete a flight");
    private SubMenuOperation showFlights = new SubMenuOperation(4, "Show flight list");
    
    
    private final SubMenuOperation[] subMenuOperations = new SubMenuOperation[] { 
                                                        createFlight, updateFlight, deleteFlight, 
                                                        showFlights, backToMenu};
    
    
    public FlightMenu() {
        super();
        this.title = "MANAGING FLIGHTS MENU";
    }
     
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
    
  
    @Override
    protected void handleUserInput( ) {
        switch(userInput) {
            case 0:
                this.setIsInFlightMenu(false);
                break;
            case 1:
                flightManager.addFlight("test");
                break;
            case 2:
                flightManager.updateFlight();
                break;
            case 3:
                flightManager.deleteFlight();
                break;
            case 4:
                flightManager.listFlights();
                break;
            default:
                System.out.println("Any other key. should stay");
                break;
        }
    }
    
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
    
    public void setIsInFlightMenu( boolean valueToSet) {
        isInFlightMenu = valueToSet;
    }
    
}
