/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arcyfelix
 */
class Plant {

    public static final int ID = 7;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int calculateGrowthForecast() {
        return 7;
    }

    public String getData() {
        String data = "Some information" + calculateGrowthForecast();
        return data;
    }

}

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Succesfully compiled!");
    }

}
