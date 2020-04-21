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
public abstract class Manager {

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

    protected void delete() {
        ArrayList<Flight> flights = Item.getAllFlights();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String secondInput = "";
        String thirdInput = "";
        String deleteId = "";
        int deleteIndex = -1;
        boolean showId = false;

        System.out.println("");
        System.out.println(DELETE_HEAD);
        if(flights.isEmpty() ) {
            System.out.println(NO_ITEMS);
            return;
        }

        while ((!input.equals("ID")) && (!input.equals("IN")) && (!input.equals("X"))) {
            System.out.println(DELETE_STRING1);
            System.out.println(DELETE_STRING2);

            try {
                input = scanner.nextLine();
            } catch (InputMismatchException e) {
                input = "";
            }

            input = input.trim();
            input = input.toUpperCase();

        }

        if (input.equals("IN") || input.equals("ID")) {

            if (input.equals("IN")) {
                System.out.println(SHOW_INDEX1);
            } else {
                System.out.println(SHOW_ID1);
                showId = true;
            }
            System.out.println(YES_NO_CONFIRMATION);
            while ((!secondInput.equals("Y")) && (!secondInput.equals("N"))) {

                try {
                    secondInput = scanner.nextLine();
                } catch (InputMismatchException e) {
                    secondInput = "";
                }

                secondInput = secondInput.trim();
                secondInput = secondInput.toUpperCase();
            }

            if (secondInput.equals("Y")) {
                if (showId) {
                    showItemIds();
                } else {
                    showItemIndexes();
                }
            }

            if (showId) {
                while (deleteId.length() <= 0) {
                    System.out.print(DELETE_ID_WHERE);
                    try {
                        deleteId = scanner.nextLine();
                    } catch (InputMismatchException e) {
                        deleteId = "";
                    }

                    deleteId = deleteId.trim();

                }
                deleteWithId(deleteId);
            } else {  
                while(deleteIndex < 0 || deleteIndex >= flights.size()) {
                    
                    System.out.print(DELETE_INDEX_WHERE);
                    try {
                        deleteIndex = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        deleteIndex = flights.size() + 1;
                        scanner.next();
                        System.out.println("Only integer values between: 0 and " + (flights.size()-1) + " allowed.");   
                    }
                }
                deleteAtIndex(deleteIndex);
            }

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
