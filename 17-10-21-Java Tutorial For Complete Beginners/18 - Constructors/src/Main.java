class Machine {
	String name;
	// Constructor method must have same name as the class
	// Its name will be uppercase, however, classes normally are denoted with lower
	// case

	public Machine() {
		System.out.println("Constructor1 method running!");
		name = "Sara";
	}

	public Machine(String name) {
		this.name = name;
		System.out.println("Constructor2 method running!");
	}
}

public class Main {

	public static void main(String[] args) {
		// Constructor 1 testing!
		Machine m1 = new Machine();
		System.out.println(m1.name);

		// Constructor 2 testing!
		Machine m2 = new Machine("Wojciech");
		System.out.println(m2.name);
	}

}
