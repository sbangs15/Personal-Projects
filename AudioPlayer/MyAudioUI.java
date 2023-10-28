//Sarthak Banglorewala 501164299
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD")) //if action is Download 
			{
				//initialize from index and to index 
				int startIndex = 0;
				int endIndex = 0;
				//ask user from index 
				System.out.print("From Store Content #: ");
				if(scanner.hasNextInt())
				{
					startIndex = scanner.nextInt();//update from index to user input 
					scanner.nextLine();//go to next line 
				}
				//ask user to index
				System.out.print("To Store Content #: ");
				
				if(scanner.hasNextInt())
				{
					endIndex = scanner.nextInt();//updtae to index to user input
					scanner.nextLine();//go to next line
				}
				for(int i = startIndex;i <= endIndex;i++)//loops through the range of the user indexes 
				{ 
					AudioContent content = store.getContent(i); // .getContent is 1-indexed
					
					if(content!=null)//check if content is not nill
					{ 
						//try to download content 
						try
						{
							library.download(content);
							System.out.println(content.getType() + " " + content.getTitle() + " Added to Library"); // Content downloaded
						}
						catch(ContentAlreadyDownloadedException e)//if an error occurs, print error message 
						{ 
							System.out.println(e.getMessage());
						}
					}
				}

			}
			else if(action.equalsIgnoreCase("DOWNLOADA"))//if user action is downloada
			{
				String artist = "";//initialize artist string
				System.out.print("Artist Name: ");//ask user for artist name 
				
				if(scanner.hasNextLine())
				{
					artist = scanner.nextLine();//update artist to user input 
				}

				if(store.getArtistMap().containsKey(artist.toUpperCase()))//checks if map contains artist 
				{ 
					ArrayList<Integer> indexes = store.getArtistMap().get(artist.toUpperCase()); //add indices of input artist to arraylist 
					for(int i=0;i<indexes.size();i++)//loop through indices 
					{ 
						AudioContent content = store.getContent(indexes.get(i)+1);//create content variable

						if(content!=null)//if content is not null
						{
							//try to download song
							try
							{
								library.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library"); // Content downloaded
							}
							catch(ContentAlreadyDownloadedException e)//if an error occurs, get error message
							{ 
								System.out.println(e.getMessage());
							}
						}
					}
				}
				else
				{ // Artist not found
					System.out.println("Artist Not Found");
				}
			}
			else if(action.equalsIgnoreCase("DOWNLOADG"))//if user action is downloadg
			{
				String genre = "";//initialize genre varible 
				System.out.print("Genre: ");//user prompt 
				
				if(scanner.hasNextLine())
				{
					genre = scanner.nextLine();//update genre to user input 
				}
				if(store.getGenreMap().containsKey(genre.toUpperCase()))//if map contains inputted genre
				{ 
					ArrayList<Integer> indexes = store.getGenreMap().get(genre.toUpperCase());//add indices of input genre to arraylist
					for(int i = 0; i<indexes.size(); i++)//loop through indices of genres
					{ 
						AudioContent content = store.getContent(indexes.get(i)+1);//create content variable 
						if(content!=null)//if content is not null
						{ 
							//try to download contenst 
							try
							{
								library.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library"); // Content downloaded
							}
							//if an error occurs, print error message 
							catch(ContentAlreadyDownloadedException e)
							{ 
								System.out.println(e.getMessage());
							}
						}
					}
				}
				else//prints genre not found if user input not in the map 
				{ 
					System.out.println("Genre Not Found");
				}
			}
			else if(action.equalsIgnoreCase("SEARCH"))//if user action is Search 
			{
				String title = "";//initialixze title varible 
				System.out.print("Title: ");//user prompt 
				
				if(scanner.hasNextLine())
				{
					title = scanner.nextLine();//update title to user input 
				}
				
				if(store.getTitleMap().containsKey(title.toUpperCase()))//if map contains title 
				{ 
					System.out.print((store.getTitleMap().get(title.toUpperCase()) + 1) + ". "); //print title
					(store.getContent(store.getTitleMap().get(title.toUpperCase()) + 1)).printInfo(); // Print the content's info
				}
				else//if match is not found 
				{ 
					System.out.println("No matches for " + title);
				}
			}
			else if(action.equalsIgnoreCase("SEARCHA"))//if user action is searcha 
			{
				String artist = "";//initialize artist varible 
				System.out.print("Artist: ");//user prompt 
				
				if(scanner.hasNextLine())
				{
					artist = scanner.nextLine();//update artist to user input 
				}
				if(store.getArtistMap().containsKey(artist.toUpperCase()))//if map contains artist 
				{ 
					ArrayList<Integer> indexes = store.getArtistMap().get(artist.toUpperCase()); // Gets the indices from the artist's ArrayList
					for(int i = 0; i < indexes.size(); i++)//loops through indices 
					{
						System.out.print((indexes.get(i) + 1) + ". ");
						(store.getContent(indexes.get(i) + 1)).printInfo(); // Print the content's info
						System.out.println(" ");
					}
				}
				else
				{ // Artist not found
					System.out.println("No matches for " + artist);
				}
			}
			else if(action.equalsIgnoreCase("SEARCHG"))//if user action is searchg
			{
				String genre = "";//initialize genre variable 
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");//user prompt 
				
				if(scanner.hasNextLine())
				{
					genre = scanner.nextLine();//update genre to user input 
				}
				if(store.getGenreMap().containsKey(genre.toUpperCase()))//if map contains genre 
				{ 
					ArrayList<Integer> indices = store.getGenreMap().get(genre.toUpperCase()); // Gets the indices from the genre's ArrayList
					for(int i=0;i<indices.size();i++)//loops through indices 
					{
						System.out.print((indices.get(i) + 1) + ". ");//prints content title
						(store.getContent(indices.get(i) + 1)).printInfo(); // Print the content's info
						System.out.println(" ");
					}
				}
				else//if genre not in map, print that no match was found
				{ 
					System.out.println("No matches for " + genre);
				}
			}
			else if(action.equalsIgnoreCase("SEARCHP"))//if user action is searchp 
			{
				String search = "";//initialize search variable 
				ArrayList<AudioContent> matches = new ArrayList<AudioContent>();//initialize arraylist for matches
				System.out.print("Look Up String: ");//user prompt 
				if(scanner.hasNextLine())
				{
					search = scanner.nextLine();//update seach varibale to user input 
				}
				for(int i = 1; i <= store.getAudioContentsSize(); i++)
				{ 
					AudioContent content = store.getContent(i); // Get the current audiocontent
					if(content.getType().equalsIgnoreCase(Song.TYPENAME))
					{ 
						Song songs = (Song) content; // Cast the Song to a song
						
						if((songs.getTitle().toUpperCase()).contains(search.toUpperCase()))//if the title contains the searched string
						{ 
							matches.add(songs); // Add the song to the match ArrayList
						}
						else if((String.valueOf(songs.getYear()).toUpperCase()).contains(search.toUpperCase()))//if song year contains searched string
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getId().toUpperCase()).contains(search.toUpperCase()))//if the id contains searched string 
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getType().toUpperCase()).contains(search.toUpperCase()))//if type contains searched string 
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getAudioFile().toUpperCase()).contains(search.toUpperCase()))//if audiofile matches searched string
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((String.valueOf(songs.getLength()).toUpperCase()).contains(search.toUpperCase()))//if song length contains searched string
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getArtist().toUpperCase()).contains(search.toUpperCase()))//if artists contains searched string
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getComposer().toUpperCase()).contains(search.toUpperCase()))//if composer contains searched string 
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getGenre().toString().toUpperCase()).contains(search.toUpperCase()))//if genre contains search string 
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
						else if((songs.getLyrics().toUpperCase()).contains(search.toUpperCase()))//if lyrics contain searched string 
						{ 
							matches.add(songs);// Add the song to the match ArrayList
						}
					}
					else if(content.getType().equalsIgnoreCase(AudioBook.TYPENAME))//if content type is audiobooks 
					{ 
						AudioBook books = (AudioBook) content; // Cast the audiocontent to a book
						
						if((books.getTitle().toUpperCase()).contains(search.toUpperCase()))//if title contains searched string
						{
							matches.add(books);// Add the book to the match ArrayList
							
						}
						else if((String.valueOf(books.getYear()).toUpperCase()).contains(search.toUpperCase()))//if year contains searched string
						{ 
							matches.add(books);// Add the book to the match ArrayList
							
						}
						else if((books.getId().toUpperCase()).contains(search.toUpperCase()))//if id contains searched string 
						{
							matches.add(books);// Add the book to the match ArrayList
							
						}
						else if((books.getType().toUpperCase()).contains(search.toUpperCase()))//if type contains searched string
						{ 
							matches.add(books);// Add the book to the match ArrayList
							
						}
						else if((books.getAudioFile().toUpperCase()).contains(search.toUpperCase()))//if audiofile contains searched string 
						{ 
							matches.add(books);// Add the book to the match ArrayList
						}
						else if((String.valueOf(books.getLength()).toUpperCase()).contains(search.toUpperCase()))//if length contains searched string 
						{ 
							matches.add(books);// Add the book to the match ArrayList
						}
						else if((books.getAuthor().toUpperCase()).contains(search.toUpperCase()))//if author contains searched string
						{ 
							matches.add(books);// Add the book to the match ArrayList
						}
						else if((books.getNarrator().toUpperCase()).contains(search.toUpperCase()))//if narrorator contains searched string
						{ 
							matches.add(books);// Add the book to the match ArrayList
						}
					
						for(String title : books.getChapterTitles())//loops through chapter titles 
						{ 
							if((title.toUpperCase()).contains(search.toUpperCase()))//checks if chapter titles contain searched string 
							{
								if(!matches.contains(books))//if book is not already in match Arraylist
								{ 
									matches.add(books);// Add the book to the match ArrayList
								}
							}
						}
						for(String chapter : books.getChapters())//loops theougj chapters
						{ 
							if((chapter.toUpperCase()).contains(search.toUpperCase()))//checks if chapters contain searched string 
							{
								if(!matches.contains(books))//if book is not already in match Arraylist
								{ 
									matches.add(books);	// Add the book to the match ArrayList
								}
							}
						}
					}
				}
				if(matches.size()==0)//if match arraylist is empty 
				{ 
					System.out.println("No matches for " + search);//print no matches found
				}
				else//if matches exist 
				{
					for(int i=0; i < matches.size();i++)//goes through matches arraylist 
					{ 
						AudioContent content = matches.get(i); // Get the current audiocontent
						System.out.print((store.getTitleMap().get(content.getTitle().toUpperCase()) + 1) + ". "); // Print the audiocontent's title
						content.printInfo(); // Print the audiocontent's info
				 		System.out.println(" ");
					}
				}
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				
				try
				{
					library.playSong(index);
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Print error message if the song doesn't exist 
				}

			}

			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}

				try
				{
					library.printAudioBookTOC(index);
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Print error message if the book doesn't exist 
				}
	
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				int chapter = 0;
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chapter = scanner.nextInt();
					scanner.nextLine();
				}
				
				try
				{
					library.playAudioBook(index, chapter);//play audiobook
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage());// Print error message if the book or chapter doesn't exist
				}
			}
			
			else if (action.equalsIgnoreCase("PLAYALLPL"))
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				try
				{
					library.playPlaylist(title);//try to play all playlist
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Print error message if the playlist doesn't exist in the library
				}

			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
        		int index = 0;
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				try
				{
					library.playPlaylist(title, index); // try to play playlist 
				}
				catch(ContentNotFoundException e1)
				{
					System.out.println(e1.getMessage()); // Print error message if the playlist doesn't exist in the library
				}
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;

				System.out.print("Library Song #: ");
				if (scanner.hasNextInt())
				{
					songNum = scanner.nextInt();
					scanner.nextLine();
				}
				try
				{
					library.deleteSong(songNum);//try to delete song
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Print error message if the song doesn't exist 
				}

			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				try
				{
					library.makePlaylist(title);//try to make playlist 
				}
				catch(PlaylistAlreadyExistsException e)
				{
					System.out.println(e.getMessage()); // Prints error message if the playlist already exists 
				}
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				try
				{
					library.printPlaylist(title);//prints playlist 
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Prints error message if the playlist doesn't exist in the library
				}
			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
       			String playlist = "";
        
        		System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
        
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine())
				{
					contentType = scanner.nextLine();
				}

				
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				try
				{
					library.addContentToPlaylist(contentType, contentIndex, playlist);//try to add  contentn to playlist 
				}
				catch(ContentNotFoundException e1)
				{
					System.out.println(e1.getMessage()); // Prints error message if playlist or audiocontent don't exist in the library
				}
				catch(ContentAlreadyDownloadedException e2)
				{
					System.out.println(e2.getMessage()); // Prints error message if audiocontent is already in the playlist
				}
				catch(InvalidContentTypeException e3)
				{
					System.out.println(e3.getMessage()); // Prints error message if the content type is invalid
				}
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					playlist = scanner.nextLine();
				}
				
				System.out.print("Playlist Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				try
				{
					library.delContentFromPlaylist(contentIndex, playlist);//try to delete audiocontent from playlist 
				}
				catch(ContentNotFoundException e)
				{
					System.out.println(e.getMessage()); // Prints error message if the playlist or audiocontent don' exist in the library
				}

			}

			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}
			
			System.out.print("\n>");
		}
	}
}
