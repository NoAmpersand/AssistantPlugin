package Instanciation;

import Interface.EventCreationInterface;
import Object.EventModel;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.IOException;



public class EventCreation implements EventCreationInterface {
    private Calendar calendar;

    public void EventCreation(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public Event createEvent(EventModel modelEvent) throws IOException {
        Event event = new Event();

        addSummary(modelEvent, event);
        addDescription(modelEvent, event);
        addLocation(modelEvent, event);
        addStartDateTime(modelEvent, event);
        addEndDateTime(modelEvent, event);

        return calendar.events().insert("primary", event).execute();
    }

    public void addSummary(EventModel eventModel, Event event) {
        if (eventModel.getSummary() != null) {
            event.setSummary(eventModel.getSummary());
        }
    }

    public void addDescription(EventModel eventModel, Event event) {
        if (eventModel.getDescription() != null) {
            event.setDescription(eventModel.getDescription());
        }
    }

    public void addLocation(EventModel eventModel, Event event) {
        if (eventModel.getLocation() != null) {
            event.setLocation(eventModel.getLocation());
        }
    }

    public void addStartDateTime(EventModel modelEvent, Event event) {
        if (modelEvent.getStartDateTime() != null) {
            DateTime dateTime = new DateTime(modelEvent.getStartDateTime().getValue());
            EventDateTime eventDateTime = new EventDateTime().setDateTime(dateTime);
            event.setStart(eventDateTime);
        }
    }

    public void addEndDateTime(EventModel modelEvent, Event event) {
        if (modelEvent.getEndDateTime() != null) {
            DateTime dateTime = new DateTime(modelEvent.getEndDateTime().getValue());
            EventDateTime eventDateTime = new EventDateTime().setDateTime(dateTime);
            event.setEnd(eventDateTime);
        }
    }

}


