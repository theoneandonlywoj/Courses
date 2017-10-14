
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        // ---------------------------------------------------------------------
        // Write object to a file.
        // ---------------------------------------------------------------------

        System.out.println("Writing objects...");

        Person[] people = {new Person(7, "John"), new Person(42, "Wojciech"), new Person(13, "Sara")};
        // ArrayList
        ArrayList<Person> peopleList = new ArrayList<Person>(Arrays.asList(people));

        // FileOutputStream with error handling
        // That automatically closes FileOutputStream.
        try (FileOutputStream fileStream = new FileOutputStream("./files/people.ser"); ObjectOutputStream objectOutStream = new ObjectOutputStream(fileStream);) {

            objectOutStream.writeObject(people);

            // Writing the array list
            objectOutStream.writeObject(peopleList);

        } catch (FileNotFoundException ex) {
            System.out.println("Writing issue: " + ex);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Writing issue: " + ex);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ---------------------------------------------------------------------
        // Read file.
        // ---------------------------------------------------------------------
        System.out.println("Reading objects...");
        try {
            FileInputStream fi = new FileInputStream("./files/people.ser");
            ObjectInputStream ObjectInStream = new ObjectInputStream(fi);

            Person[] peopleRead = (Person[]) ObjectInStream.readObject();

            // Type erosion when deserializing
            ArrayList<Person> peopleListRead = (ArrayList<Person>) ObjectInStream.readObject();
            ObjectInStream.close();

            for (Person person : people) {
                System.out.println(person);
            }

            // Iterating over the ArrayList
            for (Person person : peopleListRead) {
                System.out.println("ArrayList:");
                System.out.println(person);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Reading issue: " + ex);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Reading issue: " + ex);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Issue finding class.");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
