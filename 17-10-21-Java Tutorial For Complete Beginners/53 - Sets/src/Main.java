
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

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
        // A set is like a collection that stores only unique elements.
        Set<String> set1 = new HashSet<String>();
        set1.add("Dog");
        set1.add("Cat");
        set1.add("Bear");
        set1.add("Mouse");
        set1.add("Snake");

        // Adding duplicate items does nothing (unlike lists!
        set1.add("Mouse");
        set1.add("Snake");

        // Outputting the set
        System.out.println("HashSet:");
        System.out.println(set1);

        // HashSet does not mantain the same order!
        // It might rearrange the values every run!
        // LinkedHashSet remembers the order.
        System.out.println("LinkedHashSet:");
        Set<String> set2 = new LinkedHashSet<String>();
        set2.add("Dog");
        set2.add("Cat");
        set2.add("Bear");
        set2.add("Mouse");
        set2.add("Snake");

        System.out.println(set2);

        // TreeSet
        // For String it will put values in the alphabetic order
        Set<String> set3 = new TreeSet<String>();
        set3.add("Dog");
        set3.add("Cat");
        set3.add("Bear");
        set3.add("Mouse");
        set3.add("Snake");

        System.out.println("TreeSet:");
        System.out.println(set3);

        // Iteration over the set:
        System.out.println("");
        System.out.println("Iterating over the TreeSet");
        for (String element : set3) {
            System.out.println(element);
        }

        // Checking if the set contain of a specific object
        if (set3.contains("Dog")) {
            System.out.println("Contains Dog!");
        }

        // Checking if the set is empty:
        Set<String> set4 = new HashSet<String>();

        if (set4.isEmpty()) {
            System.out.println("Set4 is empty");
        }
        // =====================================================================
        // Intersection
        Set comparisonSet1 = new TreeSet<String>();
        comparisonSet1.add("Dog");
        comparisonSet1.add("Cat");
        comparisonSet1.add("Horse");

        Set comparisonSet2 = new TreeSet<String>();
        comparisonSet2.add("Cat");
        comparisonSet2.add("Horse");
        comparisonSet2.add("Monkey");

        // Creating copy of interSet1
        Set<String> intersection = new HashSet<String>(comparisonSet1);
        // Keeping all elements that are both in set 'intersection' (copy of interSet1)
        // and interSet2.
        intersection.retainAll(comparisonSet2);
        System.out.println("Common elements:");
        System.out.println(intersection);

        // =====================================================================
        // Difference (all that are in the comparisonSet1, but not in comparisonSet2
        Set<String> difference = new HashSet<String>(comparisonSet1);
        difference.removeAll(comparisonSet2);

        System.out.println("Unique elements for comparisonSet1:");
        System.out.println(difference);

    }

}
