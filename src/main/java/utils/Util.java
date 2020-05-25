package utils;

import data.Student;

import java.util.List;

public class Util {
    public Util() {
    }

    public static void printStudents(List<Student> students){
        for(Student s: students){
            System.out.println(s.getPrefferedName());
        }
    }
}