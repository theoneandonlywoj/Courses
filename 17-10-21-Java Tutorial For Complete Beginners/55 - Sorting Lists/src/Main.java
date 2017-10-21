
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int len1 = o1.length();
        int len2 = o1.length();

        if (len1 > len2) {
            return 1;
        } else if (len1 < len2) {
            return -1;
        } else {
            return 0;
        }

    }

}

class ReverseAlphabeticalOrder implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return -(o1.compareTo(o2));
    }

}

class Person {

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> animals = new ArrayList<String>();
        animals.add("dog");
        animals.add("cat");
        animals.add("horse");
        animals.add("mouse");
        animals.add("rabbit");
        animals.add("alligator");

        // Sorting with "the natural order."s
        /*Collections.sort(animals);

        for (String animal : animals) {
            System.out.println(animal);
        }
         */
        // Different sorting 
        // String lenght comparison
        Collections.sort(animals, new StringComparator());
        System.out.println(animals);

        // Different sorting 
        // Reverse alphabetical order
        Collections.sort(animals, new ReverseAlphabeticalOrder());
        System.out.println(animals);

        // =====================================================================
        // Sorting arbitrary objects
        List<Person> people = new ArrayList<Person>();
        people.add(new Person(7, "Wojciech"));
        people.add(new Person(8, "Joe"));
        people.add(new Person(2, "Steve"));
        people.add(new Person(3, "Brian"));
        people.add(new Person(7, "Sara"));
        System.out.println("-------------------------------------------------");
        System.out.println("Original order:");
        System.out.println(people);

        // Alphabetical order (by the name)
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }

        });
        System.out.println("Alphabetical order:");
        System.out.println(people);

        // By the id, ascending
        Collections.sort(people, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                // If p1.id > p2.id return 1;
                if (p1.getId() > p2.getId()) {
                    return 1;
                } // If p1.id < p2.id return -1;
                else if (p1.getId() < p2.getId()) {
                    return -1;
                }
                // Otherwise return 0;
                return 0;
            }
        });
        System.out.println("By id, ascending:");
        System.out.println(people);

        // By the id, descending 
        Collections.sort(people, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {

                if (p1.getId() > p2.getId()) {
                    return -1;
                } else if (p1.getId() < p2.getId()) {
                    return 1;
                }

                return 0;
            }
        });
        System.out.println("By id, descending:");
        System.out.println(people);

    }

}
