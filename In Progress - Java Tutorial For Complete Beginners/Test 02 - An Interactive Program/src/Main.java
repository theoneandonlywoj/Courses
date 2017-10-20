
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    /* 
    Task:
    Create a program that asks the user to enter an integer. 
    If the integer is less than 10, print the message "This number is too small". 
    If the integer is greater than or equal to 10, print "This number is big enough". 
    
    Hint: use the Scanner class with an if ... else statement. 
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter an integer: ");

        int myInt = input.nextInt();

        System.out.println("You entered: " + myInt);

        if (myInt < 10) {
            System.out.println("This number is too small!");
        } else {
            System.out.println("This number is big enough!");
        }
    }

}
