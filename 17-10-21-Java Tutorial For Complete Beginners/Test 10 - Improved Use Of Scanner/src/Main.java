
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
The previous program crashes if a user enters something other than a number. 
The problem is that we use the nextInt() method of Scanner, assuming blindly that we will get an integer. 

Modify the program so that no input can crash it. 

Hint: you will need the hasNextInt() method of Scanner. 
You will also need the nextLine() method so that you can get a line from the user 
if they don't enter an integer. 

You'll need to use an if...else statement that detects if the user enters an int, 
and simply gets a line (rather than an int) if he doesn't. 
Don't be afraid to consult the API docs for the Scanner class; 
type "java api scanner" into Google (without the quotes). 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        int value = 0;

        while (value != 5) {
            System.out.println("Enter the number 5:");

            // Checking compability
            if (scanner1.hasNextInt()) {
                value = scanner1.nextInt();
            } else {
                scanner1.nextLine();
            }

        }
        System.out.println("Got it!");
    }

}
