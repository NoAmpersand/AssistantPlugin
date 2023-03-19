package Object;

import com.google.api.services.people.v1.model.Date;

public class Contact {
    private String Name;
    private String birthday;
    private String phoneNumber;
    private int age;
    private String Address;
    private String emailAddress;



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   public String getAddress(){
        return Address;
   }

   public void setAddress(String Address){
        this.Address =Address;
   }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getAll(){

    }
}
