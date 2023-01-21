package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {

    private final StudentRepositoryJpa studentjpa;
    private final GradeRepository gradeRepository;

    public StudentServiceImplementation(StudentRepositoryJpa studentjpa, GradeRepository gradeRepository) {
        this.studentjpa = studentjpa;
        this.gradeRepository = gradeRepository;
    }


    @Override
    public List<Student> listAll() {
        return studentjpa.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentjpa.findAllByName(text);
    }


    @Transactional
    @Override
    public void deleteByUsername(String username) {
        this.studentjpa.deleteByUsername(username);
    }

    @Override
    public void delete(Student student) {
        studentjpa.delete(student);
    }

    @Override
    public void delete(String name) {
        studentjpa.deleteAllByName(name);
    }

//    @Override
//    public List<Student> padnati(Grade grade) {
//        return studentjpa.
//    }

    @Override
    public Optional<Student> findByUsername(String username) {
        return studentjpa.findByUsername(username);
    }

    @Transactional
    @Override
    public Optional<Student> save(String username, String password, String name, String surname, Grade grade) {
        //Grade grade =gradeRepository.findById(id).orElseThrow();
        Student student=new Student(username,password, name, surname, grade);
        if(username==null || surname==null || password==null || name==null){
            throw new InvalidArgumentException();
        }
        //studentjpa.delete(student);
        studentjpa.deleteByUsername(username);
        studentjpa.findAll().add(student);
        return Optional.of(studentjpa.save(new Student(username,password,name,surname, grade)));
    }
}
