package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public List<Teacher> findAll();
    public Teacher findById(Long id);
    Teacher addTeacher(String name, String surname);
}
