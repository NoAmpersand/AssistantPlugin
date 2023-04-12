package Understanding;

import Object.Request;

import java.util.regex.Matcher;

public class CalendarRequestHandler extends RequestHandler {

    private static final String REGEX_CALENDAR_PROGRAM = "\\b(programme|programe|program)\\b";
    private static final String REGEX_CALENDAR_SEE = "\\b(verrai|vois|verrais)\\b";
    private static final String REGEX_CALENDAR_TAKE = "\\b(prend|prends)\\b";
    private static final String REGEX_CALENDAR_DAY = "\\b(journee|journe|ajd|aujourd’hui)\\b";
    private static final String REGEX_CALENDAR_WEEK = "\\b(semaine)\\b";
    private static final String REGEX_CALENDAR_RDV = "\\b(rdv|rendez)\\b";
    private static final String REGEX_CALENDAR_NEXT = "\\b(prochain|prochaine)\\b";
    private static final String REGEX_CALENDAR_HOUR = "\\b(heure)\\b";

    @Override
    protected String understandingRequestWithName(Request request) {
        String nomsCommuns = service.convertListToString(request.getNomCommuns());
        String verbes = service.convertListToString(request.getVerbes());
        Matcher matcherSee = searchMatchRegex(verbes, REGEX_CALENDAR_SEE);
        Matcher matcherTake = searchMatchRegex(verbes, REGEX_CALENDAR_TAKE);
        if(matcherSee.find()){
            return "rdv contact";
        } else if (matcherTake.find()) {
            return "prendre rdv personne";
        }
        return "Calendrier avec personne";
    }

    @Override
    protected String understandingRequestWithNoun(Request request) {
        String nomsCommuns = service.convertListToString(request.getNomCommuns());
        String adjectifs = service.convertListToString(request.getAdjectifs());
        Matcher matcherProgram = searchMatchRegex(nomsCommuns, REGEX_CALENDAR_PROGRAM);
        Matcher matcherJournee = searchMatchRegex(nomsCommuns, REGEX_CALENDAR_DAY);
        Matcher matcherSemaine = searchMatchRegex(nomsCommuns, REGEX_CALENDAR_WEEK);
        Matcher matcherRdv = searchMatchRegex(nomsCommuns, REGEX_CALENDAR_RDV);
        Matcher matcherNext = searchMatchRegex(adjectifs, REGEX_CALENDAR_NEXT);
        Matcher matcherHeure = searchMatchRegex(nomsCommuns, REGEX_CALENDAR_HOUR);
        if(matcherProgram.find()){
            if(matcherJournee.find()){
                return "programme journée";
            } else if(matcherSemaine.find()){
                return "programme semaine";
            }
        } else if (matcherRdv.find()){
            if(matcherNext.find()){
                return "prochain rdv";
            } else if (matcherSemaine.find()){
                return "rdv semaine";
            } else {
                return "rdv ajd";
            }
        } else if (matcherHeure.find()) {
            return "heure actuelle";
        }
        return "Calendrier avec nom commun (cas le plus probable)";
    }

    @Override
    protected String understandingRequestWithLocation(Request request) {
        return "Calendrier avec ville";
    }
}
