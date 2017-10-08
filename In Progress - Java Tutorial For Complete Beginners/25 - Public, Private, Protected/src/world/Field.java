package world;
public class Field {
	private Plant p = new Plant();
	
	public Field() {
		// Variable 'size' is protected and class 'Field' is in the same package as 'Plant'.
		System.out.println(p.size);
	}
}
