package Instanciation;

import Interface.EventCreationInterface;
import Object.EventModel;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

public class EventCreation implements EventCreationInterface {

    @Override
    public EventModel createEvent(Event event) {
        EventModel eventModel = new EventModel();
        addSummary(eventModel, event);
        addDescription(eventModel, event);
        addLocation(eventModel, event);
        addStartDateTime(eventModel, event);
        addEndDateTime(eventModel, event);
        return eventModel;
    }

    public void addSummary(EventModel eventModel, Event event) {
        if (event.getSummary() != null) {
            eventModel.setSummary(event.getSummary());
        }
    }

    public void addDescription(EventModel eventModel, Event event) {
        if (event.getDescription() != null) {
            eventModel.setDescription(event.getDescription());
        }
    }

    public void addLocation(EventModel eventModel, Event event) {
        if (event.getLocation() != null) {
            eventModel.setLocation(event.getLocation());
        }
    }

    public void addStartDateTime(EventModel eventModel, Event event) {
        if (event.getStart() != null && event.getStart().getDateTime() != null) {
            DateTime dateTime = new DateTime(event.getStart().getDateTime().getValue());
            eventModel.setStartDateTime(dateTime);
        }
    }

    public void addEndDateTime(EventModel eventModel, Event event) {
        if (event.getEnd() != null && event.getEnd().getDateTime() != null) {
            DateTime dateTime = new DateTime(event.getEnd().getDateTime().getValue());
            eventModel.setEndDateTime(dateTime);
        }
    }

//        public static void main(String[] args) {
//
//            Event event = new Event();
//            event.setSummary("Sample event");
//            event.setDescription("This is a sample event created for testing purposes");
//            event.setLocation("New York, NY");
//            event.setStart(new EventDateTime().setDateTime(new DateTime("2023-04-17T10:00:00-04:00")));
//            event.setEnd(new EventDateTime().setDateTime(new DateTime("2023-04-17T12:00:00-04:00")));
//
//
//            EventCreation eventCreation = new EventCreation();
//            EventModel eventModel = eventCreation.createEvent(event);
//
//
//            System.out.println("Summary: " + eventModel.getSummary());
//            System.out.println("Description: " + eventModel.getDescription());
//            System.out.println("Location: " + eventModel.getLocation());
//            System.out.println("Start time: " + eventModel.getStartDateTime());
//            System.out.println("End time: " + eventModel.getEndDateTime());
//        }



}
