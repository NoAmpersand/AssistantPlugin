package assistant;

import ApiManager.ContactManager;
import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;
import Settings.Constants;
import View.AssistantInput;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Address;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.SearchResponse;
import com.google.api.services.people.v1.model.SearchResult;
import Object.Contact;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLOutput;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {
        ContactManager contactManager = new ContactManager();
        ContactRequest contactRequest = new ContactRequest();
        ContactCreation contactCreation = new ContactCreation();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService service =
                new PeopleService.Builder(HTTP_TRANSPORT, Constants.JSON_FACTORY, contactManager.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(Constants.APPLICATION_NAME)
                        .build();

        if (contactRequest.findContactByName("naruto", service).getResults() != null) {
            for (SearchResult result : contactRequest.findContactByName("naruto", service).getResults()) {
                Contact malik = contactCreation.createContact(result.getPerson());
                System.out.println(malik.getName());
                System.out.println(malik.getEmailAddress());
                System.out.println(malik.getAddress());
                System.out.println(malik.getBirthday());
                System.out.println(malik.getPhoneNumber());
                System.out.println(malik.getAge());
            }
        }
                /*

                //System.out.println(malik.getAge());
                //System.out.println(malik.getBirthday());
                //System.out.println(result.getPerson().getBirthdays().get(0).getDate().getYear());

            }
        } else {
            System.out.println("aucun resultat");
        }


        //System.out.println(contactRequest.findInhabitants("Paris", service));


        //contactRequest.findInhabitants("Paris", service);


        for(SearchResult result :  contactRequest.findContactByName("jiraya",service).getResults()){
            System.out.println(result.getPerson().getBirthdays());
        }


        contactRequest.findAgeRange(service, 40);


        for(Contact contact : contactRequest.findAgeRange(service, 40)){
            System.out.println(contact.getName());
        }

 */
    }
}
