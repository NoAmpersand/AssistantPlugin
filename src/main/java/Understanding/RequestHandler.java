package Understanding;

import Services.Service;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Object.Request;

public abstract class RequestHandler {

    protected abstract String understandingRequestWithName(Request request);
    protected abstract String understandingRequestWithNoun(Request request);
    protected abstract String understandingRequestWithLocation(Request request);

    protected Service service = new Service();

    public String normalizeRequest(String input){
        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    public Matcher searchMatchRegex(String requestType, String regex){
        requestType = normalizeRequest(requestType);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(requestType);
    }

    public void requestParser(Request request, String text, StanfordCoreNLP stanfordCoreNLP){
        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for (CoreLabel word : coreLabelList) {
            if(word.tag().equals("VERB") || word.tag().equals("AUX") ){
                request.addToVerbes(word.originalText());
            }
            else if(word.ner().equals("I-LOC")){
                request.addToVilles(word.originalText());
            }
            else if(word.ner().equals("I-PER") ){
                request.addToNomPropres(word.originalText());
            }
            else if(word.tag().equals("NOUN") ){
                request.addToNomCommuns(word.originalText());
            }
        }
    }

    public String understandingRequest(Request request){
        if(!request.getNomPropres().isEmpty()) {
            return understandingRequestWithName(request);
        }
        else if(!request.getNomCommuns().isEmpty()){
            return understandingRequestWithNoun(request);
        }
        else if(!request.getVilles().isEmpty()){
            return understandingRequestWithLocation(request);
        }
        return "";
    }

}
