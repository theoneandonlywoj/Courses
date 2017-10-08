package world;

public class Oak extends Plant {
	public Oak() {
		// Variable 'privVariable' will not be accessible here! It is private
		
		// Protected variable is accessible here.
		this.size = "Large";
	}

}
