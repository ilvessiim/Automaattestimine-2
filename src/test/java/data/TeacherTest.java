package data;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.time.ZonedDateTime;

public class TeacherTest {
    ZonedDateTime dateOfBirth = ZonedDateTime.parse("2001-01-01T00:00:00.000+00:00[UTC]");
    Teacher teacher = new Teacher("Karu", "Karu Jaan", "Mõmmi", dateOfBirth,19);


    @Test
    public void sayHello() {
        String expectedResult = "Hello TTeacher";
        assertEquals(teacher.sayHello(), expectedResult);
    }



}