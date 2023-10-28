//Sarthak Banglorewala 501164299
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.io.IOException; 
import java.io.File; 
import java.util.HashMap; 

public class AudioContentStore
{
	//initialize variables 
	private ArrayList<AudioContent> contents; 
	public HashMap<String, Integer> titleMap = new HashMap<>(); 
	public HashMap<String, ArrayList<Integer>> artistMap = new HashMap<>(); 
	public HashMap<String, ArrayList<Integer>> genreMap = new HashMap<>(); 
	
	public AudioContentStore() // Constructor method
	{
		try
		{
			contents = readFile("store.txt");//try store file content in arraylist 
		}
		catch(IOException e)//catch if file does not exist, and print error message 
		{ 
			System.out.println(e.getMessage()); 
			System.exit(1); 
		}

		for(int i = 0; i < contents.size(); i++)//loops through contents 
		{ 
			AudioContent content = contents.get(i);
			titleMap.put(content.getTitle().toUpperCase(), i);//adds to map 
		}
		
		for(int i = 0; i < contents.size(); i++)//loops through content
		{ 
			AudioContent content = contents.get(i);
			if(content.getType().equalsIgnoreCase(Song.TYPENAME))//if type is song
			{ 
				System.out.println("Loading SONG");
				Song song = (Song) content; //create song
				if(artistMap.containsKey(song.getArtist().toUpperCase()))//if artist map contains artist
				{ 
					ArrayList<Integer> updateIndexes = artistMap.get(song.getArtist().toUpperCase());// Get the int value of the artist key
					updateIndexes.add(i); 
					artistMap.put(song.getArtist().toUpperCase(), new ArrayList<Integer>(updateIndexes)); // Map a copy of the int to the artist key
				}
				else//if it map does not have artist already
				{ 
					ArrayList<Integer> artistIndexes = new ArrayList<Integer>(); //create new list
					artistIndexes.add(i);//add index to list(indexes)
					artistMap.put(song.getArtist().toUpperCase(), new ArrayList<Integer>(artistIndexes)); //add artist and indexes of artist in map
				}
			}
			else if (content.getType().equalsIgnoreCase(AudioBook.TYPENAME))//if type is audiobook 
			{
		
				System.out.println("Loading AUDIOBOOK");
				AudioBook bookContent = (AudioBook) content;//create bookContent 
				if(artistMap.containsKey(bookContent.getAuthor().toUpperCase()))//if artist map has the author key
				{ 
					ArrayList<Integer> updateIndex = artistMap.get(bookContent.getAuthor().toUpperCase());// Get the int value of the author key
					updateIndex.add(i);//add index 
					artistMap.put(bookContent.getAuthor().toUpperCase(), new ArrayList<Integer>(updateIndex));// Map a copy of the int to the author key
				}
				else//if the key not in the map 
				{ 
					ArrayList<Integer> authorIndexes = new ArrayList<Integer>();//create new arraylist to hold indexes 
					authorIndexes.add(i);//add indexes 
					artistMap.put(bookContent.getAuthor().toUpperCase(), new ArrayList<Integer>(authorIndexes));//put key and indexes into artistMap
				}
			}
		}
		
		for(int i=0; i < contents.size();i++)//loop through contents to create genre map
		{ 
			AudioContent content = contents.get(i);
			if(content.getType().equalsIgnoreCase(Song.TYPENAME))//check if song, as books do not have genres 
			{ 
				Song songContent = (Song) content; //create song content
				if(genreMap.containsKey(songContent.getGenre().toString().toUpperCase()))//if map contains genre 
				{ 
					ArrayList<Integer> updateIndexes = genreMap.get(songContent.getGenre().toString().toUpperCase());// Get the int value of the genre key
					updateIndexes.add(i);//add index
					genreMap.put(songContent.getGenre().toString().toUpperCase(), new ArrayList<Integer>(updateIndexes));//Map a copy of the int to the genre key
				}
				else//if the key is not in the map 
				{ 
					ArrayList<Integer> genreIndexes = new ArrayList<Integer>(); //create arraylist to hold indexes
					genreIndexes.add(i);//add indexes
					genreMap.put(songContent.getGenre().toString().toUpperCase(), new ArrayList<Integer>(genreIndexes));//put key and indexes into genreMap
				}
			}
		}
	}
	
	public AudioContent getContent(int index)//method that gets audio content
	{
		if (index >= 1 && index <= contents.size())//if index is valid
		{
			return contents.get(index-1);//return audiocontent 
			
		}
		else
		{
			return null;//if invalid return null
		}
		
	}
	
	public void listAll()
	{
		//print all audiocontent 
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}
	private ArrayList<AudioContent> readFile(String filename) throws IOException
	{
		ArrayList<AudioContent> fileContent = new ArrayList<AudioContent>(); //initialize arraylist that holds fileContent
		Scanner scanner = new Scanner(new File(filename)); //create scanner that scans file

		while(scanner.hasNextLine())
		{ 
			String type = scanner.nextLine(); 
			if(type.equalsIgnoreCase(Song.TYPENAME))
			{ 
				String id = scanner.nextLine(); // get ID
				String title = scanner.nextLine(); // get Title
				
				int year = scanner.nextInt(); // get Year
				scanner.nextLine(); 
				
				int length = scanner.nextInt(); // get Length
				scanner.nextLine(); 
				
				String artist = scanner.nextLine(); // get Artist
				String composer = scanner.nextLine(); // get Composer
				String genre = scanner.nextLine(); // get Genre 
				
				Song.Genre genreType = Song.Genre.POP; // initialize genre variable
				
				
				
				if(genre.equalsIgnoreCase("POP"))
				{ // Genre is POP
					genreType = Song.Genre.POP;
				}
				else if(genre.equalsIgnoreCase("RAP"))
				{ // Genre is RAP
					genreType = Song.Genre.RAP;
				}
				else if(genre.equalsIgnoreCase("CLASSICAL"))
				{ // Genre is CLASSICAL
					genreType = Song.Genre.CLASSICAL;
				}
				else if(genre.equalsIgnoreCase("ROCK"))
				{ // Genre is ROCK
					genreType = Song.Genre.ROCK;
				}
				else if(genre.equalsIgnoreCase("JAZZ"))
				{ // Genre is JAZZ
					genreType = Song.Genre.JAZZ;
				}
				else if(genre.equalsIgnoreCase("HIPHOP"))
				{ // Genre is HIPHOP
					genreType = Song.Genre.HIPHOP;
				}
				
				int lyricLength = scanner.nextInt(); // get Length of Lyrics
				scanner.nextLine(); // consume \n
				
				String lyrics = ""; // Initialize Lyrics String
				
				for(int i= lyricLength; i > 0; i --)
				{ 
					lyrics += scanner.nextLine() + "\r\n";
				}
				//Create a Song object and add to list
				fileContent.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genreType, lyrics)); 
			}
			else if(type.equalsIgnoreCase(AudioBook.TYPENAME))
			{ // If the current audiocontent is an audiobook
				String id = scanner.nextLine(); // get ID
				String title = scanner.nextLine(); // get Title

				int year = scanner.nextInt(); // get Year
				scanner.nextLine(); 

				int length = scanner.nextInt(); // get Length
				scanner.nextLine(); 

				String author = scanner.nextLine(); // get Author
				String narrator = scanner.nextLine(); // get Narrator
				int chapterLength = scanner.nextInt(); // get Length (number) of Chapters
				scanner.nextLine(); 



				ArrayList<String> chapterTitles = new ArrayList<String>(); // Initialize Chapter Titles ArrayList

				for(int i = chapterLength; i > 0; i--)
				{ // Iterating through the chapter titles in the file
					chapterTitles.add(scanner.nextLine()); // Add the chapter title to the chapterTitles ArrayList
				}
				
				
				ArrayList<String> chapters = new ArrayList<String>(); // Initialize Chapters ArrayList
				for(int i=chapterLength;i>0;i--)
				{ 
					int lines = scanner.nextInt(); // get length (number) of lines in the current chapter
					scanner.nextLine(); 
					String chapter = ""; // Initialize Chapter String
					
					for(int j= lines; j > 0;j--)
					{ // Iterating through the lines in the current chapter
							chapter += scanner.nextLine() + "\n";
					}
					chapters.add(chapter); // Add the chapter to chapters list
				}
				// Create an AudioBook object using the above information and add it to the 'fileContents' ArrayList
				fileContent.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters)); 
			}
		}
		return fileContent; //returns arraylist with all audio content
	}

	// Returns the titleMap 
	public HashMap<String, Integer> getTitleMap()
	{ 
		return titleMap;
	}

	// Returns the artistMap
	public HashMap<String, ArrayList<Integer>> getArtistMap()
	{ 
		return artistMap;
	}

	// Returns the artistMap
	public HashMap<String, ArrayList<Integer>> getGenreMap()
	{ // Returns the genreMap (hashmap)
		return genreMap;
	}
	// Returns the size of the AudioContentStore 
	public int getAudioContentsSize()
	{ 
		return contents.size();
	}
}