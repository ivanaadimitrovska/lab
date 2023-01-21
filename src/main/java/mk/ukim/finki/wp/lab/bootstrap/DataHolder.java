package mk.ukim.finki.wp.lab.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumeration.TypeEnumeration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
public class DataHolder {
    public static List<Course> courses=new ArrayList<>(5);
    public static List<Student> students=new ArrayList<>(5);
    public static List<Teacher> teachers=new ArrayList<>(5);

//    @PostConstruct
//    public void init(){
//        students.add(new Student("ana.anevska", "password_1", "Ana", "Anevska"));
//        students.add(new Student("petar.petrov", "password_2", "Petar", "Petrov" ));
//        students.add(new Student("marko.markov", "password_3", "Marko", "Markov"));
//        students.add(new Student("ivana.ivanovska", "password_4", "Ivana", "Ivanovska" ));
//        students.add(new Student("nikola.nikolovski", "password_5", "Nikola", "Nikolovski"));
//
//        Teacher teacher1=new Teacher(1L, "Teacher1", "teacher.1");
//        Teacher teacher2=new Teacher(2L, "Teacher2", "teacher.2");
//        Teacher teacher3=new Teacher(3L, "Teacher3", "teacher.3");
//        Teacher teacher4=new Teacher(4L, "Teacher4", "teacher.4");
//        Teacher teacher5=new Teacher(5L, "Teacher5", "teacher.5");
//        teachers.add(teacher1);
//        teachers.add(teacher2);
//        teachers.add(teacher3);
//        teachers.add(teacher4);
//        teachers.add(teacher5);
//
//
//        courses.add(new Course( "Operativni sistemi", "description2",
//                new LinkedList<>(Arrays.asList(new Student[]{students.get(2), students.get(3)})), teacher4));
//        courses.add(new Course( "Elektronska i mobilna trgovija", "description3",
//                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(4)})), teacher1));
//        courses.add(new Course( "Kompjuterski mrezhi", "description4",
//                new LinkedList<>(Arrays.asList(new Student[]{students.get(1), students.get(2)})), teacher3));
//        courses.add(new Course("Softversko inzhenerstvo", "description5",
//                new LinkedList<>(Arrays.asList(new Student[]{students.get(0), students.get(3)})), teacher2));
//        courses.add(new Course("Veb programiranje", "description1",
//                new LinkedList<>(Arrays.asList(new Student[]{students.get(1), students.get(4)})), teacher5));
//
//    }
}
