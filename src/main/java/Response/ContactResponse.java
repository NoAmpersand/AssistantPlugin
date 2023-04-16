package Response;

import ApiRequest.ContactRequest;
import Exceptions.InvalidQueryException;
import Exceptions.NoResultException;
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


    public void getResponse(String question, Request request, PeopleService people) throws IOException, InterruptedException, NoResultException, InvalidQueryException {
        String nom = service.convertListToString(request.getNomPropres());
        String localisation = service.convertListToString(request.getVilles());
        int age = request.getNombres();
        try {
            switch (question) {
                case "mail contact":
                    if (contactRequest.findContactByName(nom, people).getResults() != null) {
                        for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                            Contact contact = contactCreation.createContact(result.getPerson());
                            System.out.println(contact.getEmailAddress());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;

                case "adresse contact":
                    if (contactRequest.findContactByName(nom, people).getResults() != null) {
                        for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                            Contact contact = contactCreation.createContact(result.getPerson());
                            System.out.println(contact.getAddress());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;
                case "telephone contact":
                    if (contactRequest.findContactByName(nom, people).getResults() != null) {
                        for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                            Contact contact = contactCreation.createContact(result.getPerson());
                            System.out.println(contact.getPhoneNumber());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;
                case "anniversaire contact":
                    if (contactRequest.findContactByName(nom, people).getResults() != null) {
                        for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                            Contact contact = contactCreation.createContact(result.getPerson());
                            System.out.println(contact.getBirthday());
                            System.out.println(contact.getAge());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;
                case "Toutes les infos contact":
                    if (contactRequest.findContactByName(nom, people).getResults() != null) {
                        for (SearchResult result : contactRequest.findContactByName(nom, people).getResults()) {
                            Contact contact = contactCreation.createContact(result.getPerson());
                            contact.printAllInfo();
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;
                case "contacts localisation":
                    if (!contactRequest.findInhabitants(localisation, people).isEmpty()) {
                        for (Contact contact : contactRequest.findInhabitants(localisation, people)) {
                            System.out.println(contact.getName());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;
                case "tranche d'age plus":
                    if (!contactRequest.findAgeRange(people, age).isEmpty()) {
                        for (Contact contact : contactRequest.findAgeRange(people, age)) {
                            System.out.println(contact.getName());
                        }
                    } else {
                        throw new NoResultException("Aucun résultat");
                    }
                    break;

                case "":
                    throw new InvalidQueryException("Désolé je n'ai pas compris");

            }
        }catch (InvalidQueryException | NoResultException e){
            System.out.println(e.getMessage());
        }
    }

}
