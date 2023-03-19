package Understanding;


import java.util.regex.Matcher;
import Object.Request;

public class ContactRequestHandler extends RequestHandler {
    private static final String REGEX_CONTACT_ADRESS = "\\b(vit|vis|vi|habite|habites|demeure|demeures)\\b";
    private static final String REGEX_CONTACT_BIRTHDAY = "\\b(naissance|naissances|naisance|naissansse|ne|nes|nees)\\b";
    private static final String REGEX_CONTACT_MAIL = "\\b(mail|mel|maile|mails)\\b";
    private static final String REGEX_CONTACT_PHONE = "\\b(numero|numro|telephone|telefone)\\b";

    @Override
    public String understandingRequestWithName(Request request){
        if(!request.getNomCommuns().isEmpty()) {
            String nomsCommuns = convertListToString(request.getNomCommuns());
            Matcher matcherMail = searchMatchRegex(nomsCommuns, REGEX_CONTACT_MAIL);
            Matcher matcherAdress = searchMatchRegex(nomsCommuns, REGEX_CONTACT_ADRESS);
            Matcher matcherPhone = searchMatchRegex(nomsCommuns, REGEX_CONTACT_PHONE);
            Matcher matcherBirthday = searchMatchRegex(nomsCommuns, REGEX_CONTACT_BIRTHDAY);
            if (matcherMail.find()) {
                return "On demande l'adresse mail d'un contact";
            } else if(matcherAdress.find()){
                return "L'adresse du contact";
            } else if(matcherPhone.find()){
                return "Le numéro de telephone du contact";
            } else if (matcherBirthday.find()) {
                return "La date d'anniversaire du contact";
            } else {
                return "Toutes les infos sur le contact";
            }
        } else if (!request.getVerbes().isEmpty()) {
            String verbs = convertListToString(request.getVerbes());
            Matcher matcherBirthday = searchMatchRegex(verbs, REGEX_CONTACT_BIRTHDAY);
            Matcher matcherAdresse = searchMatchRegex(verbs, REGEX_CONTACT_ADRESS);
            if(matcherBirthday.find()){
                return "La date d'anniversaire du contact";
            } else if (matcherAdresse.find()) {
                return "L'adresse du contact";
            } else{
                return "Toutes les infos sur le contact";
            }
        } else {
            return "Je n'ai pas compris veuillez reformuler svp (juste un nom)";
        }
    }

    @Override
    public String understandingRequestWithLocation(Request request){
        String verbs = convertListToString(request.getVerbes());
        Matcher matcher = searchMatchRegex(verbs, REGEX_CONTACT_ADRESS);
        if(matcher.find()){
            return "demande les contacts d'une localisation";
        } else {
            return "Surement les contacts d'une localisation";
        }
    }

    @Override
    public String understandingRequestWithNoun(Request request){
        return "Demande quelque chose dans le plugin contact";
    }
}
