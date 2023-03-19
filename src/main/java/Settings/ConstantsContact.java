package Settings;

import com.google.api.services.people.v1.PeopleServiceScopes;

import java.util.Arrays;
import java.util.List;

public class ConstantsContact extends Constants {
    public static final List<String> SCOPES =
            Arrays.asList(PeopleServiceScopes.CONTACTS_READONLY);
}
