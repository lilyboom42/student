package org.example.student.service;

import org.example.student.interfaces.IStudentService;
import org.example.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L; // Générateur d'ID

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Student findById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
            students.add(student);
        } else {
            students.removeIf(s -> s.getId().equals(student.getId()));
            students.add(student);
        }
    } // ← ICI tu avais oublié cette accolade

    @Override
    public void delete(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public List<Student> searchByLastName(String lastName) {
        return students.stream()
                .filter(s -> s.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
