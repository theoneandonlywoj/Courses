
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 121121231418734658L;
    // Transient will not be serialized.
    private transient int id;
    private String name;
    private static int count;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Constructor invoked!");
    }

    @Override
    public String toString() {
        return "Person{" + "id = " + id + ", name = " + name + ", count = " + count + '}';
    }

}
