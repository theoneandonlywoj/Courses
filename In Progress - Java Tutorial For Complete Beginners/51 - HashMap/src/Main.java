
import java.util.HashMap;
import java.util.Map;

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
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(5, "Five");
        hashMap.put(2, "Two");
        hashMap.put(8, "Eight");
        hashMap.put(7, "Seven");

        String text = hashMap.get(7);
        System.out.println(text);

        // If the value does not exist it will return 'null'
        System.out.println(hashMap.get(0));
        // You can have duplicate values, but not indexes.
        // Putting a value with an already existing index will result
        // in overwriting previous record.

        System.out.println("Iterating over the HashMap: ");
        // Iterating over all values
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
        // HashMap does not maintain order!
    }
}
