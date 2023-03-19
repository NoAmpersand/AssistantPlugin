package Interface;

import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.SearchResponse;
import Object.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactRequestInterface {

    //récuperer un contact à partir de son nom
    public abstract SearchResponse findContactByName(String name, PeopleService peopleService) throws IOException, InterruptedException;

    //trouver tous les contacts habitants dans une ville
    public abstract List<Contact> findInhabitants(String city, PeopleService peopleService) throws IOException, InterruptedException;

    //Trouver les contact appartenant à une trannche d'age
    public abstract List<Contact> findAgeRange(PeopleService peopleService, int age) throws IOException, InterruptedException;

    //Renvoie tous les contacts
    public abstract List<Person> findAll(PeopleService peopleService) throws IOException;
}