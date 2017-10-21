/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public class Car extends Machine {

    @Override
    public void start() {
        System.out.println("Car.start()");
    }

    @Override
    public void doStuff() {
        System.out.println("Car.doStuff()");
    }

    @Override
    public void shutdown() {
        System.out.println("Car.shutdown()");
    }

}
