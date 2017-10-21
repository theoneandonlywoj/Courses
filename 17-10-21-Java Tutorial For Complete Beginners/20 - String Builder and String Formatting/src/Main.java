
public class Main {

	public static void main(String[] args) {
		// Firstly, showing bad practise as an example.
		// That way we create new String each time we want to use '+=' operation, we
		// create new String and reassign it. Very inefficient.

		// Initializing an empty String
		String info = "";

		// Operations below result in creation of a new String and then a reassignment.
		info += "Hello,";
		info += " ";
		info += "I am a Data Scientist.";

		System.out.println(info);

		// The correct approach
		String info2 = "";
		StringBuilder sb2 = new StringBuilder(info2);

		sb2.append("Hello,");
		sb2.append(" ");
		sb2.append("My name is Wojciech.");

		// Get the String back with .toString();
		System.out.println(sb2.toString());

		// You can use the chain rule

		StringBuilder sb1 = new StringBuilder();
		sb1.append("Hi again, ").append(" ").append("That is the way to go!");
		String result = sb1.toString();
		System.out.println(result);
		
		
		// Formatting
		// \t = tab
		// \n = new line
		
		
		int myInt = 5;
		
		System.out.printf("myInt is equal to: %d, another integer is equal to %d.", myInt, 55);
		
		// Printing integers with certain width
		
		for(int i = 0; i < 20; i++) {
			System.out.printf("%2d: %s \n", i, "Some text here");
		}
		
		// Formatting floating point numbers
		
		// 2 decimal spaces
		System.out.printf("%.2f \n", 5.6789);
		
		// Width field
		
		System.out.printf("%5.2f \n", 345.6789);
	}

}
