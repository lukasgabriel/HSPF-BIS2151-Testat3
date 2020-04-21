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

// This class cannot be instantiated. This is used to create the 
// FlightManager and DishManager objects.
public abstract class Manager {

    
    // Final strings 
    private static final String DELETE_HEAD = "[DELETE:]";
    private static final String DELETE_STRING1 = "Would you like to delete via index or id?";
    private static final String DELETE_STRING2 = "Enter (IN/ID). Cancel with (X).";
    private static final String SHOW_INDEX1 = "Would you like to see all indexes?";
    private static final String SHOW_ID1 = "Would you like to see all IDs?";
    private static final String DELETE_INDEX_WHERE = "Item at which index should be deleted? Please enter:";
    private static final String DELETE_ID_WHERE = "Item with which ID should be deleted?: Please enter:";
    private static final String YES_NO_CONFIRMATION = "Enter (Y/N) for yes or no.";
    private static final String CANCEL_ACTION = "-->Action was canceled.";
    private static final String NO_ITEMS = "No items present that can be deleted.";
    
    
   
    
    // Delete process
    protected void delete(Object sender) {
        // The two ArrayLists containing all flights and dishes
        ArrayList<Flight> flights = Item.getAllFlights();
        ArrayList<Dish> dishes = Item.getAllDishes();
        // Scanner object used to stream user input.
        Scanner scanner = new Scanner(System.in);
        
        // Variables used to store user input
        String input = "";
        String secondInput = "";    
        String deleteId = "";
        
        int deleteIndex = -1;
        boolean showId = false;  // True if user chooses to delete per ID.

        System.out.println("");
        System.out.println(DELETE_HEAD); // Print the head.
        
        // If sender is instanceof Flightmanager and there are no flights to delete, return.
        if(flights.isEmpty() && sender instanceof FlightManager) {
            System.out.println(NO_ITEMS);
            return;
        }
        
        // If sender is instanceof DishManager and there are no dishes to delete, return.
        if(dishes.isEmpty() && sender instanceof DishManager) {
            System.out.println(NO_ITEMS);
            return;
        }

        // While user input is anything but IN, ID or X, loop.
        while ((!input.equals("ID")) && (!input.equals("IN")) && (!input.equals("X"))) {
            System.out.println(DELETE_STRING1);
            System.out.println(DELETE_STRING2);
            
            // Save next user input to the input variable. If anything goes wrong,
            // clear the input variable. This causes repeat the loop.
            try {
                input = scanner.nextLine(); // Try to scan the next input.
            } catch (InputMismatchException e) {
                input = "";
                scanner.next();  // Input was strange, clear scanner for next input.
            }

            input = input.trim(); // User might enter spaces before and after his input per accident. 
                                  // Delete whitespaces in front and back.
            input = input.toUpperCase(); // User might enter input as lowercase. Uppercase the input before comparison.
        }
        
        // User wants to either delete with INDEX or ID.
        if (input.equals("IN") || input.equals("ID")) {
            
            // User wants to delete with INDEX:
            if (input.equals("IN")) {
                System.out.println(SHOW_INDEX1);
            } else { // User wants to delete with ID. 
                System.out.println(SHOW_ID1);
                showId = true;  // IMPORTANT: Set showId to true. 
            }
            System.out.println(YES_NO_CONFIRMATION);
            
            // While user did not input y or n to tell whether he wants to
            // display a list with all items either showing their index or id.
            while ((!secondInput.equals("Y")) && (!secondInput.equals("N"))) {

                try {
                    secondInput = scanner.nextLine(); // Try to read the next input.
                } catch (InputMismatchException e) {
                    secondInput = ""; // If this goes wrong, set the input to and empty string which causes
                                      // the loop to execute again.
                }

                secondInput = secondInput.trim();         // Trim whitespaces in front and back of string.
                secondInput = secondInput.toUpperCase();  // Uppercase whole string before comparison
            }
            
            // User wants to either print list of all items with id or index.
            if (secondInput.equals("Y")) {
                if (showId) {  // User chose ID path
                    showItemIds();
                } else {   // User wants to delete by index, so show all indexes of items.
                    showItemIndexes();
                }
            }
            
            //If user wants to delete by ID.
            if (showId) {
                // while no id present
                while (deleteId.length() <= 0) {
                    System.out.print(DELETE_ID_WHERE);
                    try {
                        deleteId = scanner.nextLine(); // Try to assign next user input to id to delete.
                    } catch (InputMismatchException e) {
                        deleteId = ""; // if this goes wrong, assign an empty string with length of 0.
                    }

                    deleteId = deleteId.trim(); // Only trim since the ID is case sensitive.
                }
                deleteWithId(deleteId); // deleteId is present, delete item with ID = deleteId
            } else {  //User wants to delete by index
                while(deleteIndex < 0 || deleteIndex >= flights.size()) {  // while index is out of bounds, ask for new index
                    System.out.print(DELETE_INDEX_WHERE);
                    try {
                        deleteIndex = scanner.nextInt();  // try to assign this index
                    } catch (InputMismatchException e) {
                        deleteIndex = flights.size() + 1; // set to anything that is out of bounds.
                        scanner.next();  // clear input for next value
                        System.out.println("Only integer values between: 0 and " + (flights.size()-1) + " allowed.");   
                    }
                }
                deleteAtIndex(deleteIndex); // deleteIndex is within bounds and an integer.
            }
        // User canceled the whole dialog.
        } else {
            System.out.println("");
            System.out.println(CANCEL_ACTION);

        }
    }

    protected abstract void deleteWithId(String id);

    protected abstract void deleteAtIndex(int index);

    protected abstract void showItemIds();

    protected abstract void showItemIndexes();

}
