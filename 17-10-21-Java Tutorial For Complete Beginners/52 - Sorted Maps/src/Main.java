
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
        // ALl the objects can be initialized as Map or as a specific type
        // f.e. HashMap or TreeMap.

        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();

        // HashMap does not guarantee any specific order.
        System.out.println("HashMap:");
        testMap(hashMap);

        // LinkedHashMap maintains the order the values were put in.
        System.out.println("LinkedHashMap:");
        testMap(linkedHashMap);

        // TreeMap sorts the key in "the natural order".
        System.out.println("TreeMap:");
        testMap(treeMap);

    }

    public static void testMap(Map<Integer, String> map) {
        map.put(9, "fox");
        map.put(4, "swan");
        map.put(8, "dog");
        map.put(1, "horse");
        map.put(19, "snake");
        map.put(5, "cat");
        map.put(7, "bear");

        for (Integer key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + ": " + value);
        }
        // Empty line for better visualization:
        System.out.println("");
    }

}
