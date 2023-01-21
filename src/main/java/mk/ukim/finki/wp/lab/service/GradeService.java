package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GradeService {
     List<Grade> findAll();
     Optional<Grade> findById(Long id);
     Grade findByStudentAndCourse(Student s, Course c);
     Grade save(Grade g);
     List<Grade> findAllByCourse(Course c);
     Optional<Grade> save1(String grade,Student student, Course course, LocalDateTime timestamp);
     Optional<Grade> save2(String grade, Course course, LocalDateTime timestamp);
     void deleteGradeByCourse(Course c);
     List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c);
     void delete(Grade grade);
     List<Grade> padnati(String ocena);
}
