/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arcyfelix
 */
class Machine {

    public void start() {
        System.out.println("Machine started!");
    }
}

class Camera extends Machine {

    public void start() {
        System.out.println("Camera started!");
    }

    public void snap() {
        System.out.println("Foto taken!");
    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Machine machine1 = new Machine();
        Camera camera1 = new Camera();

        machine1.start();
        camera1.start();
        camera1.snap();

        // Upcasting
        Machine machine2 = camera1;
        System.out.println("Upcasting: Machine2.start():");

        machine2.start();
        // machine2.snap() would not work
        // variable machine2 will allow to use only methods in class Machine

        // Downcasting
        System.out.println("Downcasting: Camera.start():");
        Machine machine3 = new Camera();
        Camera camera2 = (Camera) machine3;
        camera2.start();
        // Now I will be able to use method 'snap'.
        camera2.snap();

        // Upcasting is safe.
        // Downcasting is inherently unsafe.
        // Be careful using it!
        // Example (RunTime Error)
        /*
        
        Machine machine4 = new Machine();
        Camera camera3 = (Camera)machine4;
        camera3.start();
        
         */
    }

}
