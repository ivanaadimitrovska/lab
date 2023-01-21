package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {
    List<Teacher> teachers= DataHolder.teachers;

    public List<Teacher> findAll(){
        return teachers;
    }

    public Teacher findById(Long id){
        return DataHolder.teachers.stream().filter(r -> r.getId().equals(id)).findFirst().get();
    }
}
