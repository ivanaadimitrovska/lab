package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Optional<Student> findByUsername(String username);
    Optional<Student> save(String username, String password, String name, String surname, Grade grade);
    void  deleteByUsername(String username);
    void delete(Student student);
//    List<Student> padnati(Grade grade);
    void delete(String name);
}
