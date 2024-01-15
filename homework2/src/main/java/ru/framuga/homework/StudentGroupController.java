package ru.framuga.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class StudentGroupController {

    private StudentRepository studentRepository;


    @Autowired
    public StudentGroupController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/{groupName}/students")
    public List<Student> getGroupStudents(@PathVariable String groupName){
        return studentRepository.getGroupStudents(groupName);
    }

}
