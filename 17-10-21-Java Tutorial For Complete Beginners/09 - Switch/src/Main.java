import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Initializing the input
		Scanner input = new Scanner(System.in);
		// User prompt
		System.out.println("Please enter a command: ");
		// Getting the input
		String text = input.nextLine();

		// Switch
		switch (text) {
		
		case "start":
			System.out.println("Machine started!");
			break;
			
		case "stop":
			System.out.println("Machine stopped!");
			break;

		default:
			System.out.println("Command not recognized!");
		}
	}

}
