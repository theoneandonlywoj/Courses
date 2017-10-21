
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
        FileWriter fr;
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write("This is line 1.");
            br.newLine();
            br.write("This is line 2.");
            br.newLine();
            br.write("This is line 3.");
        } catch (IOException ex) {
            System.out.println("Unable to write the file:" + file.toString());
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Success!");

    }

}
