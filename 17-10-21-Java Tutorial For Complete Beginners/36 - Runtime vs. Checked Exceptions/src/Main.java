
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
class DivisionByZero {

    public int perform() {
        int value = 7;
        value = value / 0;
        return value;
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Runtime Exception
        // Division that you don't need to handle:
        // Example -> Division by zero
        // It will not run for purpose.    
        DivisionByZero division;
        division = new DivisionByZero();
        division.perform();
        
        // Checked Exceptions
        // A checked exception is a type of exception that must be 
        // either caught or declared in the method in which it is thrown. 
        // For example, the java.io.IOException is a checked exception.
    }
}
