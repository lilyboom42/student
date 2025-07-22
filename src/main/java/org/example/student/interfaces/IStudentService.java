package org.example.student.interfaces;

import org.example.student.model.Student;
import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    Student findById(Long id);
    void save(Student student);
    void delete(Long id);
    List<Student> searchByLastName(String lastName);
}
