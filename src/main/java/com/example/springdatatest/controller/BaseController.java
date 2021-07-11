package com.example.springdatatest.controller;

import com.example.springdatatest.dao.Student;
import com.example.springdatatest.repository.GradeRepository;
import com.example.springdatatest.repository.PassportRepository;
import com.example.springdatatest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class BaseController {

    private final StudentRepository studentRepository;
    private final PassportRepository passportRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public BaseController(StudentRepository studentRepository, PassportRepository passportRepository,
                          GradeRepository gradeRepository) {

        this.studentRepository = studentRepository;
        this.passportRepository = passportRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/{gradeId}")
    public Student getStudent(@PathVariable(name = "gradeId") String gradeId) {
        return null;
    }

    @PostMapping
    public Student processStudent() {
        return null;
    }
}
