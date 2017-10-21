
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        // Adding
        numbers.add(10);
        numbers.add(100);
        numbers.add(40);

        // Retrieving
        System.out.println(numbers.get(0));

        // For loop iteration
        System.out.println("For loop iteration: ");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("i: " + i + " value: " + numbers.get(i));

        }
        System.out.println("");
        // Different iteration method
        System.out.println("Looping over the array ('list-style'): ");
        for (Integer value : numbers) {
            System.out.println("Value: " + value);
        }

        // Removing
        // This method is slow!
        // It will copy all the items like re-indexing.
        // This issue can be solved with LinkedArrayList
        numbers.remove(0);

        // List Interface
        List<String> list = new ArrayList<String>();
    }

}
