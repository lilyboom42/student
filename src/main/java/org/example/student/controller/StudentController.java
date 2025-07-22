package org.example.student.controller;

import org.example.student.model.Student;
import org.example.student.interfaces.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @GetMapping("/students/{id}")
    public String studentDetails(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "studentDetails";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchForm() {
        return "searchResults";
    }

    @PostMapping("/search")
    public String searchResults(@RequestParam String lastName, Model model) {
        List<Student> results = studentService.searchByLastName(lastName);
        model.addAttribute("results", results);
        return "searchResults";
    }
}
