package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, String> {
    List<Student> findAllByName(String text);

    Optional<Student> findByUsername(String username);
    void deleteByUsername(String username);
    void deleteAllByName(String name);

}
