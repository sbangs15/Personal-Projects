//Sarthak Banglorewala 501164299
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	//returns title
	public String getTitle()
	{
		return title;
	}
	//sets title
	public void setTitle(String title)
	{
		this.title = title;
	}
	//add content to content list 
	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	//return contents list 
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}
	//set contents list
	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		//loop that goes through content list
		for(int i = 0; i < contents.size(); i++)
		{
			System.out.print(i+1 + ". ");// print the number (i +1) because it starts at 1 
			contents.get(i).printInfo();//print the info for each item in the list 
			System.out.println();//prints next content on new line
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		//loop that goes through contents list
		for(int i = 0; i < contents.size(); i++)
		{
			contents.get(i).play(); //plays all content in contents list
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if(contains(index))//calls contains method and check if the index is in the list 
		{
			contents.get(index-1).play();//plays the content at the index -1 (-1 because indexing starts at 1 for user)
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();//checks if the index is valid(greater than 1 and less than the size of the list)
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist playlist2 = (Playlist) other;//make new playlist object and cast Playlist to other to compare 

		return (this.getTitle()).equalsIgnoreCase(playlist2.getTitle());//checks if the playlists have the same title, returns true if same, false if not
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return; //checks if the index does not exist
		contents.remove(index-1);// if it exists remove index -1 because indexing starts at 1 to user 
	}
	
	
}
