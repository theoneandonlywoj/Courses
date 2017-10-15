
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        Person person1 = new Person(7, "John");
        // Variable count is static final, there is no reason to serialize it.
        // Setting 'count' for all objects of type Person.
        Person.setCount(26);

        System.out.println(person1);

        // FileOutputStream with error handling
        // That automatically closes FileOutputStream.
        try (FileOutputStream fileStream = new FileOutputStream("./files/people.bin")) {
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileStream);

            objectOutStream.writeObject(person1);

            // Closing the ObjectOutputStream instance
            objectOutStream.close();
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
        // When serialized, we obtain a new object without invoking a constructor.
        System.out.println("Reading objects...");
        try {
            FileInputStream fi = new FileInputStream("./files/people.bin");
            ObjectInputStream ObjectInStream = new ObjectInputStream(fi);

            Person p1 = (Person) ObjectInStream.readObject();

            ObjectInStream.close();

            System.out.println(p1);
            ;

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
