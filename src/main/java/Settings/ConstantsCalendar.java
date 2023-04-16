package Settings;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

import java.util.Arrays;
import java.util.List;

public class ConstantsCalendar extends Constants {
    public static final List<String> SCOPES =
            Arrays.asList(CalendarScopes.CALENDAR_READONLY,CalendarScopes.CALENDAR);
}
