package Instanciation;
import Interface.ContactCreationInterface;
import Interface.ContactServiceInterface;
import Object.Contact;
import Services.ContactService;
import com.google.api.services.people.v1.model.Person;

public class ContactCreation implements ContactCreationInterface {
    ContactService contactService = new ContactService();

    @Override
    public Contact createContact(Person person){
        Contact contact = new Contact();
        addName(person, contact);
        addBirthday(person, contact);
        addPhoneNumber(person, contact);
        addAddress(person, contact);
        addEmailAdress(person, contact);
        addAge(person, contact);

        return contact;
    }

    @Override
    public void addName(Person person, Contact contact){
        if(person.getNames()!=null) {
            contact.setName(person.getNames().get(0).getDisplayName());
        }
    }
    @Override
    public void addBirthday(Person person, Contact contact){
        if(person.getBirthdays()!=null) {
            contact.setBirthday(person.getBirthdays().get(0).getText());
        }
    }
    @Override
    public void addPhoneNumber(Person person, Contact contact) {
        if (person.getPhoneNumbers() != null){
            contact.setPhoneNumber(person.getPhoneNumbers().get(0).getValue());
        }
    }
    @Override
    public void addAge(Person person, Contact contact){
        if(person.getBirthdays()!=null) {
            contact.setAge(contactService.calculateAge(person.getBirthdays().get(0).getText()));
        }
    }
    @Override
    public void addAddress(Person person, Contact contact){
        if(person.getAddresses()!=null) {
            contact.setAddress(person.getAddresses().get(0).getFormattedValue());
        }
    }
    @Override
    public void addEmailAdress(Person person, Contact contact){
        if(person.getEmailAddresses()!=null) {
            contact.setEmailAddress(person.getEmailAddresses().get(0).getValue());
        }
    }

}
