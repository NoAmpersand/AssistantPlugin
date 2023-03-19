package Interface;

import com.google.api.services.people.v1.model.Person;
import Object.Contact;

public interface ContactCreationInterface {
    public abstract Contact createContact(Person person);

    public abstract void addName(Person person, Contact contact);

    public abstract void addBirthday(Person person, Contact contact);

    public abstract void addPhoneNumber(Person person, Contact contact);

    public abstract void addAge(Person person, Contact contact);

    public abstract void addAddress(Person person, Contact contact);

    public abstract void addEmailAdress(Person person, Contact contact);
}
