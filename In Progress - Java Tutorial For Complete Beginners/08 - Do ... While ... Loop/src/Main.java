import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Initializing a scanner
		Scanner scanner = new Scanner(System.in);
		
		/* 
		 * A do-while loop will run atleast once since the condition 
		 * is tested at the end of the loop.
		 * 
		 * Declare the variable outside the curly brackets.
		 */
		int value;
		do {
			// Prompt user info
			System.out.println("Enter an integer: ");
			
			// Read the integer
			value = scanner.nextInt();
				
		}
		while(value != 5);
		
		
	}

}
