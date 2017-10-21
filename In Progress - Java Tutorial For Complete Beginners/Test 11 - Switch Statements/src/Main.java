
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
Write a program that asks the user to enter an integer. 
If the user enters '1', print "Got 1". If the user enters '66', print "Got 66". 
If the user enters something other than these two numbers, print "Got something else". 

The program should use a switch statement. 

Hint: you may need to look up switch statements on Google. 
Use the default clause to implement the case where the user doesn't enter '1' or '66'. 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an integer:");

        int value = scanner.nextInt();

        switch (value) {
            case 1:
                System.out.println("Got 1!");
                break;
            case 66:
                System.out.println("Got 66!");
                break;
            default:
                System.out.println("Got something else!");

        }

    }

}
