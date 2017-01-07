package edu.msc.opennlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caldama on 11/29/2016.
 */
//Testing from https://opennlp.apache.org/documentation/1.6.0/manual/opennlp.html
//Models to download: http://opennlp.sourceforge.net/models-1.5/

@RunWith(SpringJUnit4ClassRunner.class)
public class BasicTests {

    public static final String PHRASE_TEST = "Pierre Vinken, 61 years old, and Marcelo Caldas will join the board as a nonexecutive director Nov. 29. Mr. Vinken is " +
            "chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years " +
            "old and former chairman of Consolidated Gold Fields P.L.C., was named a director of this " +
            "British industrial conglomerate.";

    @Test
    public void testTokenization() {
        InputStream modelIn = null;
        try {
            modelIn = new FileInputStream("src/test/resources/en-token.bin");
            TokenizerModel model = new TokenizerModel(modelIn);
            Tokenizer tokenizer = new TokenizerME(model);
            String tokens[] = tokenizer.tokenize(PHRASE_TEST);
            for( String t: tokens) {
                System.out.println("t = " + t);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    @Test
    public void testSentenceDetection() throws FileNotFoundException {
        InputStream modelIn = new FileInputStream("src/test/resources/en-sent.bin");


        try {
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            String sentences[] = sentenceDetector.sentDetect(PHRASE_TEST);
            for (String s: sentences) {
                System.out.println("s:" + s);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    @Test
    public void testNameEntityRecognizer() throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream modelIn = new FileInputStream("src/test/resources/en-ner-person.bin");
        InputStream sentencemodelIn = new FileInputStream("src/test/resources/en-sent.bin");
        InputStream tokenmodelIn = new FileInputStream("src/test/resources/en-token.bin");

        try {
            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
            NameFinderME nameFinder = new NameFinderME(model);

            SentenceModel sentencemodel = new SentenceModel(sentencemodelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentencemodel);
            String sentences[] = sentenceDetector.sentDetect(PHRASE_TEST);

            TokenizerModel tokenmodel = new TokenizerModel(tokenmodelIn);
            Tokenizer tokenizer = new TokenizerME(tokenmodel);

            for (String s: sentences) {
                String tokens[] = tokenizer.tokenize(s);
                Span nameSpans[] = nameFinder.find(tokens);
                if (nameSpans != null && nameSpans.length > 0) {
                    for (Span sp : nameSpans) {
                        System.out.print("name: ");
                        for (int idx = sp.getStart(); idx < sp.getEnd(); idx++) {
                            System.out.print(tokens[idx] + ", ");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("No names found in sentence -> " + s);
                }
                // do something with the names
            }
            long endTime = System.currentTimeMillis();
            System.out.println("process took: " + (endTime - startTime) +  "ms.");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    @Test
    public void testPOSAndLemmatization() throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream sentencemodelIn = new FileInputStream("src/test/resources/en-sent.bin");
        InputStream tokenmodelIn = new FileInputStream("src/test/resources/en-token.bin");

        InputStream modelIn = new FileInputStream("src/test/resources/en-pos-maxent.bin");

        try {
            POSModel posmodel = new POSModel(modelIn);

            SentenceModel sentencemodel = new SentenceModel(sentencemodelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentencemodel);
            String[] sentences = sentenceDetector.sentDetect(PHRASE_TEST);

            TokenizerModel tokenmodel = new TokenizerModel(tokenmodelIn);
            Tokenizer tokenizer = new TokenizerME(tokenmodel);

            POSTaggerME tagger = new POSTaggerME(posmodel);

            for (String s: sentences) {
                String[] tokens = tokenizer.tokenize(s);
                String[] posTags = tagger.tag(tokens);
                int i = 0;
                for (String t: posTags) {
                    System.out.println(tokens[i++] + ": " +  "t = (" + t + ")" + PenTreeBankDef.getDescription(t));

                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("process took: " + (endTime - startTime) +  "ms.");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                }
                catch (IOException e) {
                }
            }
        }
    }
}
