package world;

public class Plant {
	// Public, generally bad practise.
	// Available from anywhere.
	public String name;

	// Acceptable
	// You can make the variable accessible from unchangeable (using final).
	public final static int id = 0;

	// Private = Will be accessible only within the class!
	private String privVariable;

	// Protected = Private-like, but can be accessed by the child classes.
	// Within the same class, always within the same package.
	protected String size;

	// No access specifier = package level visibility
	int height;
	
	// Constructor
	public Plant() {
		this.name = "Freddy";
		this.privVariable = "Something";
		this.size = "Medium";
	}

}
