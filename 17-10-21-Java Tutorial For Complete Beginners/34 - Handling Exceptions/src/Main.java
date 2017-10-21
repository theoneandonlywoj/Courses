
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        // File 'text.txt' is missing on purpose.
        File file = new File("text.txt");

        // Try - Catch
        try {
            FileReader fr = new FileReader(file);
            // If the exception was thrown in the line above, 
            // the line below would not be executed.
            System.out.println("No exceptions!");
        } catch (FileNotFoundException ex) {
            System.out.println("Error! File " + file.toString() + " missing!");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
