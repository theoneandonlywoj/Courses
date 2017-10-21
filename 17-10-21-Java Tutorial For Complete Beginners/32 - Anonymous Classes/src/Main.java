/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
class Machine {

    public void start() {
        System.out.println("Starting machine ...");
    }
}

interface Plant {

    public void grow();
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Machine machine1 = new Machine() {
            @Override
            public void start() {
                System.out.println("Camera: snap!");
            }
        };

        // You cannot create a new object from an anonymous class.
        // It is a "one-shot" class.
        machine1.start();

        // You cannot intenciate an interface.
        // Plant p1 = new Plant(); would not work, but:
        Plant plant1 = new Plant() {

            public void grow() {
                System.out.println("Plant growing!");
            }
        };
        plant1.grow();
    }

}
