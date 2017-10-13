
import java.io.IOException;
import java.text.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public class Test {
    public void run() throws IOException, ParseException{
        //throw new IOException();
        
        throw new ParseException("Erorr in command list.", 2);
    }
}
