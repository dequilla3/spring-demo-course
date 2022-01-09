package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    boolean isExist = studentRepository.existsById(studentId);
    if (!isExist) {
      throw new RuntimeException("Student ID: " + studentId + " does not exists!");
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    Student student =
        studentRepository
            .findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student does not exist"));

    if (name !=null &&!name.isEmpty() && !name.equalsIgnoreCase(student.getName())) {
      student.setName(name);
    }

    if (email !=null && !email.isEmpty() && !email.equals(student.getEmail())) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if (studentOptional.isPresent()) {
        throw new RuntimeException("Email is taken");
      }
      student.setEmail(email);
    }
  }
}
