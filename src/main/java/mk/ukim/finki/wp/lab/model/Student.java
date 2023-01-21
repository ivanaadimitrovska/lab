package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Grade grade;


    public Student(String username, String password, String name, String surname, Grade grade) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.grade=grade;
    }

    public Student() {

    }
}
