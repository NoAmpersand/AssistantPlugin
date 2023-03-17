package Understanding;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import Object.Request;

public class requestHandler {

    public static void requestParser(Request request, String text, StanfordCoreNLP stanfordCoreNLP){

        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for (CoreLabel tok : coreLabelList) {
            System.out.println(tok.originalText() + "=" + tok.ner() + "=" + tok.tag());
            if(tok.ner().equals("I-LOC") && !tok.tag().equals("VERB") ){
                request.addToVilles(tok.originalText());
            }
            if(tok.ner().equals("I-PER") ){
                request.addToNomPropres(tok.originalText());
            }
            if(tok.tag().equals("NOUN") ){
                request.addToNomCommuns(tok.originalText());
            }

        }
        System.out.println(request.getNomCommuns());
        System.out.println(request.getVilles());
        System.out.println(request.getNomPropres());
    }
    public static void main(String[] args) {

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipelineApplication();

        String text = "Qui habite à Paris ?";

        Request request = new Request();

        requestParser(request, text, stanfordCoreNLP);

    }
}
