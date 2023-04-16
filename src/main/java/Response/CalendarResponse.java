package Response;

import ApiRequest.CalendarRequest;
import Exceptions.InvalidQueryException;
import Exceptions.NoResultException;
import Instanciation.ContactCreation;
import Instanciation.EventCreation;
import Services.CalendarService;
import Services.Service;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.people.v1.PeopleService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import Object.Request;
import Object.EventModel;

public class CalendarResponse {
    public void getResponse(String question, Request request,  Calendar service) throws IOException, InterruptedException, ParseException {
        CalendarRequest calendarRequest = new CalendarRequest();
        CalendarService calendarService = new CalendarService();
        EventCreation eventCreation = new EventCreation();
        Service regularService = new Service();
        List<Event> upcommingEvents = calendarRequest.getUpcommingEvents(service);
        try {
            switch (question) {
                case "programme journée":
                    List<Event> dailyEvents = calendarRequest.getDailyEvents(calendarService, upcommingEvents);
                    if (!dailyEvents.isEmpty()) {
                        for (Event item : dailyEvents) {
                            EventModel event = eventCreation.createEvent(item);
                            System.out.println(event.getSummary());
                        }
                    } else {
                        throw new NoResultException("Aucun événement");
                    }
                    break;
                case "programme semaine":
                    List<Event> weeklyEvent = calendarRequest.getWeeklyEvents(calendarService, upcommingEvents);
                    if (!weeklyEvent.isEmpty()) {
                        for (Event item : weeklyEvent) {
                            EventModel event = eventCreation.createEvent(item);
                            System.out.println(event.getSummary());
                        }
                    } else {
                        throw new NoResultException("Aucun événement");
                    }
                    break;
                case "rdv ajd":
                    List<Event> items = calendarRequest.getDailyEvents(calendarService, upcommingEvents);
                    if (calendarRequest.doIHaveAppointmentToday(items)) {
                        System.out.println("oui");
                    } else {
                        System.out.println("non");
                    }
                    break;
                case "rdv contact":
                    if (calendarRequest.getNextEventWithSomeone(upcommingEvents, regularService.convertListToString(request.getNomPropres())) != null) {
                        Event event = calendarRequest.getNextEventWithSomeone(upcommingEvents, regularService.convertListToString(request.getNomPropres()));
                        EventModel item = eventCreation.createEvent(event);
                        System.out.println(item.getStartDateTime());
                    } else {
                        throw new NoResultException("rdv");
                    }
                    break;

                case "prendre rdv personne":
                    throw new InvalidQueryException("Par soucis de droit d'accés nous ne sommes pas en mesures de répondre à votre requête");
                case "heure actuelle":
                    System.out.println(calendarRequest.whatTimeIsIt());
                    break;
                case "prochain rdv":
                    Event event = calendarRequest.getNextEvent(upcommingEvents);
                    EventModel item = eventCreation.createEvent(event);
                    if (!upcommingEvents.isEmpty()) {
                        System.out.println(item.getSummary() + " " + item.getStartDateTime());
                    } else {
                        throw new InvalidQueryException("Pas de rendez-vous programmé");
                    }
                    break;
            }
        }catch (InvalidQueryException | NoResultException e){
            System.out.println(e.getMessage());
        }

    }
}
