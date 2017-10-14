
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
class Temp implements AutoCloseable{

    @Override
    public void close() throws Exception {
        System.out.println("Closing!");    }
    }
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Temp temp = new Temp();
        try {
            temp.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // After Java 7, we can use try with resources.
        // .close will be automatically called.
        try(Temp temp2 = new Temp()){
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Example with files
        File file = new File("./files/text.txt");
        FileReader fr;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find the file: " + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Unable to read the file:" + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
