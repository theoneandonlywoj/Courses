import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Creating user input 
		// CRTL + SHIFT + O to add all imports!
		
		// Create scanner input
		Scanner input = new Scanner(System.in);
		
		// Ask for an input
		System.out.println("Input something: ");
		
		// Wait for a response
		String line = input.nextLine();
		
		// Prompt the response
		System.out.println("You entered: " + line);
		
		// --------------------------------------
		// Getting input for numbers etc
		
		// Create scanner input
		Scanner inputInt = new Scanner(System.in);
		
		// Ask for an input
		System.out.println("Input an integer: ");
		
		// Wait for a response
		int inputInteger = inputInt.nextInt();
		
		// Prompt the response
		System.out.println("You entered: " + inputInteger);

	}

}
