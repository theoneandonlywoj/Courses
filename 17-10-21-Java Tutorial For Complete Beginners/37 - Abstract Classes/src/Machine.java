/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public abstract class Machine {
    // Machine is an abstract class, which represents common functionalities.
    // I will not be able to do: 
    // Machine m1 = new Machine();
    
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Methods can be abstract as well.
    // It can be handy to force all the class childs to handle.
    public abstract void start();
    public abstract void doStuff();
    public abstract void shutdown();
    
    public void run(){
        start();
        doStuff();
        shutdown();
    }

}
