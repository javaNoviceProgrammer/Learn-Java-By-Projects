package test;

@__inline__
public class Complex { // value-type classes

	double re, im ;
	
	public Complex(double re, double im) {
		this.re = re ;
		this.im = im ;
	}

	@Override
	public String toString() {
		return "Complex [re=" + re + ", im=" + im + "]";
	}

}
