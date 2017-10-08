
public class Main {

	public static void main(String[] args) {
		Machine mach1 = new Machine();
		mach1.start();

		Person person1 = new Person("Bob");
		person1.greet();

		// Classes Machine and Person has nothing to do with each other. However, I
		// might want to say that I want both of them to have a common method, f.e.
		// 'showInfo'. That can be done with an interface.
		
		Info info1 = new Machine();
		info1.showInfo();
		
		Info info2 = person1;
		info2.showInfo();

	}

}
