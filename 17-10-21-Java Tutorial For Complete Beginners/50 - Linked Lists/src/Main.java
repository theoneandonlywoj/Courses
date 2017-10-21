
import java.util.ArrayList;
import java.util.LinkedList;
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
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        LinkedList<Integer> linkedList = new LinkedList<Integer>();

        doTimings("ArrayList", arrayList);
        doTimings("LinkedList", linkedList);
    }

    // Use 'List<typeName>' to be allowed to input all types of Lists to the method.
    private static void doTimings(String type, List<Integer> list) {
        for (int i = 0; i < 1E5; i++) {
            list.add(i);
        }
        // Timer start
        long start = System.currentTimeMillis();
        // Add items at the end of the list
        for (int i = 0; i < 1E5; i++) {
            list.add(i);
        }
        // Timer stop
        long stop = System.currentTimeMillis();
        // Prompt 
        System.out.println("Time adding to the end of the list: " + (stop - start) + " ms for type: " + type);

        // Timer start
        long start2 = System.currentTimeMillis();
        // Add items at the end of the list
        for (int i = 0; i < 1E5; i++) {
            // Adding with starting from the index 0
            list.add(0, i);
        }
        // Timer stop
        long stop2 = System.currentTimeMillis();
        // Prompt 
        System.out.println("Time adding to the beginning of the list: " + (stop2 - start2) + " ms for type: " + type);
    }
    // When shifting of indexes is involved, use LinkedList. Otherwise ArrayList is possibly the better solution.
    // LinkedList stores references to the next and previous elements.
}
