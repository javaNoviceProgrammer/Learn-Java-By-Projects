package test;


@__inline__
public class Complex { // value-type classes 

	double re, im ;
	
	public Complex(double re, double im) {
		this.re = re ;
		this.im = im ;
	}
	
	public static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.re + z2.re, z1.im + z2.im) ;
	}

	@Override
	public String toString() {
		return "Complex [re=" + re + ", im=" + im + "]";
	}
	
	

}
