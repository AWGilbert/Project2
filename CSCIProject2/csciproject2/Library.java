package csciproject2;

import java.io.*;
import java.util.*;

public class Library 
{
	private ArrayList<Song> allSongs;
	private TreeSet<String> genres;

	// Default Constructor
	public Library () 
	{
		allSongs = new ArrayList<Song>();
		genres = new TreeSet<String>();
	}
	
	// Add Song to the Library
	public void add (Song song) 
	{
		if (!(allSongs.contains(song)))
		{
			allSongs.add(song);
		}
	}

	// Remove Song from Library
	public void remove (Song song) 
	{
		allSongs.remove(song);
	}
	
	// Displays content of Library
	public void display () 
	{
		Iterator<Song> iterator = this.getIterator();
		while (iterator.hasNext()) 
		{
			Song song = iterator.next();
			System.out.println(song);
		}
		
	}
	
	// Returns Collection Iterator
	public Iterator<Song> getIterator () 
	{
		return allSongs.iterator();
	}
	
	// Returns whether Library is Empty
	public boolean empty () 
	{
		return allSongs.isEmpty();
	}
	
	// Returns number of Songs in Library
	public int size () 
	{
		return allSongs.size();
	}
	
	//Return first element in Library
	public Song returnFirst () 
	{
		if (empty())
			return null;
		
		return allSongs.get(0);
	}
	
	// Combine two Libraries together.
	public Library addAll (Library rhs) 
	{
		if (rhs.empty())
			return this;
		Iterator<Song> iterator = rhs.getIterator();
		while (iterator.hasNext()) 
		{
			Song song = iterator.next();
			this.add(song);
		}
		
		return this;
	}
	
	// Returns whether or not Library contains song.
	public boolean contains (Song song)
	{
		if (allSongs.contains(song))
			return true;
		
		else
			return false;
	}
	
	// Finds Song based on ID
	public Song findSongByID (int id) 
	{
		Iterator<Song> iterator = this.getIterator();
		while (iterator.hasNext()) 
		{
			Song song = iterator.next();
			if (song.getID() == id) 
			{
				return song;
			}
		}
		return null;
	}
	
	// Finds Song(s) based on Artist
	public Library findSongByArtist (String artist) 
	{
		Library results = new Library();
		Iterator<Song> iterator = this.getIterator();

		// Iterate through allSongs
		while (iterator.hasNext())
		{
			Song song = iterator.next();

			// Find only values with matching artist name
			if (song.getArtist().equals(artist))
			{
				results.add(song);
			}
		}
		return results;
	}
	
	// Finds Song(s) based on Genre
	public Library findSongByGenre (String genre)
	{
		Library results = new Library();
		Iterator<Song> iterator = this.getIterator();

		// Iterate through allSongs
		while (iterator.hasNext())
		{
			Song song = iterator.next();

			// Find only values with matching artist name
			if (song.getGenre().equals(genre)) 
			{
				results.add(song);
			}
		}
		return results;
	}

	// Suggests Song, one input one output
	public Song suggestSong_1I1O (Song song) 
	{
		Library sameGenre = new Library();
		Library sameArtistandGenre = new Library();
		
		sameGenre.addAll(this.findSongByGenre(song.getGenre()));
		sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));
		
		if (sameArtistandGenre.size() > 1)
		{
			sameArtistandGenre.remove(song);
			return sameArtistandGenre.returnFirst();
		}
		
		else if (sameGenre.size() > 1)
		{
			sameGenre.remove(song);
			return sameGenre.returnFirst();
		}
		
		return song;
	}
	
	// Suggests Song, one input multiple output
	public Library suggestSong_1IMO (Song song)
	{
		Library sameGenre = new Library();
		Library sameArtistandGenre = new Library();
		
		sameGenre.addAll(this.findSongByGenre(song.getGenre()));
		sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));
		
		if (sameArtistandGenre.size() > 2)
		{
			sameArtistandGenre.remove(song);
			return sameArtistandGenre;
		}
		
		else if (sameGenre.size() > 2)
		{
			sameGenre.remove(song);
			return sameGenre;
		}
		
		else if (sameArtistandGenre.size() == 2)
		{
			sameArtistandGenre.remove(song);
			return sameGenre;
		}
		
		else if (sameGenre.size() == 2)
		{
			sameGenre.remove(song);
			return sameGenre;
		}
		
		return sameGenre;
	}

	// Suggests Song, multiple input one output
	public Song suggestSong_MI1O (Library library) 
	{
		Song na = new Song();
		Library sameGenre = new Library();
		
		Iterator<Song> iterator = library.getIterator();
		while (iterator.hasNext())
		{
			Song song = iterator.next();
			sameGenre.addAll(this.findSongByGenre(song.getGenre()));

		}
		
		Library sameArtistandGenre = new Library();
		
		Iterator<Song> iterator2 = library.getIterator();
		while (iterator2.hasNext())
		{
			Song song = iterator2.next();
			sameArtistandGenre.addAll(sameGenre.findSongByArtist(song.getArtist()));

		}
		
		Iterator<Song> iterator3 = library.getIterator();
		while (iterator3.hasNext())
		{
			Song song = iterator3.next();
			if (sameGenre.contains(song))
				sameGenre.remove(song);
			if (sameArtistandGenre.contains(song))
				sameArtistandGenre.remove(song);
		}
	
		if (sameArtistandGenre.size() > 0)
		{
			return sameArtistandGenre.returnFirst();
		}
		
		else if (sameGenre.size() > 0)
		{
			return sameGenre.returnFirst();
		}
		
		return na;
		
	}

	// Suggests Song, multiple input multiple output
	public Library suggestSong_MIMO (Library library) 
	{
		Library sameGenre = new Library();
		
		Iterator<Song> iterator = library.getIterator();
		while (iterator.hasNext())
		{
			Song song = iterator.next();
			sameGenre.addAll(this.findSongByGenre(song.getGenre()));

		}
		
		Library sameArtistandGenre = new Library();
		
		Iterator<Song> iterator2 = sameGenre.getIterator();
		while (iterator2.hasNext())
		{
			Song song = iterator2.next();
			sameArtistandGenre.addAll(sameGenre.addAll(findSongByArtist(song.getArtist())));

		}
		
		Iterator<Song> iterator3 = library.getIterator();
		while (iterator3.hasNext())
		{
			Song song = iterator3.next();
			if (sameGenre.contains(song))
				sameGenre.remove(song);
			if (sameArtistandGenre.contains(song))
				sameGenre.remove(song);
		}
	
		
		if (sameArtistandGenre.size() > 0)
		{
			return sameArtistandGenre;
		}
		
		else if (sameGenre.size() > 0)
		{
			return sameGenre;
		}
		
		return null;
		
	}
	
	// Returns array of all Genres(Strings)
	public String[] getGenres () 
	{
		String[] toReturn = new String[genres.size()];
		int index = 0;
		for (String s : genres) 
		{
			toReturn[index] = s;
			index++;
		}
		return toReturn;
	}
	
	// Read in the contents of a csv file to the collection.
	public void readFile (String fn) 
	{
		BufferedReader lineReader = null;
		try 
		{
			FileReader fr = new FileReader(fn);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine()) != null) 
			{
				String[] tokens = line.split(",");
				String genre = tokens[2];
				genres.add(genre);
				this.add((new Song(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Double.parseDouble(tokens[6]))));
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("There was a problem with the file reader, trying different read type...");
			try
			{
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fn.substring(1))));
				String line = null;
				while ((line = lineReader.readLine()) != null)
				{
					String[] tokens = line.split(",");
					String genre = tokens[2];
					genres.add(genre);					
					allSongs.add(new Song(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Double.parseDouble(tokens[6])));
				} 
			}
				catch (Exception e2)
			{
				System.err.println("There was a problem with the file reader. Either no such file or format error.");
			} 
				finally 
				{
				if (lineReader != null)
					try 
				{
						lineReader.close();
				}	
				catch (IOException e2)
				{
						System.err.println("could not close BufferedReader");
				}
			}			
		} 
		finally 
		{
			if (lineReader != null)
				try 
			{
					lineReader.close();
			} 	catch (IOException e) 
			{
					System.err.println("could not close BufferedReader");
			}
		}
	} // end of readFile method	
	
	// Make the contents of a Collection into a csv file.
	public void writeFile (String fn)
	{
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutFile = new BufferedWriter(fw);
			Iterator<Song> iterator = this.getIterator();
			
			// Use the iterator to print every person in the Collection, making use of Song's toString method.
			while (iterator.hasNext()) 
			{
				Song song = iterator.next();
				myOutFile.write(song.toString() + "\n");
			}
			
			// Close everything
			myOutFile.flush();
			myOutFile.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}

}