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
    public static final int DOG = 0;
    public static final int CAT = 1;
    public static final int MOUSE = 2;

    public static void main(String[] args) {
        // Example of what 'enum' is trying to replace.
        // Variables initialized above outside of the main class
        int animal = CAT;

        switch (animal) {
            case DOG:
                System.out.println("DOG");
                break;
            case CAT:
                System.out.println("CAT");
                break;
            case MOUSE:
                System.out.println("MOUSE");
                break;
        }

        // Enum implementation
        AnimalEnum animal2 = AnimalEnum.DOG;
        switch (animal2) {
            case DOG:
                System.out.println("DOG");
                break;
            case CAT:
                System.out.println("CAT");
                break;
            case MOUSE:
                System.out.println("MOUSE");
                break;
            default:
                throw new AssertionError(animal2.name());
        }

        // More advanced examples:
        // Using AnimalEnum...
        System.out.println(AnimalEnum.DOG instanceof AnimalEnum);
        System.out.println(AnimalEnum.DOG);
        // Predefined Java method to get the name of the "category"
        System.out.println(AnimalEnum.DOG.name());

        // Another example
        AnimalEnum animal3 = AnimalEnum.valueOf("CAT");
        System.out.println(animal3);

    }

}
