package movie_timeline;

public class TimeStamp {
	
	int timeSeconds ; // total seconds: example: 300 seconds
	int hours, minutes, seconds ; // timestamp format: hh:mm:ss
	String fmt ; // extra format for the toString() method
	
	public TimeStamp(int timeSeconds, String fmt) {
		this.timeSeconds = timeSeconds ; // prevent shadowing
		this.fmt = fmt ;
		doConversion() ;
	}
	
	public TimeStamp(int timeSeconds) {
		this.timeSeconds = timeSeconds ; // prevent shadowing
		doConversion() ;
	}
	
	public TimeStamp setFmt(String fmt) {
		this.fmt = fmt ;
		return this ;
	}
	
	private void doConversion() {
		hours = timeSeconds/3600 ; // 1 hour = 3600 seconds --> integer division
		minutes = (timeSeconds - hours*3600)/60 ; // 1 minute = 60 seconds
		seconds = timeSeconds - hours*3600 - minutes*60 ; // 1 second = 1 second
	}

	@Override
	public String toString() {
		String str = String.format("%s:%s:%s", Utils.toDoubleDigit(hours), Utils.toDoubleDigit(minutes), Utils.toDoubleDigit(seconds)) ;
		if(fmt == null) {
			return str ;
		}
		else {
			return String.format(fmt, str) ;
		}
	}
	
	public static void main(String[] args) {
		TimeStamp timeStamp = new TimeStamp(1000) ;
		System.out.println(timeStamp);
	}
	
}
