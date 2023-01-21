package mk.ukim.finki.wp.lab.model;

import lombok.*;
import mk.ukim.finki.wp.lab.model.enumeration.TypeEnumeration;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Student> students;
    @ManyToOne(fetch = FetchType.EAGER)
    Teacher teacher;
    @Enumerated(value = EnumType.STRING)
    TypeEnumeration type;

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }


    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public Course() {

    }

//    public Student addStudent(Student student) {
//        students.removeIf(student1 -> student1.getUsername().equals(student.getUsername()));
//        students.add(student);
//        return student;
//    }
}