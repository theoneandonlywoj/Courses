
public class Main {

	public static void main(String[] args) {
		Machine m1 = new Machine();

		m1.start();
		m1.stop();

		Car car1 = new Car();
		car1.start();
		car1.openDoor();
		car1.closeDoor();
	}

}
