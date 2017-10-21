/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojciech Orzechowski
 */
public enum AnimalEnum {
    // CAT, DOG and MOUSE are objects of class AnimalEnum
    CAT("Kitty"), DOG("Doggie"), MOUSE("Jerry");

    private String name;

    private AnimalEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "This animal is called: " + name;
    }

}
