package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherFullname implements Serializable {
    String name;
    String surname;

    public TeacherFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
