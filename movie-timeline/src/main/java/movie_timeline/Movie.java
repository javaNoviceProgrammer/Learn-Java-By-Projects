package movie_timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Movie {
	
	String name ; // name of the movie
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
		String str = "MOVIE: %s [Time stamp format: %s]".formatted(name, specifier) ;
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
//		MovieClip clip1 = MovieClip.parseString("0:2:3 some arbitrary name") ;
//		MovieClip clip2 = MovieClip.parseString("1:13:2 some other movie clip") ;
//		MovieClip clip3 = MovieClip.parseString("2:1:21 the third movie clip") ;
//		Movie movie = new Movie("joined movie clips", TimeStampSpecifier.START_TIME) ;
//		movie.addMovieClip(clip1).addMovieClip(clip2).addMovieClip(clip3) ;
//		System.out.println(movie);
//		// save to file
//		movie.saveToFile("./src/main/resources/test2.txt");
		String fileName = "./src/main/resources/test2.txt" ;
		Movie movie = Movie.parseTextFile(fileName, TimeStampSpecifier.DURATION, TimeStampSpecifier.END_TIME) ;
		movie.setName("some awesome movie") ;
		System.out.println(movie);
	}

}
