
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
class Person {
    
    private int id;
    private String name;
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Person: id = " + id + ", name = " + name;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person person1 = new Person(7, "Bob");
        Person person2 = new Person(7, "Bob");
        // They point to two different objects, which are same semantically.
        System.out.println(person2 == person1);

        // Semantical comparison
        System.out.println(person1.equals(person2));
        System.out.println(person2.equals(person1));

        // Working with values
        // It will not work with Doubles
        Double d1 = 7.2;
        Double d2 = 7.2;
        
        System.out.println(d1 == d2);
        System.out.println(d1.equals(d2));

        // '==' is checking if it is pointing at the same objects
        // Don't use it to compare non-primitives.
        // Use '.equals()'
        String t1 = "Hello";
        String t2 = "Hello, hi".substring(0, 5);
        
        System.out.println(t1);
        System.out.println(t2);

        // They point at two different objects...
        System.out.println(t1 == t2);

        // ... but are same semantically
        System.out.println(t1.equals(t2));
        
    }
    
}
