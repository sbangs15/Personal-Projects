//Sarthak Banglorewala 501164299
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	//initalize arraylists for songs, audiobooks, and playlists
	private ArrayList<Song>songs; 
	private ArrayList<AudioBook>audiobooks;
	private ArrayList<Playlist>playlists; 
	
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	
	// String errorMsg = "";//initialize error message string to empty 
	
	// //returns error message 
	// public String getErrorMessage()
	// {
	// 	return errorMsg;
	// }

	public Library()
	{
		//create arraylists 
		songs = new ArrayList<Song>(); 
		audiobooks = new ArrayList<AudioBook>();
		playlists = new ArrayList<Playlist>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content)throws ContentAlreadyDownloadedException
	{
		if (content.getType().equals(Song.TYPENAME))
		{
			if (songs.contains(content))
			{
				//throws exception if downloaded
				throw new ContentAlreadyDownloadedException(Song.TYPENAME + " " + content.getTitle() + " already downloaded");
			}
			songs.add((Song)content);
		}
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			if (audiobooks.contains(content))
			{
				//throws exception if downloaded
				throw new ContentAlreadyDownloadedException(AudioBook.TYPENAME + " " + content.getTitle() + " already downloaded");
			}
			audiobooks.add((AudioBook)content);
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		//System.out.print(songs.size());

		//for loop that goes through songs list 
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;//set index to i + 1 because indexing will start from 1 
			System.out.print(index + ". ");//print index
			songs.get(i).printInfo();//print song infos
			System.out.println();//to print next statement on new line 
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		//for loop that goes through audiobooks list 
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;//set index to i + 1 because indexing will start from 1 
			System.out.print(index + ". ");//prints index
			audiobooks.get(i).printInfo();//prints audiobook info 
			System.out.println();//print new line 
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		//for loop that goes through playlist list 
		for(int i= 0; i < playlists.size(); i++)
		{
			System.out.println((i+1) + ". " + playlists.get(i).getTitle());//prints number and playlist title
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		
		ArrayList<String> artists = new ArrayList<String>();//create empty playlidt of artists

		//for loop that goes through the size of the song list
		for(int i = 0; i < songs.size(); i ++)
		{
			if (!artists.contains(songs.get(i).getArtist()))//checks if artist is not already in the artist list (no repeats)
			{
				artists.add(songs.get(i).getArtist());//id condition above is true, adds artist to artist lists 
			}
		}

		//after list of unique artists are created print all the artists in the list 
		for(int i= 0; i < artists.size();i++)
		{ 
			System.out.println((i+1) + ". " + artists.get(i));
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)throws ContentNotFoundException
	{
		if(index >= 1 && index <= songs.size())//checks if index is valid
		{ 
			for(int i = 0; i < playlists.size(); i++)
			{
				ArrayList<AudioContent> allAudio = playlists.get(i).getContent(); //add all audio from playlists into new list 
				
				//loops through list of all audio 
				for(int j = 0; j < allAudio.size(); j++)
				{ 	
					//checks if an audio in all audio is equal to the song at the given index 
					if((allAudio.get(j)).equals(songs.get(index-1)))
					{ 
						playlists.get(i).deleteContent(j+1);//removes the content from the list
					}
				}
			}
			songs.remove(index-1);//also remove song from song list
			
		}
		else //if index is invalid 
		{
			throw new ContentNotFoundException("Song Not Found");
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		Collections.sort(songs, new SongYearComparator());//sort song by year, using song year comparator
	
	}
	
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		//compares both songs, and returns 1 if song1 is greater, -1 if song2 is greater and 0 if they equal
		public int compare(Song song1, Song song2)
		{
			return Integer.compare(song1.getYear(), song2.getYear());
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		Collections.sort(songs, new SongLengthComparator());//sort songs by length using song length comparator
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		//compares both songs, and returns 1 if song1 is longer , -1 if song2 is greater and 0 if they equal
		public int compare(Song song1, Song song2) 
		{
			return Integer.compare(song1.getLength(), song2.getLength()); 
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);//sorts song by name, using compare to method in songs 
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)throws ContentNotFoundException
	{
		//checks if index is valid 
		if (index >= 1 && index <= songs.size())
		{
			songs.get(index-1).play();//play at song at given index
		}
		else
		{
			//throws exception if does not exist
			throw new ContentNotFoundException("Song Not Found");
		}
		
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	// public boolean playPodcast(int index, int season, int episode)
	// {
	// 	return false;
	// }
	
	// // Print the episode titles of a specified season
	// // Bonus 
	// public boolean printPodcastEpisodes(int index, int season)
	// {
	// 	return false;
	// }
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws ContentNotFoundException
	{
		//checks if index is invalid (less than 1 and greater than audiobook list size)
		if(index < 1 || index > audiobooks.size())
		{ 
			//throws exception if does not exist
			throw new ContentNotFoundException("Audiobook Not Found");
		}
		//checks if index is invalid (less than 1 and greater than number of chapters)
		if(chapter<1 || chapter> audiobooks.get(index-1).getNumberOfChapters())
		{ 
			//throws exception if does not exist
			throw new ContentNotFoundException("Chapter Not Found");
		}
		audiobooks.get(index-1).selectChapter(chapter);//sets audiobook chapter 										 
		audiobooks.get(index-1).play();//plays audiobook
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)throws ContentNotFoundException
	{
		//checks if index is valid 
		if(index >= 1 && index <= audiobooks.size())
		{ 
			//if it index is valid gets the index and prints audiobook
			audiobooks.get(index-1).printTOC(); 
			
		}
		else//if invalid
		{
			//throws exception if does not exist
			throw new ContentNotFoundException("Audiobook Not Found");
		}
		
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)throws PlaylistAlreadyExistsException
	{
		//make a new plalylist with given title
		Playlist playlist2 = new Playlist(title);
		//for loop that goes through playlist list 
		for(int i = 0; i < playlists.size(); i++)
		{
			//checks if playlist is equal to any of the playlists already made
			if((playlists.get(i)).equals(playlist2))
			{ 
				//throws exception if playlist already exists
				throw new PlaylistAlreadyExistsException("Playlist Already Exists");
			}
		}
		//if the new playlist is unique adds to playlist list, return true
		playlists.add(playlist2);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)throws ContentNotFoundException
	{
		//for loop that goes through playlist list 
		for(int i = 0; i < playlists.size(); i++)
		{
			//checks if a playlist exists in the list that is the same as user input
			if((playlists.get(i).getTitle()).equalsIgnoreCase(title))
			{ 
				//if match is found, prints contents of playlist 
				playlists.get(i).printContents();
				return;
				
			}
		}
		throw new ContentNotFoundException("Playlist Not Found");


	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)throws ContentNotFoundException
	{
		//for loop that goes through playlists list 
		for(int i = 0; i < playlists.size(); i++)
		{
			//checks if given title exists in the playlist list 
			if((playlists.get(i).getTitle()).equalsIgnoreCase(playlistTitle))
			{ 
				//if match is found prints playlist title and plays all the audio in the playlist
				System.out.println(playlists.get(i).getTitle()); // 
				playlists.get(i).playAll(); 
				return;
			}
		}
		throw new ContentNotFoundException("Playlist Not Found");
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) throws ContentNotFoundException
	{
		//for loop that goes through playlists 
		for(int i = 0;i < playlists.size(); i++)
		{	
			//checks if any of the playlist in the list are the same as user input (have the same title)
			if((playlists.get(i).getTitle()).equalsIgnoreCase(playlistTitle))
			{ 	
				//if match is found checks if the playlist contains audio at the index given 
				if(playlists.get(i).contains(indexInPL))
				{ 
					//if index is valid, prints title of playlist, and plays audio at specified index of the playlist 
					System.out.println(playlists.get(i).getTitle()); 
					playlists.get(i).play(indexInPL); 
					return;
					
				}
				else//if index is invalid then set error message to content not found and return false 
				{
					throw new ContentNotFoundException("Content Not Found");
				}
			}
		}
		throw new ContentNotFoundException("Playlist Not Found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)throws ContentNotFoundException, ContentAlreadyDownloadedException, InvalidContentTypeException
	{
		if(type.equalsIgnoreCase(Song.TYPENAME))//checks if type is equal to the type of song
		{ 
			//checks if index is valid 
			if(index >= 1 && index <= songs.size())
			{ 
				//for loop that goes through playlists list 
				for(int i = 0;i < playlists.size(); i++)
				{
					//checks if any of the playlists in the same as user input (looking for match)
					if((playlists.get(i).getTitle()).equalsIgnoreCase(playlistTitle))
					{ 
						ArrayList<AudioContent> allContent  = playlists.get(i).getContent();//create new arraylist with playlist content
						//for loop that goes through content 
						for(int j = 0; j< allContent.size(); j++)
						{ 
							//check if the content has the same type as song and check if the song is the same 
							if(allContent.get(j).getType().equalsIgnoreCase(Song.TYPENAME) && (allContent.get(j)).equals(songs.get(index-1)))
							{ 
								throw new ContentAlreadyDownloadedException("Song Already in Playlist " + playlists.get(i).getTitle());
							}	
						}
					}
						//add song to playlists, return true
						playlists.get(i).addContent(songs.get(index-1)); 
						return;
				}
				//if none of the conditions above are met the playlist does not exist, set error message to playlist not found and return false
				throw new ContentNotFoundException("Playlist Not Found"); // If playlist not found
				
			}
			else
			{
				//if invalid, sets error message to song not found and returns false 
				throw new ContentNotFoundException("Song Not Found");
			}
		}
		else if (type.equalsIgnoreCase(AudioBook.TYPENAME))
		{
			//checks if index is valid 
			if(index >= 1 && index <= audiobooks.size())
			{ 
				//for loop that goes through playlists list 
				for(int i = 0;i < playlists.size(); i++)
				{
					//checks if any of the playlists in the same as user input (looking for match)
					if((playlists.get(i).getTitle()).equalsIgnoreCase(playlistTitle))
					{ 
						ArrayList<AudioContent> allContent  = playlists.get(i).getContent();//create new arraylist with playlist content
						
						for(int j = 0; j< allContent.size(); j++)
						{ 
							//check if the content has the same type as audiobook and check if the audiobook is the same 
							if((allContent.get(j).getType().equalsIgnoreCase(AudioBook.TYPENAME) && (allContent.get(j)).equals(audiobooks.get(index-1))))
							{ 
								//set error message saying book is already in list, and return false 
								throw new ContentAlreadyDownloadedException("AudioBook Already in Playlist " + playlists.get(i).getTitle());

							}
						}
					}
					//add book to playlists, return true
					playlists.get(i).addContent(audiobooks.get(index-1)); 
					return;	
				}
				//if none of the conditions above are met the playlist does not exist, set error message to playlist not found and return false
				throw new ContentNotFoundException("Playlist Not Found"); // If playlist not found
				
			}
			else
			{
				//if invalid, sets error message to song not found and returns false 
				throw new ContentNotFoundException("AudioBook Not Found");
			}
		}
		else//if type is not equal to audiobook or song, set error message to invalid type and return false 
		{
			throw new InvalidContentTypeException("Invalid Content Type"); // If the content type is not (SONG/AUDIOBOOK)
		}
	}
	

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title) throws ContentNotFoundException
	{
		//for loop that goes through playlists 
		for(int i = 0;i < playlists.size();i++)
		{
			//checks if input title exists in playlist list
			if((playlists.get(i).getTitle()).equalsIgnoreCase(title))
			{ 
				//checks if the index exists in playlist
				if(playlists.get(i).contains(index))
				{ 
					//deletes content in playlist at inputted index 
					playlists.get(i).deleteContent(index);
					return;
				}
				else
				{
					//if index does not exist set error message to content nor found, and return false 
					throw new ContentNotFoundException("Content Not Found");
				}
				
			}
		}
		//if conditions above not met, then playlist does not exist 
		throw new ContentNotFoundException("Playlist Not Found");
	}
	
}
class ContentNotFoundException extends RuntimeException
{ // Exception for when a content is not found
	public ContentNotFoundException(){}
	public ContentNotFoundException(String message)
	{
		super(message);
	}
}

class ContentAlreadyDownloadedException extends RuntimeException
{ // Exception for when a content is already downloaded
	public ContentAlreadyDownloadedException(){}
	public ContentAlreadyDownloadedException(String message)
	{
		super(message);
	}
}

class InvalidContentTypeException extends RuntimeException
{ // Exception for when a content type is invalid
	public InvalidContentTypeException(){}
	public InvalidContentTypeException(String message)
	{
		super(message);
	}
}

class PlaylistAlreadyExistsException extends RuntimeException
{ // Exception for when a playlist already exists
	public PlaylistAlreadyExistsException(){}
	public PlaylistAlreadyExistsException(String message)
	{
		super(message);
	}
}

