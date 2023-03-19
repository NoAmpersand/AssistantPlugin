package ApiRequest;

import Instanciation.ContactCreation;
import Interface.ContactRequestInterface;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.SearchResponse;
import Object.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactRequest implements ContactRequestInterface {

    @Override
    public List<Person> findAll(PeopleService peopleService) throws IOException {
        ListConnectionsResponse response = peopleService.people().connections().list("people/me")
                .setPersonFields("names,emailAddresses,addresses,birthdays,phoneNumbers")
                .execute();
        List<Person> people = response.getConnections();
        return people;
    }

    @Override
    public SearchResponse findContactByName(String name, PeopleService peopleService) throws IOException, InterruptedException {
        // Warmup cache
        SearchResponse response = peopleService.people().searchContacts()
                .setQuery("")
                .setReadMask("names,emailAddresses,addresses,birthdays,phoneNumbers")
                .execute();
        // Wait a few seconds
        Thread.sleep(5);

        // Send search request
        return peopleService.people().searchContacts()
                .setQuery(name)
                .setReadMask("names,emailAddresses,addresses,birthdays,phoneNumbers")
                .execute()
                ;
    }

    @Override
    public List<Contact> findInhabitants(String city, PeopleService peopleService) throws IOException, InterruptedException {
        List<Person> persons = findAll(peopleService);
        List<Contact> contacts = new ArrayList<>();
        ContactCreation contactCreation = new ContactCreation();
        for (Person person : persons) {
            if (person.getAddresses() != null) {
                if (person.getAddresses().get(0).getCity().equals(city)) {
                    contacts.add(contactCreation.createContact(person));
                }
            }
        }

        return contacts;
    }

    @Override
    public List<Contact> findAgeRange(PeopleService peopleService, int age) throws IOException, InterruptedException {
        List<Person> persons = findAll(peopleService);
        List<Contact> contacts = new ArrayList<>();
        ContactCreation contactCreation = new ContactCreation();
        for (Person person : persons) {
            if (person.getBirthdays() != null) {
                Contact contact = contactCreation.createContact(person);
                if (contact.getAge() >= age) {
                    //System.out.println(person.getNames().get(0).getDisplayName());
                    contacts.add(contactCreation.createContact(person));
                }
            }
        }
        return contacts;
    }


}
