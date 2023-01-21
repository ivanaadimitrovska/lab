package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.TypeEnumeration;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class CourseRepository {


    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

    public Course findById(Long courseId){
        return DataHolder.courses.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return DataHolder.courses.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().get().getStudents();
    }

//    public Course  addStudentToCourse(Student student, Course course){
//        DataHolder.students.removeIf(r -> r.getUsername().equals(student.getUsername()));
//        DataHolder.students.add(student);
//        course.addStudent(student);
//        return course;
//    }

    public Optional<Course> save(String name, String description, Teacher teacher){
        DataHolder.courses.removeIf(r->r.getName().equals(name));
        DataHolder.courses.add(new Course(name, description, new LinkedList<>(Arrays.asList(DataHolder.students.get(0), DataHolder.students.get(3))), teacher));
        return Optional.of(new Course(name, description, new LinkedList<>(Arrays.asList(DataHolder.students.get(0), DataHolder.students.get(3))), teacher));
    }

    public void deleteById(Long id){
         DataHolder.courses.removeIf(r->r.getCourseId().equals(id));
    }
}
