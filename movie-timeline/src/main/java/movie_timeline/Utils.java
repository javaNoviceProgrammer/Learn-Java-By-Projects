package movie_timeline;

public class Utils {

	public static String toDoubleDigit(int number) {
		String result ;
		if(number<10) {
			result = "0%d".formatted(number) ;
		}
		else {
			result = "%d".formatted(number) ;
		}
		return result ;
	}
	
}
