
public class Main {

	public static void main(String[] args) {
		// Defininig an array of strings - Method 1
		// Allocating memory for references for three strings
		String[] words1 = new String[3];
		
		words1[0] = "Hello";
		words1[1] = "my dearest";
		words1[2] = "friend!";
		
		System.out.println("----------");
		System.out.println("Method 1");
		System.out.println("----------");
		
		for(int i = 0; i < words1.length; i++) {
			System.out.println(words1[i]);
		}
		
		// Defininig an array of strings - Method 2
		String[] words2 = {"Hello", "my dearest", "friend!"};
		
		System.out.println("----------");
		System.out.println("Method 2");
		System.out.println("----------");
		
		// Another way of iterating over an array of strings
		for(String word: words2) {
			System.out.println(word);
		}
	}

}
