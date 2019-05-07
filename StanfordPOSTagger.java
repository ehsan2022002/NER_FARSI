import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class StanfordPOSTagger {

	public static void main(String[] args) {
		try {

			 // Initialize the tagger
	        MaxentTagger tagger = new MaxentTagger(
	                "Resources/persian.tagger");
	 
	        // The sample string
	        String sample = "—Ê“ ŒÊ»Ì »—«Ì ‘„« ¬—“Ê„‰œ„";
	 
	        // The tagged string
	        String tagged = tagger.tagString(sample);
	 
	        // Output the result
	        System.out.println(tagged);
	        						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
