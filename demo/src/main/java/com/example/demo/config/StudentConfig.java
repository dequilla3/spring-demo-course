package com.example.demo.config;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.CoreUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class StudentConfig {

  @Bean
  CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
    return args -> {
      List<Student> studentList = new LinkedList<>();

      Student student1 = new Student();
      student1.setName("KIM ARIEL N. DEQUILLA");
      student1.setBirthdate(CoreUtil.convertLocalDateToDate(LocalDate.now()));
      student1.setAge(23);
      student1.setEmail("deqkim4@gmail.com");
      studentList.add(student1);

      Student student2 = new Student();
      student2.setName("MITCHELL R. BARCELONA");
      student2.setBirthdate(CoreUtil.convertLocalDateToDate(LocalDate.now()));
      student2.setAge(21);
      student2.setEmail("deqkim5@gmail.com");
      studentList.add(student2);

      studentRepository.saveAll(studentList);
    };
  }
}
