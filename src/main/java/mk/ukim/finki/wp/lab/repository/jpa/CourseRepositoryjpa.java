package mk.ukim.finki.wp.lab.repository.jpa;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepositoryjpa extends JpaRepository<Course,Long> {
    Course findCourseByCourseId(Long courseId);
    Course findByCourseIdAfter(Long id);
    void deleteByName(String name);
    List<Course> findAllByName(String name);
    //Optional<Course> deleteByName(String name);

    //Course save(Student student, Course course);
}