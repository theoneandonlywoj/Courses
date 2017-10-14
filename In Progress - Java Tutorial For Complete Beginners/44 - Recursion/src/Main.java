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
        // Factorial example
        // 4! = 4 * 3 * 2 * 1

        int f = factorial(5);
        System.out.println(f);

    }

    public static int factorial(int value) {
        if (value == 1) {
            return 1;
        }
        return factorial(value - 1) * value;
    }
}
