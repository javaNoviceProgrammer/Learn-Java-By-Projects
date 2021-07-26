package movie_timeline;

public class Utils {

	public static String toDoubleDigit(int number) {
		String result ;
		if(number<10) {
			result = String.format("0%d", number) ;
		}
		else {
			result = String.format("%d", number) ;
		}
		return result ;
	}
	
}
