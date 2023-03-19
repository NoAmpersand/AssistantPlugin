package Object;

public class Contact {
    private String name;
    private String birthday;
    private String phoneNumber;
    private int age;
    private String address;
    private String emailAddress;



    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
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
        return address;
   }

   public void setAddress(String Address){
        this.address =Address;
   }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void printAllInfo(){
        if(this.name!=null){
            System.out.println("Nom : "+this.name);
        }
        if(this.birthday!=null){
            System.out.println("date de naissance : "+this.birthday);
        }
        if(this.age!=0){
            System.out.println("Age : "+this.age);
        }
        if(this.address !=null){
            System.out.println("Adresse : "+this.address);
        }
        if(this.emailAddress!=null){
            System.out.println("E-mail : "+this.emailAddress);
        }
        if(this.phoneNumber!=null){
            System.out.println("Numéro de téléphone : "+this.phoneNumber);
        }
    }
}
