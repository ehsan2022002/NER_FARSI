import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.ml.EventTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;


public class SentenceDetectorTrainingExample {

	public static void main(String[] args) {
		try {
			new SentenceDetectorTrainingExample().trainSentDectectModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method generates s custom model file for sentence detection, in directory "custom_models".
	 * The training data used is "trainingDataSentences.txt". Training data contains a sentence per line in the text file.
	 * @throws IOException
	 */
	public void trainSentDectectModel() throws IOException {
		// directory to save the model file that is to be generated, create this directory in prior
		File destDir = new File("Resources"); 

		// training data
		InputStreamFactory in = new MarkableFileInputStreamFactory(new File("Resources/fa-sentance2.txt"));

		// parameters used by machine learning algorithm, Maxent, to train its weights,  PERCEPTRON  MAXENT
		TrainingParameters mlParams = new TrainingParameters();		
	    mlParams.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");
	    mlParams.put(TrainingParameters.TRAINER_TYPE_PARAM, EventTrainer.EVENT_VALUE);
	    mlParams.put(TrainingParameters.ITERATIONS_PARAM, 100);
	    mlParams.put(TrainingParameters.CUTOFF_PARAM, 3); 
		
		// train the model
		SentenceModel sentdetectModel = SentenceDetectorME.train(
				"fa",
				new SentenceSampleStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8)),
				true,
				null,
				mlParams);

		// save the model, to a file, "en-sent-custom.bin", in the destDir : "custom_models"
		File outFile = new File(destDir,"fa-sent-custom.bin"); 
		FileOutputStream outFileStream = new FileOutputStream(outFile); 
		sentdetectModel.serialize(outFileStream);  

		// loading the model
		SentenceDetectorME sentDetector = new SentenceDetectorME(sentdetectModel); 

		// detecting sentences in the test string
		String testString = ("سلام یک روز خوب میآید . بهتر از دیروز");
		System.out.println("\nTest String: "+testString);
		String[] sents = sentDetector.sentDetect(testString);
		System.out.println("---------Sentences Detected by the SentenceDetector ME class using the generated model-------");
		for(int i=0;i<sents.length;i++){
			System.out.println("Sentence "+(i+1)+" : "+sents[i]);
		}
		
		System.out.println("ok done");
	} 
	

	
}