package Understanding;


import java.util.regex.Matcher;

import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;
import Object.Request;
import Services.Service;

public class ContactRequestHandler extends RequestHandler {
    private static final String REGEX_CONTACT_ADRESS = "\\b(vit|vis|vi|habite|habites|demeure|demeures)\\b";
    private static final String REGEX_CONTACT_BIRTHDAY = "\\b(naissance|naissances|naisance|naissansse|ne|nes|nees)\\b";
    private static final String REGEX_CONTACT_MAIL = "\\b(mail|mel|maile|mails)\\b";
    private static final String REGEX_CONTACT_PHONE = "\\b(numero|numro|telephone|telefone)\\b";


    @Override
    public String understandingRequestWithName(Request request){
        if(!request.getNomCommuns().isEmpty()) {
            String nomsCommuns = service.convertListToString(request.getNomCommuns());
            Matcher matcherMail = searchMatchRegex(nomsCommuns, REGEX_CONTACT_MAIL);
            Matcher matcherAdress = searchMatchRegex(nomsCommuns, REGEX_CONTACT_ADRESS);
            Matcher matcherPhone = searchMatchRegex(nomsCommuns, REGEX_CONTACT_PHONE);
            Matcher matcherBirthday = searchMatchRegex(nomsCommuns, REGEX_CONTACT_BIRTHDAY);
            if (matcherMail.find()) {
                return "mail contact";
            } else if(matcherAdress.find()){
                return "adresse contact";
            } else if(matcherPhone.find()){
                return "telephone contact";
            } else if (matcherBirthday.find()) {
                return "anniversaire contact";
            } else {
                return "Toutes les infos contact";
            }
        } else if (!request.getVerbes().isEmpty()) {
            String verbs = service.convertListToString(request.getVerbes());
            Matcher matcherBirthday = searchMatchRegex(verbs, REGEX_CONTACT_BIRTHDAY);
            Matcher matcherAdresse = searchMatchRegex(verbs, REGEX_CONTACT_ADRESS);
            if(matcherBirthday.find()){
                return "anniversaire contact";
            } else if (matcherAdresse.find()) {
                return "adresse contact";
            } else{
                return "Toutes les infos contact";
            }
        } else {
            return "";
        }
    }

    @Override
    public String understandingRequestWithLocation(Request request){
        String verbs = service.convertListToString(request.getVerbes());
        Matcher matcher = searchMatchRegex(verbs, REGEX_CONTACT_ADRESS);
        if(matcher.find()){
            return "contacts localisation";
        } else {
            return "contacts localisation";
        }
    }

    @Override
    public String understandingRequestWithNoun(Request request){
        return "";
    }
}
