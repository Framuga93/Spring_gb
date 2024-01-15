package ru.framuga.homework;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository(List<Student> students) {
        this.students = students;
        students.add(new Student("Student #1", "1"));
        students.add(new Student("Student #2", "1"));
        students.add(new Student("Student #3", "1"));
        students.add(new Student("Student #4", "2"));
        students.add(new Student("Student #5", "2"));
        students.add(new Student("Student #6", "2"));
        students.add(new Student("Student #7", "3"));
        students.add(new Student("Student #8", "4"));
        students.add(new Student("Student #9", "3"));
        students.add(new Student("Student #10", "3"));
    }

    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public List<Student> getByName(String name) {
        return students.stream()
                .filter(it -> it.getName().startsWith(name))
                .collect(Collectors.toList());
    }

    public Student getById(long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getGroupStudents(String groupName) {
        return students.stream()
                .filter(it -> it.getGroupName().equals(groupName))
                .collect(Collectors.toList());
    }

    public void addStudent(Student student) {
        long newId = students.stream()
                .reduce((first, second) -> second)
                .map(Student::getId)
                .get();
        student.setId(++newId);
        students.add(student);
    }

    public Student deleteStudent(long id){
        Student deleteStudent = getById(id);
        students.remove(deleteStudent);
        return deleteStudent;
    }
}
