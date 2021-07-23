package movie_timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
	
	String name ; // name of the movie
	String header ; // header for export text file
	ArrayList<MovieClip> movieClips ; // a list of clips (added in sequence)
	TimeStampSpecifier specifier ; // how the time stamps of the movie clips are going to be printed
	int totalSecondsElapsed = 0 ;
	
	public Movie(String name, TimeStampSpecifier specifier) {
		this.name = name ;
		this.specifier = specifier ;
		this.movieClips = new ArrayList<>() ;
	}
	
	public Movie setName(String name) {
		this.name = name ;
		return this ;
	}
	
	public Movie setHeader(String header) {
		this.header = header ;
		return this ;
	}
	
	public Movie setTimeStampSpecifier(TimeStampSpecifier specifier) {
		this.specifier = specifier ;
		return this ;
	}
	
	// add movie clips --> most important method
	public Movie addMovieClip(MovieClip clip) {
		// step 1: set the format of time stamp for the clip
		clip.setTimeStampSpecifier(specifier) ;
		// step 2: set the offset of the clip
		clip.setOffset(totalSecondsElapsed) ;
		// step 3: update the total time
		totalSecondsElapsed += clip.getTotalSeconds() ;
		// step 4: add it to the list
		movieClips.add(clip) ;
		// step 5: return a reference to the current movie object
		return this ;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(movieClips.size()*15) ;
		String str ;
		if(header==null) {
			str = "MOVIE: %s [Time stamp format: %s]".formatted(name, specifier) ;
		} else {
			str = header ;
		}
		sb.append(str).append(System.lineSeparator()) ;
		sb.append("*".repeat(str.length())).append(System.lineSeparator()) ;
		for(MovieClip clip : movieClips) {
			sb.append(clip.toString()).append(System.lineSeparator()) ;
		}
		return sb.toString() ;
	}
	
	// save to file
	public void saveToFile(String fileName) {
		File textFile = new File(fileName) ;
		try(FileWriter writer = new FileWriter(textFile)) {
			writer.write(toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// parsing a text file that has list of movie clips
	public static Movie parseTextFile(String fileName, TimeStampSpecifier inputSpecifier, TimeStampSpecifier outputSpecifier) {
		Movie movie = new Movie("null", outputSpecifier) ;
		// read text file line by line --> parse each line to a movie clip
		try(Scanner scanner = new Scanner(new File(fileName))) {
			while(scanner.hasNextLine()) {
				MovieClip clip = MovieClip.parseString(scanner.nextLine()) ; // assumes duration
				clip.setTimeStampSpecifier(inputSpecifier) ;
				movie.addMovieClip(clip) ;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movie ;
	}
	
	
	
	
	public static void main(String[] args) {
		String fileName = "./src/main/resources/example1.txt" ;
		Movie lectures = Movie.parseTextFile(fileName, TimeStampSpecifier.DURATION, TimeStampSpecifier.START_TIME) ;
		lectures.setName("Lectures") ;
		lectures.setHeader("Time-Line") ;
		lectures.saveToFile("./src/main/resources/lectures_timeline.txt");
	}

}
