
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
class Person implements Comparable<Person> {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // I cannot add the object to the TreeSet till they have defined the natural order
    @Override
    public int compareTo(Person otherPerson) {
        // Alphabetical order using String
        // int result = this.name.compareTo(otherPerson.name);
        // Reverse alphabetical order using String.
        // int result = - this.name.compareTo(otherPerson.name);

        int len1 = this.name.length();
        int len2 = otherPerson.name.length();
        int result;
        if (len1 > len2) {
            result = 1;
        } else if (len1 > len2) {
            result = -1;
        } else {
            // This will return zero only when the two names are exactly equal.
            // If they have the same lenght, it will put them
            // in the alphabetical order.
            result = name.compareTo(otherPerson.name);
        }

        return result;
    }

    // I cannot add the object to a set before defining .equals and .hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        // Basic example for objects of type 'String'
        List<String> list = new ArrayList<String>();
        SortedSet<String> set = new TreeSet<String>();

        addElement(list);
        addElement(set);

        showElements(list);
        showElements(set);

        Collections.sort(list);

        // Artificial object type (type 'Person')
        System.out.println("");
        List<Person> peopleList = new ArrayList<Person>();
        SortedSet<Person> peopleSet = new TreeSet<Person>();

        addPerson(peopleList);
        addPerson(peopleSet);

        showPeople(peopleList);
        showPeople(peopleSet);

        Collections.sort(peopleList);

    }

    private static void addElement(Collection<String> col) {
        col.add("Joe");
        col.add("Peter");
        col.add("Mike");
        col.add("Wojciech");
    }

    private static void showElements(Collection<String> col) {
        System.out.println(col);
    }

    private static void addPerson(Collection<Person> people) {
        people.add(new Person("Joe"));
        people.add(new Person("Wojciech"));
        people.add(new Person("Sara"));
        people.add(new Person("Sue"));

    }

    private static void showPeople(Collection<Person> people) {
        System.out.println(people);
    }

}
