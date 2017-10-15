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
        // In Java there is only 'passing by value'.

        Main main = new Main();

        int myInt = 7;
        System.out.println("1. Value is: " + myInt);

        main.show(myInt);

        System.out.println("4. Value is: " + myInt);

        // =====================================================================
        System.out.println("===================");
        Person person = new Person("John");
        System.out.println("1. Value is: " + person);

        main.show(person);

        System.out.println("4. Value is: " + person);

    }

    public void show(int myInt) {
        // myInt defined in the method is not same as myInt in the main method.
        // Variables have their corresponsing scopes in their methods
        // between the {} brackets.
        System.out.println("2. Value is: " + myInt);
        myInt = 8;
        System.out.println("3. Value is: " + myInt);
    }

    public void show(Person person) {
        System.out.println("2. Value is: " + person);
        // Object 'person at this point refers to Bob
        person.setName("Sue");

        // After next line, object 'person' will refer to Wojciech within limits
        // of the method.
        person = new Person("Wojciech");
        System.out.println("3. Value is: " + person);
    }

}
