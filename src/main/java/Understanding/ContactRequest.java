package Understanding;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactRequest extends UnderstandingResquest {

    public static void detectKeyword(String input) {
        String request = translateRequest(input);
        String regex = "\\b(appelle|habite|age)\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            switch (matcher.group()) {
                case "habite":
                    System.out.println("La question concerne la résidence de la personne.");
                    break;
                case "appelle":
                    System.out.println("La question concerne le nom de la personne.");
                    break;
                case "age":
                    System.out.println("La question concerne l'âge de la personne.");
                    break;
            }
        } else {
            System.out.println("Aucun mot clé détecté.");
        }
    }

    public static String translateRequest(String input){
        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    public static void main(String[] args) {
        detectKeyword("ou  habitê ce chien");
    }
}
