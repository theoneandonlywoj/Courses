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
        Robot robot1 = new Robot(7);
        robot1.start();

        // Additional approach:
        Robot.Arm arm = robot1.new Arm();
        arm.move();

        // Static class
        Robot.Battery battery1 = new Robot.Battery();
        battery1.charge();

    }

}
