package ApiRequest;

import Interface.ContactRequestInterface;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.SearchResponse;

import java.io.IOException;

public class ContactRequest implements ContactRequestInterface {

    @Override
    public SearchResponse findContactByName(String name, PeopleService peopleService) throws IOException, InterruptedException {
        // Warmup cache
        SearchResponse response = peopleService.people().searchContacts()
                .setQuery("")
                .setReadMask("names,emailAddresses")
                .execute();
        // Wait a few seconds
        Thread.sleep(5);

        // Send search request
        return peopleService.people().searchContacts()
                .setQuery(name)
                .setReadMask("names")
                .execute()
                ;
    }
}
