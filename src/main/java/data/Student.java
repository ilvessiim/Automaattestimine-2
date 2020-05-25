package data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends PersonImpl {
    public Student(String firstName, String fullName, String prefferedName, ZonedDateTime dateOfBirth, int age) {
        super(firstName, fullName, prefferedName, dateOfBirth, age);
    }

    List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public List<String> showAllTeachersNames() {
        List<String> teachersNames = new ArrayList<String>();

        for (Course course : this.courses) {
            teachersNames.add(course.getTeacher().getFullName());
        }

        return teachersNames;
    }


    @Override
    public String sayHello() {
        return "Hello Bstudent";
    }
}
