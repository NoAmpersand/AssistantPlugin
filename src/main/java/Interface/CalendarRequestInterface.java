package Interface;

import Services.CalendarService;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CalendarRequestInterface {

    public abstract List<Event> getUpcommingEvents(Calendar service) throws IOException;
    public abstract List<Event> getDailyEvents(CalendarService calendarService, List<Event> items) throws IOException;
    public abstract List<Event> getWeeklyEvents(CalendarService calendarService, List<Event> items) throws IOException;
    public abstract Event getNextEvent(List<Event> items);
    public abstract Event getNextEventWithSomeone (List<Event> items, String name);
    public abstract boolean doIHaveAppointmentToday(List<Event> items);
    public abstract String whatTimeIsIt();

    public abstract void createEvent (String name, String day, int hour, String location,CalendarService calendarService, Calendar service) throws IOException, ParseException;



}
