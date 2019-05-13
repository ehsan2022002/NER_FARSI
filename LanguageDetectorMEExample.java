import java.io.*;

import opennlp.tools.langdetect.*;
import opennlp.tools.util.*;
/**
* Language Detector Example in Apache OpenNLP
*/
public class LanguageDetectorMEExample {
	
    private static LanguageDetectorModel model;
    
    public static void main(String[] args) throws IOException{
        // loading the training data to LanguageDetectorSampleStream
        LanguageDetectorSampleStream sampleStream = null;
        try {
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("resources/Doc_catSample.txt"));
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            sampleStream = new LanguageDetectorSampleStream(lineStream);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // training parameters
        TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 100);
        params.put(TrainingParameters.CUTOFF_PARAM, 5);
        params.put("DataIndexer", "TwoPass");
        params.put(TrainingParameters.ALGORITHM_PARAM, "NAIVEBAYES");
        // train the model
        try {
            model = LanguageDetectorME.train(sampleStream, params, new LanguageDetectorFactory());
            
            model.serialize(new File("resources/langdetect.bin"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Completed");
        // load the model
        
        
        File modelFile = new File("resources/langdetect.bin");    	
        LanguageDetectorModel trainedModel = new LanguageDetectorModel(modelFile);        	    	
        // load the model
        LanguageDetector ld = new LanguageDetectorME(trainedModel);
                
        //LanguageDetector ld = new LanguageDetectorME(model);
       
        // use model for predicting the language
        Language[] languages = ld.predictLanguages("estava em uma marcenaria na Rua Bruno");
        System.out.println("Predicted languages..");
        for(Language language:languages){
            // printing the language and the confidence score for the test data to belong to the language
            System.out.println(language.getLang()+"  confidence:"+language.getConfidence());
        }
    }
} 