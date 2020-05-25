package data;

import java.time.ZonedDateTime;

public class Teacher extends PersonImpl {
    public Teacher(String firstName, String fullName, String prefferedName, ZonedDateTime dateOfBirth, int age) {
        super(firstName, fullName, prefferedName, dateOfBirth, age);
    }

    @Override
    public String sayHello() {
        return "Hello TTeacher";
    }
}