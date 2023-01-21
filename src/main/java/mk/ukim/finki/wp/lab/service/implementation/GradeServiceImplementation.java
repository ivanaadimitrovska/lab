package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImplementation implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImplementation(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade findByStudentAndCourse(Student s, Course c) {
        return gradeRepository.findByStudentAndCourse(s,c);
    }

    @Override
    public List<Grade> findAllByCourse(Course c) {
        return gradeRepository.findAllByCourse(c);
    }

    @Transactional
    @Override
    public Grade save(Grade g) {
        //gradeRepository.delete(g);
        gradeRepository.findAll().add(g);
        return gradeRepository.save(g);
    }

    @Transactional
    @Override
    public Optional<Grade> save1(String grade,Student student, Course course, LocalDateTime timestamp) {
        //gradeRepository.delete(g);
        //gradeRepository.findAll().add(g);
        return Optional.of(gradeRepository.save(new Grade(grade,student,course,timestamp)));
    }

    @Transactional
    @Override
    public Optional<Grade> save2(String grade,Course course, LocalDateTime timestamp) {
        //gradeRepository.delete(g);
        //gradeRepository.findAll().add(g);
        return Optional.of(gradeRepository.save(new Grade(grade,course,timestamp)));
    }


    @Override
    public void delete(Grade grade) {
        gradeRepository.delete(grade);
    }

    @Override
    public List<Grade> padnati(String ocena) {
        //List<Grade> grades=gradeRepository.findAllByGradeLessThan(grade);
        return gradeRepository.findAllByGradeLessThan(ocena);
    }

    @Override
    public void deleteGradeByCourse(Course c) {
        gradeRepository.deleteAllByCourse(c);
    }

    @Override
    public List<Grade> findBetween(LocalDateTime from, LocalDateTime to, Course c) {
        return gradeRepository.findByTimestampBetweenAndCourse(from, to, c);
    }
}
