package Response;

import ApiManager.ContactManager;
import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;
import Settings.Constants;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.SearchResult;
import Object.Contact;
import Object.Request;
import Services.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ContactResponse {
    ContactManager contactManager = new ContactManager();
    ContactRequest contactRequest = new ContactRequest();
    ContactCreation contactCreation = new ContactCreation();
    Service service = new Service();
    public ContactResponse() throws GeneralSecurityException, IOException {
    }

    public void getResponse(String question, Request request, PeopleService people) throws IOException, InterruptedException {
        String nom = service.convertListToString(request.getNomPropres());
        String localisation = service.convertListToString(request.getVilles());
        switch (question){
            case "mail contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getEmailAddress());
                    }
                }
                break;

            case "adresse contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getAddress());
                    }
                }
                break;
            case "telephone contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getPhoneNumber());
                    }
                }
                break;
            case "anniversaire contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getBirthday());
                    }
                }
                break;
            case "Toutes les infos contact":
                break;
            case "contacts localisation":
                if (contactRequest.findInhabitants(localisation, people) != null) {
                    for (Contact contact : contactRequest.findInhabitants(localisation, people)) {
                        System.out.println(contact.getName());
                    }
                }

                break;

        }
    }
}
