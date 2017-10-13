
import java.io.IOException;
import java.text.ParseException;
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
        Test test = new Test();

        /*
        Method 1
        try {
        test.run();
        } catch (IOException ex) {
        System.out.println("IO exception!");
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
        System.out.println("Parse exception!");
        //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        // Method 2, Multi exceptions
        /*
        try {
            test.run();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        // Method 3, All exception - Exception polimorphism, be careful!
        try {
            test.run();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
