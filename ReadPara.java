
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ReadPara {

	public List<String> ReadFiles(String FileName)
	{
		String strLine;					
		List<String> docs = new ArrayList<String>();
		
		try {
			
			FileInputStream fstream = new FileInputStream(FileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
			while ((strLine = br.readLine()) != null)   {
				
				docs.add(strLine);
				
			}
		
		fstream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return docs;				
	}
	
	List<String> stopwordlist = new ArrayList<String>();
	public void initStopWord()
	{
		stopwordlist = ReadFiles("resource/stopwords.txt");
				
	}
			  
	public String RemoveStopWord(String s)
	{
		/*String s="I love this phone, its super fast and there's so" +
	            " much new and cool things with jelly bean....but of recently I've seen some bugs.";
		*/
		String r = ""; 
	    String[] words = s.split(" ");
	    ArrayList<String> wordsList = new ArrayList<String>();	 
	    //load from file
	    if (stopwordlist.size() == 0)
	    	initStopWord();
	    
	    	
		for(String word : words)
		    {
		        String wordCompare = word.toUpperCase();
		        if(!stopwordlist.contains(wordCompare))
		        {
		        	if (word !=null)
		        		wordsList.add(word);
		        }
		    }
		 
		 
		 for (String str : wordsList){
		        //System.out.print(str+" ");
		        r = r + str +" "; 
		    }
		return r;		
	}
	
}

