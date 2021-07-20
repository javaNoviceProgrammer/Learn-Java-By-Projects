package movie_timeline;

public class TimeStamp {
	
	int timeSeconds ; // total seconds: example: 300 seconds
	int hours, minutes, seconds ; // timestamp format: hh:mm:ss
	
	public TimeStamp(int timeSeconds) {
		this.timeSeconds = timeSeconds ; // prevent shadowing
		doConversion() ;
	}
	
	private void doConversion() {
		hours = timeSeconds/3600 ; // 1 hour = 3600 seconds --> integer division
		minutes = (timeSeconds - hours*3600)/60 ; // 1 minute = 60 seconds
		seconds = timeSeconds - hours*3600 - minutes*60 ; // 1 second = 1 second
	}

	@Override
	public String toString() {
		return String.format("%s:%s:%s", Utils.toDoubleDigit(hours), Utils.toDoubleDigit(minutes), Utils.toDoubleDigit(seconds)) ;
	}
	
	public static void main(String[] args) {
		TimeStamp timeStamp1 = new TimeStamp(222) ;
		System.out.println(timeStamp1);
	}
	
}
