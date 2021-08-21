package test;

@__inline__
public class Point {
	
	double x, y ;
	
	public Point(double x, double y) {
		this.x = x ;
		this.y = y ;
	}
	
	public Point translate(double dx, double dy) {
		return new Point(x+dx, y+dy) ;
	}

}
