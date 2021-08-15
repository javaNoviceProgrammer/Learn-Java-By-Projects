package test;

@__inline__
public class Complex { // value-type classes 

	double re, im ;
	
	public Complex(double re, double im) {
		this.re = re ;
		this.im = im ;
	}
	
	public Complex add(Complex z) {
		return new Complex(re+z.re, im+z.im) ;
	}

	@Override
	public String toString() {
		return "Complex [re=" + re + ", im=" + im + "]";
	}

}
