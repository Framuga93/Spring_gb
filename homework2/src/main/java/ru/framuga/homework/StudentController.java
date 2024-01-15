package ru.framuga.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentRepository studentRepository;


    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.getAll();
    }

    @GetMapping(value = "/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentRepository.getById(id);
    }

    @GetMapping("/search")
    public List<Student> getStudentByName(@RequestParam("name") String name){
        return studentRepository.getByName(name);
    }

    @PutMapping
    public Student createStudent(@RequestBody Student student){
        studentRepository.addStudent(student);
        return student;
    }

    @DeleteMapping(value = "/{id}")
    public Student deleteStudent(@PathVariable long id){
        return studentRepository.deleteStudent(id);
    }
}

