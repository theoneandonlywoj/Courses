class Person {
	// Instance variables
	String name;
	int age;
	String character;

	void introduction() {
		System.out.println("Hi! My name is " + name + " and I am " + age + " years old.");
	}

}

public class Main {

	public static void main(String[] args) {
		/*
		 * Classes can contain: 
		 - 1. Data 
		 - 2. Subroutines (methods)
		 */

		Person person1 = new Person();
		person1.name = "Joe";
		person1.age = 35;

		Person Sara = new Person();
		Sara.name = "Sara";
		Sara.age = 33;
		Sara.character = "lovely";
		Sara.introduction();

	}

}
