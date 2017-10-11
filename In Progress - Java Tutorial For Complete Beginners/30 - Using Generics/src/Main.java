
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arcyfelix
 */
class SomeClass{
    
}
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // A generic class is a class that can work with multiple objects 
        // and you decide which object to use when you intiaciate the class.
        
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("dog");
        strings.add("cat");
        strings.add("horse");
        String animal = strings.get(0);
        System.out.println(animal);
        
        // You can have generic classes with multiple type variables.
        // Type
        // A short example:
        
        HashMap<Integer, String> multipleParameters = new HashMap<Integer, String>(); 
        
        
        // Type of the object can be non-standard
        ArrayList<SomeClass> someClassArray = new ArrayList<SomeClass>();
    }

}
