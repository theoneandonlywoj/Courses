/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arcyfelix
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Polymorphism, in simple words, means that you can use a child class 
        // everywhere where you could use the parent class.

        Plant p1 = new Plant();
        Tree t = new Tree();

        Plant p2 = p1;
        Plant p3 = t;

        p3.grow();

        t.shedLeaves();
        // p2 is of type Plant.
        // p2.shedLeaves(); will not work, since the method does not exist in the class.

        Tree t2 = new Tree();
        doGrow(t2);

    }

    public static void doGrow(Plant p) {
        p.grow();
    }
}
