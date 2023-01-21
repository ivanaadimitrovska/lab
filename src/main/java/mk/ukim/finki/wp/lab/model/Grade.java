package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grade;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH%3Amm")
    private LocalDateTime timestamp;
    //2022-12-01T00%3A06

    public Grade(String grade,Student student, Course course, LocalDateTime timestamp) {
        this.grade=grade;
        this.student = student;
        this.course = course;
        this.timestamp = timestamp;
    }
    public Grade(String grade, Course course, LocalDateTime timestamp) {
        this.grade=grade;
        this.course = course;
        this.timestamp = timestamp;
    }

    public Grade() {

    }
}
