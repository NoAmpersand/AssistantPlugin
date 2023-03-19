package Response;

import ApiRequest.ContactRequest;
import Instanciation.ContactCreation;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.SearchResult;
import Object.Contact;
import Object.Request;
import Services.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ContactResponse {

    ContactRequest contactRequest = new ContactRequest();
    ContactCreation contactCreation = new ContactCreation();
    Service service = new Service();
    public ContactResponse() throws GeneralSecurityException, IOException {
    }


    public void getResponse(String question, Request request, PeopleService people) throws IOException, InterruptedException {
        String nom = service.convertListToString(request.getNomPropres());
        String localisation = service.convertListToString(request.getVilles());
        int age = 0;
        switch (question){
            case "mail contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getEmailAddress());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;

            case "adresse contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getAddress());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;
            case "telephone contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getPhoneNumber());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;
            case "anniversaire contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        System.out.println(contact.getBirthday());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;
            case "Toutes les infos contact":
                if (contactRequest.findContactByName(nom, people).getResults() != null) {
                    for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                        Contact contact = contactCreation.createContact(result.getPerson());
                        contact.printAllInfo();
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;
            case "contacts localisation":
                if (!contactRequest.findInhabitants(localisation, people).isEmpty()) {
                    for (Contact contact : contactRequest.findInhabitants(localisation, people)) {
                        System.out.println(contact.getName());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }
                break;
            case "tranche d'age plus":
                if (!contactRequest.findAgeRange(people,age).isEmpty()) {
                    for (Contact contact : contactRequest.findAgeRange(people,age)) {
                        System.out.println(contact.getName());
                    }
                }
                else{
                    System.out.println("Aucun résultat");
                }

            case "":
                System.out.println("Désolé je n'ai pas compris");

        }
    }
}
