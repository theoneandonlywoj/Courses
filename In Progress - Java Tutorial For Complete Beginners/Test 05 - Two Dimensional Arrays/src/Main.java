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
Write an application that creates a two-dimensional array of Strings, 
with two rows and three columns. Print the value in the third row and third value. 

Hint: you can create an array of strings just like you created an array 
of floating point values above, but with text in quotes (e.g. "I'm a string") 
instead of floating-point numbers.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[][] multiDimArray = {
            {"Everythin", "is"},
            {"working", "fine!"},
            {"We", "are", "amazing!"}
        };

        System.out.println(multiDimArray[2][2]);

    }

}
