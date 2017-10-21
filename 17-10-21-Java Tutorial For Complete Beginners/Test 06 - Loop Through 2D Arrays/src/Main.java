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
    public static void main(String[] args) {
        String[][] multiDimArray = {
            {"Everything", "is"},
            {"working", "fine!"},
            {"We", "are", "amazing!", "Yupi!"}
        };
        for (int row = 0; row < multiDimArray.length; row++) {
            for (int column = 0; column < multiDimArray[row].length; column++) {
                System.out.println(multiDimArray[row][column]);
            }
        }
     }

}
