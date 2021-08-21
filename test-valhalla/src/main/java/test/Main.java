package test;

public class Main {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis() ;
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
		Complex[] w2 = new Complex[1000_000] ;
		for(int i=0; i<w2.length; i++) {
			w2[i] = new Complex(10.0*i, 25.0/i) ;
		}
		
		for(int i=0; i<w2.length; i++) {
			w2[i] = Complex.add(w2[i], z1) ;
		}
		long endTime = System.currentTimeMillis() ;
		System.out.println(String.format("total time (msec) = %d", endTime-startTime));
		System.out.println(w2[100]);
		
		var p1 = new Point(1.1, 2.2) ;
		System.out.println(p1);
		System.out.println(p1.getClass());
		var p2 = p1.translate(5, 10) ;
		System.out.println(p2);

	}

}
