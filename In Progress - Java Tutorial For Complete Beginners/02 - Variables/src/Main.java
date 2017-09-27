
public class Main {

	public static void main(String[] args) {
		// 
		int myNumber;
		myNumber = 7;
		
		/*  
		 * The short data type is a 16-bit signed two's complement integer. 
		 * It has a minimum value of -32,768 and a maximum value of 32,767 (inclusive)
		 */
		short myShortNumber = 1337;
		
		/* The long data type is a 64-bit two's complement integer. 
		 * The signed long has a minimum value of -263 and a maximum value of 263-1. 
		 * In Java SE 8 and later, you can use the long data type to represent an unsigned 64-bit long, 
		 * which has a minimum value of 0 and a maximum value of 264-1
		 */
		long myLongNumber = 8746273;
		
		double myDouble = 3.14;
		// Add 'f' in order to get the number as float.
		// It is of type double by default.
		float myFloat = 1.44f;
		
		char myChar = 'y';
		
		boolean myBoolean = true; 
		byte myByte = 127; 
		
		System.out.println(myNumber);
		System.out.println(myShortNumber);
		System.out.println(myLongNumber);
		System.out.println(myDouble);
		System.out.println(myFloat);
		System.out.println(myChar);
		System.out.println(myBoolean);
		System.out.println(myByte);
		
	}

}
