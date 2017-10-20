/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
/*
Task:
First, create a main program as in the first exercise. 
Next, define a new class in its own file. Call the class Car. 
Give it a single method called "start". Make the method simply print "Car started!". 
In your main program, create a new Car object and call its start() method. 
Your final program should simply therefore display the text "Car started!". 
*/
class Car{
    public static void start(){
        System.out.println("Car started!");
}
}
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.start();
    }

}
