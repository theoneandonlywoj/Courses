/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arcyfelix
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        byte b = 5;
        short v = 5;
        int intValue = 7;
        long l = 23234254;

        float floatV = 45.9f;
        double d = 3425.324;

        // Assigning number sometimes require type transformation
        intValue = (int) l;
        System.out.println(intValue);

        d = intValue;

        // Casting a float number to integer will literally chop off everything after the floating point.
        intValue = (int) floatV;
        System.out.println(floatV);
        System.out.println(intValue);
        
        // If you want to round it, use Math.round.
    }

}
