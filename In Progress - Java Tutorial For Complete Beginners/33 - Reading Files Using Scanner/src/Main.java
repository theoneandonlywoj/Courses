
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "./files/example.txt";
        File textFile = new File(filePath);
        
        Scanner in = new Scanner(textFile);
        while(in.hasNextLine()){
            String line = in.nextLine();
            System.out.println(line);
        }
        in.close();
    }

}
