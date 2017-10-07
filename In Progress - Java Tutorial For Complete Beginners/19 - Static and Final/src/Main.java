class Thing {
	// Name is an instance variable
	public String name;
	// Description is a class variable, associated with the class
	public static String description;
	// Final variable cannot be reassign.
	// Thus, it need to have a value when created.
	public final static int LUCKY_NUMBER = 7;

	public void showName() {
		// Instance method can access the static data
		// System.out.prinlnt(description); would work
		System.out.println(name);
	}

	public static void showDescription() {
		System.out.println(description);
		// Static method cannot use non-static variable
		// System.out.println(name); would throw an error.
	}

	// Static can be use for example when calculating number of instances of the
	// class.

	// Initializing a variable 'count', which equals to 0.
	public static int count = 0;

	// Creating a constructor. That specific one will add 1 to 'count'
	public Thing() {
		count++;
	}

}

public class Main {

	public static void main(String[] args) {
		// Calling the static variable associated with the class
		Thing.description = "This is the description!";
		System.out.println(Thing.description);
		
		// Creating two instances of class 'Thing'.
		Thing thing1 = new Thing();
		Thing thing2 = new Thing();

		thing1.name = "Joe";
		thing2.name = "Julie";

		thing1.showName();
		thing1.showDescription();

		thing2.showName();
		thing2.showDescription();

		System.out.println(thing1.LUCKY_NUMBER);

		// Printing out the count. It will be equal to two independently of to which
		// object it belongs to. 
		// 'Count' is attached to the class, not instance of the class.
		System.out.println(thing1.count);
		System.out.println(thing2.count);

	}

}
