package Interface;

import Object.EventModel;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;

public interface EventCreationInterface {


    EventModel createEvent(Event event) throws IOException;


    void addSummary(EventModel eventModel, Event event);

    void addDescription(EventModel eventModel, Event event);


    void addLocation(EventModel eventModel, Event event);


    void addStartDateTime(EventModel eventModel, Event event);


    void addEndDateTime(EventModel eventModel, Event event);

}
