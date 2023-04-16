package ApiRequest;

import Interface.CalendarRequestInterface;
import Services.CalendarService;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;



import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;


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
    public void createEvent(String name, String day, int hour, String location, CalendarService calendarService, Calendar service) throws IOException, ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = day + " " + hour + ":00:00";
        Date dateTime = dateFormat.parse(dateTimeStr);


        Event event = new Event();
        event.setSummary(name);
        event.setLocation(location);
        event.setDescription("Event description for testing purposes");


        Date startDate = new Date();
        DateTime startDateTime = new DateTime(startDate);
        EventDateTime start = new EventDateTime().setDateTime(startDateTime);
        event.setStart(start);

        Date endDate = java.util.Calendar.getInstance().getTime();
        endDate.setTime(startDate.getTime() + 3600000);
        DateTime endDateTime = new DateTime(endDate);
        EventDateTime end = new EventDateTime().setDateTime(endDateTime);
        event.setEnd(end);

        service.events().insert(String.valueOf(service.calendarList().get("primary")), event).execute();
    }


}










