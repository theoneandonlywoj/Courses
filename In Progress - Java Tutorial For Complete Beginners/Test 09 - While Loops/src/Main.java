
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
/*
Task:
Write an application that asks the user to enter the number '5' 
and loops over and over until '5' is entered. 

When 5 is finally entered, print "Got it!". 

Hint: the application structure will look like this: 

 - declare a variable and set it equal to 0 (for example) 
 - create a new Scanner object so that we can use it to get input later on. 
 - begin a while loop which loops until the variable is set equal to 5. 
 - ask the user to enter '5' 
 - store the user input in your variable 
 - end the while loop 
 - print "Got it!" 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int myNumber = 0;
        Scanner scanner = new Scanner(System.in);
        
        while (myNumber != 5) {
            System.out.println("Please enter 5:");
            myNumber = scanner.nextInt();

        }
        System.out.println("Got it!");
    }

}
