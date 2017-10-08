import world.Plant;

public class Main {

	public static void main(String[] args) {
		// Public, it can be accessed from anywhere:
		Plant p = new Plant();
		System.out.println(p.name);
		p.name = "Jose";
		System.out.println(p.name);

		// Look up 'Plant' for other types.
		/* Summary:
		 * Private = only within the class
		 * Public = from anywhere
		 * Protected = from same class, subclass, but must be in the same package
		 * No modifier = within the package
		 */
		
		// Package level
		// System.out.println(p.height); won't work.
	}

}
