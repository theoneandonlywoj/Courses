
import java.util.ArrayList;

class Machine {

    @Override
    public String toString() {
        return "I am a machine";
    }

    public void start() {
        System.out.println("Machine starting.");
    }

}

class Camera extends Machine {

    @Override
    public String toString() {
        return "I am a camera";
    }

    public void snap() {
        System.out.println("snap!");
    }
}

public class Main {

    public static void main(String[] args) {

        ArrayList<Machine> list1 = new ArrayList<Machine>();

        list1.add(new Machine());
        list1.add(new Machine());

        ArrayList<Camera> list2 = new ArrayList<Camera>();

        list2.add(new Camera());
        list2.add(new Camera());

        showList(list2);
        showList2(list1);
        showList3(list1);
    }

    // Wildcard
    // Input as an ArrayList of unknown type.
    // Wildcard means you can pass an ArrayList (in this example) 
    // with any kind of type parameter.
    // You need to treat the parameter as of type Object
    // Thus, you cannot use method specific for either class Machine or Camera.
    public static void showList3(ArrayList<?> list) {
        for (Object value : list) {
            System.out.println(value);
        }
    }

    // Specifing bounds of the wildcard
    // ArrayList of Machine or a subtype of Machine.
    public static void showList(ArrayList<? extends Machine> list) {
        // Now I can iterate over ArrayList of type Machine.
        // However, I cannot use method 'snap' from class Camera.
        for (Machine value : list) {
            System.out.println(value);
            value.start();
        }

    }

    // Still cannot use the method 'snap'
    // Iterating over an ArrayList of type Camera or its super class.
    public static void showList2(ArrayList<? super Camera> list) {
        for (Object value : list) {
            System.out.println(value);
        }
    }

}
