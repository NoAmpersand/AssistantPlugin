package Understanding;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Pipeline {

    private static final StanfordCoreNLP pipelineApplication;

    static {
        // Créer le pipeline une seule fois au lancement de l'application
        pipelineApplication = new StanfordCoreNLP("french");
    }

    public static StanfordCoreNLP getPipelineApplication(){
        return pipelineApplication;
    }
}
