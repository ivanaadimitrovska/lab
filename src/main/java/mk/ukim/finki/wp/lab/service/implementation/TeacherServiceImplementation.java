package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullname;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImplementation implements TeacherService {

    private final TeacherRepositoryJpa teacherjpa;

    public TeacherServiceImplementation(TeacherRepositoryJpa teacherjpa) {
        this.teacherjpa = teacherjpa;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherjpa.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherjpa.findById(id).orElseThrow();
    }

    @Override
    public Teacher addTeacher(String name, String surname) {
        return teacherjpa.save(new Teacher(new TeacherFullname(name, surname)));
    }
}
