package Interface;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.SearchResponse;

import java.io.IOException;

public interface ContactRequestInterface {
    public abstract SearchResponse findContactByName(String name, PeopleService peopleService) throws IOException, InterruptedException;
}