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

        Camera cam1 = new Camera();
        cam1.setId(7);

        Car car1 = new Car();
        car1.setId(77);

        // Since class 'Machine' is abstract, I cannot instantiate it.
        // Machine m1 = new Machine(); won't work!
        // Forcing child classes to run certain methods.
        cam1.run();
        car1.run();

    }

}
