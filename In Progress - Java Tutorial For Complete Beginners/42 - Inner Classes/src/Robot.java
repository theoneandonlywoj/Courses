/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public class Robot {

    private int id;

    public Robot(int id) {
        this.id = id;
    }

    public void start() {
        System.out.println("Starting robot: " + this.id);
        Brain brain = new Brain();
        brain.think();
        final String myName = "Robert";
        // You can declare classes inside methods.
        class Temp {

            public void showId() {
                // Showing access to the variables:
                System.out.println("Id is: " + id);
                System.out.println("My name is: " + myName);

            }
        }

        Temp temp1 = new Temp();
        temp1.showId();
    }

    // Example of a non-static, inner class, oftenly called nested class.
    private class Brain {

        // This class access to data instances inside the Robot class
        public void think() {
            System.out.println("Robot " + id + " is thinking.");
        }
    }

    public static class Battery {

        // Generally, non-static inner classes are created 
        // when you need to group certain functionality.
        // They are not associated with the outher class, just grouped.
        // This class is static. Thus I cannot access the variable 'id'.
        // In order to do that I would need to declare the variable static.
        public void charge() {
            System.out.println("Battery charging...");
        }
    }

    public class Arm {

        public void move() {
            System.out.println("Robot " + id + " is moving its arm.");

        }
    }

}
