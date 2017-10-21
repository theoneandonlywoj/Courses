class Person {
	String name;
	int age;
	
	void speak() {
		System.out.println("My name is: " + name);
	}
	
	int calculateYearsToRetirement() {
		int yearsLeftAtWork = 65 - age;
		
		return yearsLeftAtWork;
	}
	
	int getAge() {
		return age;
	}
	
	String getName() {
		return name;
	}
	
}
public class Main {

	public static void main(String[] args) {
		
		Person person1 = new Person();
		
		person1.name = "Joe";
		person1.age = 25;
		
		person1.speak();
		
		int retirement = person1.calculateYearsToRetirement();
		System.out.println("Years to retirement: " + retirement);

		int age = person1.getAge();
		System.out.println("Age: " + age);
		
		String name = person1.getName();
		System.out.println("Name: " + name);
	}

}
