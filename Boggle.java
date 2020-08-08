import java.io.*;
import java.util.*;


public class Boggle
{
	static String[][] board;
	static TreeSet<String> hit = new TreeSet<String>();
	static TreeSet<String> dictionary = new TreeSet<String>();

	public static void main( String args[] ) throws Exception
	{
		Scanner sc = new Scanner(new File(args[0]));
		board = loadBoard( args[1] );
		String w;

		while(sc.hasNextLine())
		{
			w = sc.nextLine();
			dictionary.add(w);
		}

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); 

		for(String s: hit)
			System.out.println(s);


	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{
		if(!dictionary.higher(word).startsWith(word))
		{


			return;
		}

		word += board[r][c];
		if(dictionary.contains(word) && word.length()>=3)
			hit.add(word);

		

		if ( r-1 >= 0 && board[r-1][c] != null )   
		{	String unMarked = board[r][c]; 
			board[r][c] = null; 
			dfs( r-1, c, word ); 
			board[r][c] = unMarked; 
		}

		
		if(r-1>=0 && c+1<board[r].length && board[r-1][c+1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r-1, c+1, word );
			board[r][c] = unMarked;
		}

	
		if(c+1<board[r].length && board[r][c+1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r, c+1, word );
			board[r][c] = unMarked;
		}

		// SE 
		if(r+1<board.length && c+1<board[r].length && board[r+1][c+1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r+1, c+1, word );
			board[r][c] = unMarked;
		}

		// S 
		if(r+1<board.length && board[r+1][c]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r+1, c, word );
			board[r][c] = unMarked;
		}

		// SW 
		if(r+1<board.length && c-1>=0 && board[r+1][c-1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r+1, c-1, word );
			board[r][c] = unMarked;
		}

		// W 
		if(c-1>=0 && board[r][c-1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r, c-1, word );
			board[r][c] = unMarked;
		}

		// NW 
		if(r-1>=0 && c-1>=0 && board[r-1][c-1]!=null)
		{
			String unMarked=board[r][c];
			board[r][c] = null;
			dfs( r-1, c-1, word );
			board[r][c] = unMarked;
		}

	} 
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD

} // END BOGGLE CLASS
