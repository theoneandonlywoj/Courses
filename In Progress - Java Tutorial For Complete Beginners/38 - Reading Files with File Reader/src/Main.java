
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("./files/text.txt");
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            
            String line;
            
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Missing file: " + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Parsing error! File: " + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            System.out.println("Unable to close file! File: " + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex){
            // Handling NullPointerException.
        }
    }

}
