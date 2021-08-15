package test;

public class Main {

	public static void main(String[] args) {
		Complex z1 = new Complex(1.1, 2.2) ;
		Complex z2 = new Complex(-1.1, -2.2) ;
		Complex[] w = {z1, z2} ; // inside java heap: flat layout
		System.out.println(z1);
		System.out.println(z1.getClass()); // examine the class type of this object: Complex
		System.out.println(w.getClass()); // byte code --> [: array, Ltest.Complex;
		// doesn't have identity: Qtest.Complex;
		System.out.println(w[0].getClass());
		// some changes
		// code like an object, works like a primitive 
		
	}

}
