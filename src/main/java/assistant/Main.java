package assistant;

import ApiManager.ContactManager;
import ApiRequest.ContactRequest;
import Settings.Constants;
import View.AssistantInput;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.SearchResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
        ContactManager contactManager = new ContactManager();
        ContactRequest contactRequest = new ContactRequest();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService service =
                new PeopleService.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, contactManager.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(Constants.APPLICATION_NAME)
                        .build();

        if (contactRequest.findContactByName("Ken", service).getResults() != null) {
            for (SearchResult result : contactRequest.findContactByName("Ken", service).getResults()) {
                List<Name> names = result.getPerson().getNames();
                if (names != null && names.size() > 0) {
                    System.out.println("Name: " + result.getPerson().getNames().get(0)
                            .getDisplayName());
                } else {
                    System.out.println("No names available for connection.");
                }
            }
        } else {
            System.out.println("aucun resultat");
        }
    }
}
