
public class Main {

	public static void main(String[] args) {
		int value = 7;

		int[] values;
		// Allocating memory for three integers
		values = new int[3];
		// Default value for an array is 0
		System.out.println(values[0]);
		values[0] = 10;
		System.out.println(values[0]);

		values[0] = 10;
		values[1] = 20;
		values[2] = 30;

		// Iterating over the array
		System.out.println("Iterating over the array!");
		for (int i = 0; i < values.length; i++) {
			System.out.println(values[i]);
		}

		System.out.println("Iterating over another array!");
		// Defining an list
		int[] numbers = { 5, 6, 10 };
		for (int iter = 0; iter < numbers.length; iter++) {
			System.out.println(numbers[iter]);

		}
	}
}
