package data;

import java.time.ZonedDateTime;

public class PersonImpl implements Person{
    private String firstName;
    private String fullName;
    private String prefferedName;
    private ZonedDateTime dateOfBirth;
    private int age;

    public PersonImpl(String firstName, String fullName, String prefferedName, ZonedDateTime dateOfBirth, int age) {
        this.firstName = firstName;
        this.fullName = fullName;
        this.prefferedName = prefferedName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrefferedName() {
        return prefferedName;
    }

    public void setPrefferedName(String prefferedName) {
        this.prefferedName = prefferedName;
    }


    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(ZonedDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String sayHello(){
        return null;
    }
}