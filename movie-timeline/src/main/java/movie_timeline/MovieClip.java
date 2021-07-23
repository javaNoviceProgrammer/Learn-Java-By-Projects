package movie_timeline;

public class MovieClip {
	
	String name ;
	int totalSeconds ; // total duration in seconds
	int offsetSeconds ; // start offset in seconds
	int hours, minutes, seconds ; // for duration
	TimeStamp startTimeStamp, endTimeStamp ; // actual time stamps
	TimeStampSpecifier specifier ;
	
	public MovieClip(String name, int hours, int minutes, int seconds, TimeStampSpecifier specifier) {
		this.name = name ;
		this.hours = hours ;
		this.minutes = minutes ;
		this.seconds = seconds ;
		this.totalSeconds = hours*3600 + minutes*60 + seconds ;
		this.offsetSeconds = 0 ;
		this.specifier = specifier ;
	}
	
	public MovieClip(String name, int hours, int minutes, int seconds) {
		this(name, hours, minutes, seconds, TimeStampSpecifier.DURATION) ;
	}
	
	public MovieClip setTimeStampSpecifier(TimeStampSpecifier specifier) {
		this.specifier = specifier ;
		return this ;
	}
	
	public MovieClip setTotalSeconds(int totalSeconds) {
		this.totalSeconds = totalSeconds ;
		hours = totalSeconds/3600 ;
		minutes = (totalSeconds - hours*3600)/60 ;
		seconds = totalSeconds - hours*3600 - minutes*60 ;
		return this ;
	}
	
	public MovieClip setOffset(int offset) {
		this.offsetSeconds = offset ;
		startTimeStamp = new TimeStamp(offsetSeconds) ;
		endTimeStamp = new TimeStamp(offsetSeconds+getTotalSeconds()) ;
		return this ;
	}
	
	public int getTotalSeconds() {
		return totalSeconds ;
	}
	
	public TimeStamp getStartTimeStamp() {
		if(startTimeStamp==null) {
			startTimeStamp = new TimeStamp(offsetSeconds) ;
		}
		return startTimeStamp ;
	}
	
	public TimeStamp getEndTimeStamp() {
		if(endTimeStamp==null) {
			endTimeStamp = new TimeStamp(offsetSeconds+getTotalSeconds()) ;
		}
		return endTimeStamp ;
	}

	// format: timestamp name
	@Override
	public String toString() {
		String str ;
		switch (specifier){
			case START_TIME:
				str = "%s %s".formatted(getStartTimeStamp(), name) ;
				break ;
			case END_TIME:
				str = "%s %s".formatted(getEndTimeStamp(), name) ;
				break ;
			default:
				// create a formatted time stamp for the duration
				str = "%s %s".formatted(new TimeStamp(getTotalSeconds()), name) ;
				break ;
		}
		return str ;
	}
	
	// create a parser for the string --> format: "hhh:mm:ss name " --> has to be the duration format
	public static MovieClip parseString(String str) {
		String temp = str.trim() ; // remove white spaces (leading, trailing)
//		int index = 0 ;
//		for(int i=0; i<temp.length(); i++) {
//			if(temp.charAt(i)==' ') {
//				index = i ;
//				break ;
//			}
//		}
		int index = temp.indexOf(' ') ;
		// split the time stamp and name
		String timeStampStr = temp.substring(0, index) ;
		String[] args = timeStampStr.split(":") ;
		String name = temp.substring(index).trim() ;
		return new MovieClip(name, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])) ;
	}
		
	public static void main(String[] args) {
		// parsing string
		MovieClip clip1 = MovieClip.parseString("0:2:300    some arbitrary name") ;
		System.out.println(clip1);
	}

}
