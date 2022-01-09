package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudents(Student student) {
    Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
    if (studentByEmail.isPresent()) {
      throw new RuntimeException("Email is taken!");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    Boolean isExist = studentRepository.existsById(studentId);
  }
}
