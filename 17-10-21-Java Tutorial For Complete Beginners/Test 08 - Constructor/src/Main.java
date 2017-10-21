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
Modify the previous Car class so that it has an instance variable called name of type String. 
Add a constructor that accepts a string parameter and sets the car's name using this parameter. 
Add a getName() method that returns the car's name. 

Finally, modify the main application so that it sets the car's name via the constructor, 
then prints the cars name (retrieving it using getName()). 
 */
class Car {

    String name;

    public Car(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Car car1 = new Car("Jockie");
        System.out.println(car1.getName());
    }

}
