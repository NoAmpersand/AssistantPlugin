package Understanding;

import Object.Request;
public class CalendarRequestHandler extends RequestHandler{

    private static final String REGEX_CALENDAR_NOMS_COMMUNS = "\\b(programme|programe|program|journee|journe|semaine|rdv|rendez)\\b";
    @Override
    protected String understandingRequestWithName(Request request) {
        return "Calendrier avec personne";
    }

    @Override
    protected String understandingRequestWithNoun(Request request) {
        return "Calendrier avec nom commun (cas le plus probable)";
    }

    @Override
    protected String understandingRequestWithLocation(Request request) {
        return "Calendrier avec ville";
    }
}
