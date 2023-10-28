/*//Sarthak Banglorewala 501164299

 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable <Song>
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		super(title, year, id, type, audioFile, length);//initialize variables from super class
		//initialize variables 
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	//returns type 
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo(); //prints song basic info 
		System.out.println("Artist: " + artist + " Composer: " + composer + " Genre: " + genre);//prints arist, composer, and genre 
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		super.setAudioFile(lyrics);//sets audiofile to the lyrics
		super.play();//plays song
	}
	//returns composer
	public String getComposer()
	{
		return composer;
	}
	//sets composer 
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	//returns artist 
	public String getArtist()
	{
		return artist;
	}
	//sets artist
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	//returns lyrics
	public String getLyrics()
	{
		return lyrics;
	}
	//sets lyrics
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}
	//returns gernre
	public Genre getGenre()
	{
		return genre;
	}
	//sets genre 
	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		Song song2 = (Song) other; //create new Song variable for other song
		//checks if the songs are the same, return true if equal, false if not equal
		return (this.getComposer()).equals(song2.getComposer()) && (this.getArtist()).equals(song2.getArtist()) && super.equals(other);


	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		return (super.getTitle()).compareTo(other.getTitle());// returns 1 if current title is greater, -1 if other title is greater and 0 if they equal
	}
}
