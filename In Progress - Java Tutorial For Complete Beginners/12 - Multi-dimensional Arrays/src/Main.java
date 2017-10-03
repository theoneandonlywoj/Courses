
public class Main {

	public static void main(String[] args) {
		int[][] multiDimArray = { { 1, 2 }, { 3, 4 }, { 5 } };

		System.out.println("The array:");
		for (int row = 0; row < multiDimArray.length; row++) {
			for (int col = 0; col < multiDimArray[row].length; col++) {
				System.out.print(multiDimArray[row][col] + "\t");
			}
			System.out.println();
		}

		// Initializing an array without knowing number of columns
		// Just leave it empty.

		int[][] emptyCols = new int[2][];
	}

}
