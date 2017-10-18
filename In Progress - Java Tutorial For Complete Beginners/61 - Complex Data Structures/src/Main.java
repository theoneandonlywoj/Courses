
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
    public static String[] vehicles = {
        "Ambulance", "Helicopter", "Lifeboat"
    };

    public static String[][] drivers = {
        {"Fred", "Sue", "John"},
        {"Sara", "Rose", "Bob", "Wojciech"},
        {"Maria", "Stan", "Louis"}

    };

    public static void main(String[] args) {
        // For the emergency vehicle order does not matter.
        // We just want to iterate through the vehicles.
        // Thus, we use the most HashMap
        Map<String, Set<String>> personnel = new HashMap<String, Set<String>>();

        // Iterate through the vehicles
        for (int i = 0; i < vehicles.length; i++) {
            String vehicle = vehicles[i];
            String[] driversRow = drivers[i];

            // We want the drivers to be in order.
            // Not the alphabetical order, because we want to keep the original order.
            // Thus, we will use LinkedHashSet
            // It will remove duplicates since it is a set.
            Set<String> driverSet = new LinkedHashSet<String>();
            
            for (String driver: driversRow){
                driverSet.add(driver);
            }
            
            // Adding driverRow to the map
            
            personnel.put(vehicle, driverSet);
            

        }
        
        Set<String> driversRow = personnel.get("Helicopter");

            for (String driver : driversRow) {
                System.out.println(driver);
            }
            
        // Everything
        System.out.println("");
        System.out.println("Assignments:");
        // Iterate through the set of keys
        for (String vehicle : personnel.keySet()) {
            System.out.print(vehicle);
            System.out.print(": ");
            Set<String> driversRowTemp = personnel.get(vehicle);

            for (String driver : driversRowTemp) {
                System.out.print(driver);
                System.out.print(" ");
            }
            
            System.out.println();
        }

    }

}
