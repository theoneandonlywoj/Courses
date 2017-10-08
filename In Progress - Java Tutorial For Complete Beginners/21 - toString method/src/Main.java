class Frog {
	private int id;
	private String name;

	// Constructor
	public Frog(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {

		return String.format("%4d : %s", id, name);
	}

}

public class Main {

	public static void main(String[] args) {
		Frog frog1 = new Frog(7, "Freddy");
		System.out.println(frog1);
		// When printing out an object, System.out.println will look for
		// object.toString() method. If the method does not exist, it will return name
		// of the class and a hashcode as follows: className@hashCode.

	}

}
