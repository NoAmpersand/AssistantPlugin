package ApiRequest;

import Interface.CalendarRequestInterface;
import Services.CalendarService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;


import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarRequest implements CalendarRequestInterface {
    @Override
    public List<Event> getUpcommingEvents(Calendar service) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        return items;
    }


    @Override
    public List<Event> getDailyEvents(CalendarService calendarService, List<Event> items) throws IOException {
        List<Event> EventToRemove = new ArrayList<>();
        String dateLimit = calendarService.getLimitForDailyEvent();
        for(Event item : items){
            String dateOfTheEvent = item.getStart().getDateTime().toString();
            if(calendarService.firstDateIsAfterSecondDate(dateOfTheEvent,dateLimit)){
                    EventToRemove.add(item);
            }
        }

        for(Event item : EventToRemove){
            items.remove(item);
        }

        return items;
    }

    @Override
    public List<Event> getWeeklyEvents(CalendarService calendarService, List<Event> items) {
        List<Event> EventToRemove = new ArrayList<>();
        String dateLimit = calendarService.getLimitForWeeklyEvent();
        for(Event item : items){
            String dateOfTheEvent = item.getStart().getDateTime().toString();
            if(calendarService.firstDateIsAfterSecondDate(dateOfTheEvent,dateLimit)){
                EventToRemove.add(item);
            }
        }

        for(Event item : EventToRemove){
            items.remove(item);
        }

        return items;
    }

    @Override
    public Event getNextEvent(List<Event> items){
        return items.get(0);
    }

    @Override
    public Event getNextEventWithSomeone (List<Event> items, String name){
        String rdv1 = "Rendez-vous";
        String rdv2 = "Rendez vous";
        String rdv3 = "rdv";
        for(Event item : items){
            String title = item.getSummary().toUpperCase();
            if(title.contains(rdv1.toUpperCase()) || title.contains(rdv2.toUpperCase()) || title.contains(rdv3.toUpperCase())
                    && title.contains(name.toUpperCase())){
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean doIHaveAppointmentToday(List<Event> dailyItems){
        String rdv1 = "Rendez-vous";
        String rdv2 = "Rendez vous";
        String rdv3 = "rdv";
        for(Event item : dailyItems){
            String title = item.getSummary().toUpperCase();
            if(title.contains(rdv1.toUpperCase()) || title.contains(rdv2.toUpperCase()) || title.contains(rdv3.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String whatTimeIsIt(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        String[] split1 = ZonedDateTime.now().toString().split("T");
        return split1[1].split("\\.")[0];
    }

    @Override
    public void createEvent (String name, String day, int hour, String location, CalendarService calendarService, Calendar service) throws IOException {
        /*
        Event event = new Event();
        event.setSummary("Rendez-vous avec "+name);
        EventDateTime date = new EventDateTime();
        date.setDate(calendarService.getDate(day,hour,0,0));
        event.setStart(date);
        event.setLocation(location);

        service.events().insert("primary",event).execute();

        Event event = new Event()
                .setSummary("Rendez-vous avec "+name)
                .setLocation(location)
                .setDescription("rdv important");


        EventDateTime start = new EventDateTime()
                .setDateTime(calendarService.getDate(day,hour,0,0))
                .setTimeZone("Europe/Paris");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(calendarService.getDate(day,hour+1,0,0))
                .setTimeZone("Europe/Paris");
        event.setEnd(end);

        String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("lpage@example.com"),
                new EventAttendee().setEmail("sbrin@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
        */

    }









}
