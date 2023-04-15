package Interface;

import java.io.IOException;
import Object.EventModel;
import com.google.api.services.calendar.model.Event;

public interface EventCreationInterface {
    public Event createEvent(EventModel modelEvent) throws IOException;

    public void addSummary(EventModel eventModel, Event event);

    public void addDescription(EventModel eventModel, Event event);

    public void addLocation(EventModel eventModel, Event event);
    
    public void addStartDateTime(EventModel modelEvent, Event event);

    public void addEndDateTime(EventModel modelEvent, Event event);
}
