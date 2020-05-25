package data;


import java.time.ZonedDateTime;


public interface Person {
    String getFirstName();
    String getFullName();
    String getPrefferedName();
    ZonedDateTime getDateOfBirth();
    String sayHello();


}