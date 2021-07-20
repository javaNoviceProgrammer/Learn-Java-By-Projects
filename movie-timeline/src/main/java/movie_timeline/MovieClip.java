package movie_timeline;

public class MovieClip {
	
	String name ;
	int totalSeconds ; // total duration in seconds
	int offset ; // start offset in seconds
	int hours, minutes, seconds ; // for duration
	TimeStamp startTimeStamp, endTimeStamp ; // actual time stamps
	TimeStampSpecifier specifier ;
	
	public MovieClip(String name, int hours, int minutes, int seconds, TimeStampSpecifier specifier) {
		this.name = name ;
		this.hours = hours ;
		this.minutes = minutes ;
		this.seconds = seconds ;
		this.totalSeconds = hours*3600 + minutes*60 + seconds ;
		this.offset = 0 ;
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
		this.offset = offset ;
		return this ;
	}
	
	public int getTotalSeconds() {
		return totalSeconds ;
	}
	
	public TimeStamp getStartTimeStamp() {
		startTimeStamp = new TimeStamp(offset) ;
		return startTimeStamp ;
	}
	
	public TimeStamp getEndTimeStamp() {
		endTimeStamp = new TimeStamp(offset+getTotalSeconds()) ;
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
	
	// create a parser for the text files
	
	
	public static void main(String[] args) {
		MovieClip clip1 = new MovieClip("Avengers End game", 2, 35, 5) ;
		System.out.println(clip1);
		clip1.setTimeStampSpecifier(TimeStampSpecifier.START_TIME) ;
		System.out.println(clip1);
		clip1.setOffset(210) ;
		System.out.println(clip1);
		clip1.setTimeStampSpecifier(TimeStampSpecifier.END_TIME) ;
		System.out.println(clip1);
	}

}
