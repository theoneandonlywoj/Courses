
import java.util.Iterator;
import java.util.LinkedList;

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
        LinkedList<String> animals = new LinkedList<String>();

        animals.add("Fox");
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Rabbit");
        animals.add("Bear");

        // Legacy iteration, still very useful
        System.out.println("Legacy iteration:");
        Iterator iter = animals.iterator();

        while (iter.hasNext()) {
            String animal = (String) iter.next();
            System.out.println(animal);

            // Very useful!
            if (animal.equals("Cat")) {
                iter.remove();
            }
        }

        System.out.println("");

        // Modern iteration, Java 5 and later
        System.out.println("Modern iteration:");
        for (String animal2 : animals) {
            System.out.println(animal2);
        }
    }

}
