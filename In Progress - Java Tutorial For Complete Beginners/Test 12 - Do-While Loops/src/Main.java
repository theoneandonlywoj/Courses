
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
Test:
A while loop checks its condition before the first iteration of the loop. 
A do...while loop checks the condition at the end of the loop. 
This means there's always at least one iteration of the loop. 

Write a program that asks the user to enter an integer, then gets the input from the user. 
The program should repeatedly ask the user to enter an integer 
until the user enters an integer greater than 10; 
then it should print "Integer greater than 10 detected!" and should end. 

The program must contain only two print statements! 

Hint: use a do...while loop to enclose the 'prompt' 
(i.e. the text that asks the user to enter the integer) and the bit that gets the user input. 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        do {
            System.out.println("Enter an integer:");
            value = scanner.nextInt();

        } while (value <= 10);
        System.out.println("Your integer is greater than 10! Congratulations!");
    }

}
