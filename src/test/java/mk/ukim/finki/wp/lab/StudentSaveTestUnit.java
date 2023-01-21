package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentSaveTestUnit {

    @Mock
    private StudentRepositoryJpa studentRepositoryJpa;
    @Mock
    private GradeRepository gradeRepository;
    private StudentServiceImplementation studentServiceImplementation;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Grade grade=new Grade("9",
                new Course("Bazi", "BaziDesc", new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000"));
        Student student = new Student("username", "password", "name", "surname", grade);
        Mockito.when(this.studentRepositoryJpa.save(Mockito.any(Student.class))).thenReturn(student);
        Mockito.when(this.gradeRepository.save(Mockito.any(Grade.class))).thenReturn(grade);
        this.studentServiceImplementation = Mockito.spy(new StudentServiceImplementation(this.studentRepositoryJpa, this.gradeRepository));
    }

    @Test
    public void testSuccessSaveStudent(){

            Grade grade = this.gradeRepository.save(new Grade("9",
                    new Course("Bazi", "BaziDesc", new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000")));

            Student student = this.studentServiceImplementation.save("username",
                    "password", "name", "surname", new Grade("9", new Course("name", "description",
                   new Teacher(new TeacherFullname("teacherName", "teacherSurname"))),
                            LocalDateTime.parse("2022-12-02T23:22:00.000000"))).get();

            Mockito.verify(this.gradeRepository).save(grade);

            Mockito.verify(this.studentServiceImplementation).save("username",
                    "password", "name", "surname", new Grade("9", new Course("name", "description",
                            new Teacher(new TeacherFullname("teacherName", "teacherSurname"))),
                            LocalDateTime.parse("2022-12-02T23:22:00.000000")));

            Assert.assertNotNull("Student is null", student);

            Assert.assertEquals("username do not match", "username", student.getUsername());
            Assert.assertEquals("name do not match", "name", student.getName());
            Assert.assertEquals("surname do not match", "surname", student.getSurname());
            Assert.assertEquals("password do not match", "password", student.getPassword());
            Assert.assertEquals("grade do not match", grade.getGrade(),student.getGrade().getGrade());
        }


    @Test
    public void testNullUsername() {
        Assert.assertThrows("Invalid arguments exception",
                InvalidArgumentException.class,
                () -> this.studentServiceImplementation
                        .save(null, "password", "name", "surname",
                                new Grade("9",
                                        new Course("Bazi", "BaziDesc",
                                                new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000"))));
        Mockito.verify(this.studentServiceImplementation)
                .save(null, "password", "name", "surname",
                        new Grade("9",
                                new Course("Bazi", "BaziDesc",
                                        new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000")));
    }


    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("Invalid arguments exception",
                InvalidArgumentException.class,
                () -> this.studentServiceImplementation
                        .save(username, password, "name", "surname",
                                new Grade("9",
                                        new Course("Bazi", "BaziDesc",
                                                new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000"))));
        Mockito.verify(this.studentServiceImplementation)
                .save(username, password, "name", "surname",
                        new Grade("9",
                                new Course("Bazi", "BaziDesc",
                                        new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000")));
    }

    @Test
    public void testNullSurname() {
        String username = "username";
        String surname = null;
        Assert.assertThrows("Invalid arguments exception",
                InvalidArgumentException.class,
                () -> this.studentServiceImplementation
                        .save(username, "password", "name", null,
                                new Grade("9",
                                        new Course("Bazi", "BaziDesc",
                                                new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000"))));
        Mockito.verify(this.studentServiceImplementation)
                .save(username, "password", "name", null,
                        new Grade("9",
                                new Course("Bazi", "BaziDesc",
                                        new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000")));
    }

    @Test
    public void testNullName() {
        String username = "username";
        String name = null;
        Assert.assertThrows("Invalid arguments exception",
                InvalidArgumentException.class,
                () -> this.studentServiceImplementation
                        .save(username, "password", name, "surname",
                                new Grade("9",
                                        new Course("Bazi", "BaziDesc",
                                                new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000"))));
        Mockito.verify(this.studentServiceImplementation)
                .save(username, "password", name, "surname",
                        new Grade("9",
                                new Course("Bazi", "BaziDesc",
                                        new Teacher(new TeacherFullname("Ana", "Anevska"))), LocalDateTime.parse("2022-12-02T23:22:00.000000")));
    }

}
