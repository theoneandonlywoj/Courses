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
Modify the previous program so that it uses a for loop to display all the values in the array, 
all on the same line, each value formatted to two decimal places and followed by a space. 

Hint: use System.out.printf. 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Double[] array = {0.5, 0.07, 7.7, 34.9, 26.5};

        for (int i = 0; i < array.length; i++) {
            System.out.printf("%.2f ", array[i]);
        }
    }

}
