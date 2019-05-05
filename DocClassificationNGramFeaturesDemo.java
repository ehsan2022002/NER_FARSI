//github ready
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import opennlp.tools.doccat.BagOfWordsFeatureGenerator;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.doccat.NGramFeatureGenerator;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
 
/**
* oepnnlp version 1.7.2
* Usage of NGram features for Document Classification in OpenNLP
* @author www.tutorialkart.com
*/
public class DocClassificationNGramFeaturesDemo {
 
    public static void main(String[] args) {
 
        try {
            // read the training data
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("Resources/fa_train_test.txt"));
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
 
            // define the training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
            params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
            
            // feature generators - N-gram feature generators
            FeatureGenerator[] featureGenerators = { new NGramFeatureGenerator(1,1),
                    new NGramFeatureGenerator(2,3) };
            DoccatFactory factory = new DoccatFactory(featureGenerators);
 
            // create a model from traning data
            DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);
            System.out.println("\nModel is successfully trained.");
 
            // save the model to local
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("Resources/fa_train_test-ngram.bin"));
            model.serialize(modelOut);
            System.out.println("\nTrained Model is saved locally at : "+"Resources/fa_train_test-ngram.bin");
 
            // test the model file by subjecting it to prediction
            DocumentCategorizer doccat = new DocumentCategorizerME(model);
            String[] docWords =  "فضای دانشگاه آزاد توسعه علمی خواهد یافت".split(" ");
            double[] aProbs = doccat.categorize(docWords);
 
            // print the probabilities of the categories
            System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            for(int i=0;i<doccat.getNumberOfCategories();i++){
                System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
            }
            System.out.println("---------------------------------");
 
            System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
        }
        catch (IOException e) {
            System.out.println("An exception in reading the training file. Please check.");
            e.printStackTrace();
        }
    }
} 