/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public class Camera extends Machine {

    @Override
    public void start() {
        System.out.println("Camera.start()");
    }

    @Override
    public void doStuff() {
        System.out.println("Camera.doStuff()");
    }

    @Override
    public void shutdown() {
        System.out.println("Camera.shutdown()");
    }

}
