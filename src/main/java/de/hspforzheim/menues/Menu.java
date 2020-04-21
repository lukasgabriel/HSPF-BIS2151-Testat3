/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspforzheim.menues;

import de.hspforzheim.Eaf;
import de.hspforzheim.commands.MenuOperation;
import de.hspforzheim.commands.OperationType;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cedric Jansen
 */
public class Menu {
    
    
    protected static final int YEAR_INT = Calendar.getInstance().get(Calendar.YEAR);
    
    protected final String YEAR_STRING = String.valueOf(YEAR_INT);
    protected final String HEADER = "\nEAF: EAT&FLY-Management";
    protected final String VERSION = "1.0";
    protected final int GROUP = 1;
    
    protected String title;
    protected String subTitle;
    protected String enterChoice;
    protected int userInput;
    
    private final MenuOperation exitProgram = new MenuOperation(0, "Program exit");
    private final MenuOperation manageFlights = new MenuOperation(1, "Managing flights");
    private final MenuOperation manageDishes = new MenuOperation(2,  "Managing dishes");
     
    private final MenuOperation[] menuOperations = new MenuOperation[] {manageFlights, manageDishes, exitProgram};
    
    public Menu() {
         this.title = "MAIN MENU";
         this.subTitle = "Please select:\n";
         this.enterChoice = "\nPlease enter your choice: ";
    }
    
    public void start() {
        while(true) {
            printFullHeader();
            printSubHeader();
            printCommands();
            printChoiceQuestion();
            userInput();
            handleUserInput();
        }  
    }
    
    
    
    
    protected void handleUserInput( ) {
        switch(userInput) {
            case 0:
                System.exit(0);
                break;
            case 1:
                FlightMenu flightMenu = Eaf.getFlightMenu();
                flightMenu.start();
                break;
            case 2:
                DishMenu dishMenu = Eaf.getDishMenu();
                dishMenu.start();
                break;
            default:
                System.out.println("Any other key");
                break;
        }
    }
    
    
    protected void userInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isInputOk;
        try {
            userInput = scanner.nextInt();
        } catch( InputMismatchException e) {
            userInput = -1;
        }
    }
    
    
    protected void printCommands() {
        for(MenuOperation op : menuOperations) {
            if(op.getKey() == 0) {
                System.out.printf("\n%-18s%6s\n", op.getConsoleName(), op.getKey());
            }
            else {
                 System.out.printf("%-18s%6s\n", op.getConsoleName(), op.getKey());
            }
           
        }
    }
    
    protected void printChoiceQuestion() {
        System.out.print(enterChoice + " ");
    }
    
    protected void printSubHeader() {
        System.out.println(title);
        System.out.println(subTitle);
    }
    
    
    protected void printFullHeader() {
        StringBuilder stringBuilder = new StringBuilder(HEADER);
        String result;
        
        stringBuilder.append(" (Version ")
                .append(VERSION)
                .append(" (C)")
                .append(" ")
                .append(YEAR_STRING)
                .append(" by Group ")
                .append(String.valueOf(GROUP))
                .append(")\n");
        
        result = stringBuilder.toString();
        System.out.println(result);
    }
    
    
    protected void printMenuTitle() {
        System.out.println(title);
    }
    
    protected void printSubTitle() {
        System.out.println(subTitle);
    }
}
