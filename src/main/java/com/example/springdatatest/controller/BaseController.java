package com.example.springdatatest.controller;

import com.example.springdatatest.dao.*;
import com.example.springdatatest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/base")
public class BaseController {

    private final StudentRepository studentRepository;
    private final PassportRepository passportRepository;
    private final GradeRepository gradeRepository;
    private final HomeAddressRepository homeAddressRepository;
    private final ScientificPublicationRepository scientificPublicationRepository;
    private final StudentGroupRepository studentGroupRepository;

    @Autowired
    public BaseController(StudentRepository studentRepository, PassportRepository passportRepository,
                          GradeRepository gradeRepository, HomeAddressRepository homeAddressRepository,
                          ScientificPublicationRepository scientificPublicationRepository,
                          StudentGroupRepository studentGroupRepository) {

        this.studentRepository = studentRepository;
        this.passportRepository = passportRepository;
        this.gradeRepository = gradeRepository;
        this.homeAddressRepository = homeAddressRepository;
        this.scientificPublicationRepository = scientificPublicationRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @GetMapping("/{id}")
    public void getStudent(@PathVariable String id) {
//         why doesn't this line delete a row in db?
        homeAddressRepository.deleteById(id);
    }

    @PostMapping
    public void processStudent() {
        Student student = new Student();
        student.setFirstName("Bruce");
        student.setSecondName("Banner");
        student.setPassport(createPassport());
        Grade grade = createGrade();
        HomeAddress homeAddress = createHomeAddress();
        StudentGroup studentGroup = createStudentGroup();
        ScientificPublication scientificPublication = createScientificPublication();
        grade.addStudent(student);
        homeAddress.addStudent(student);
        studentGroup.addStudent(student);
        student.addScientificPublication(scientificPublication);
        studentRepository.save(student);
        System.out.println(homeAddress.getId());
    }

    private Passport createPassport() {
        Passport passport = new Passport();
        passport.setPassportNumber(0000L);
        return passport;
    }

    private HomeAddress createHomeAddress() {
        HomeAddress homeAddress = new HomeAddress();
        homeAddress.setCity("Cambridge");
        homeAddress.setStreet("Cambridge street");
        homeAddress.setFlat(111L);
        return homeAddress;
    }

    private Grade createGrade() {
        Grade grade = new Grade();
        grade.setGradeNumber(9876L);
        grade.setGradeDescription("Simple grade");
        return grade;
    }

    private StudentGroup createStudentGroup() {
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setGroupSize(50L);
        studentGroup.setGroupDescription("Simple student group");
        return studentGroup;
    }

    private ScientificPublication createScientificPublication() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(1990, Calendar.AUGUST, 15, 12, 0, 0);
        ScientificPublication scientificPublication = new ScientificPublication();
        scientificPublication.setPublicationDate(calendar.getTime());
        scientificPublication.setPublicationNumber(10L);
        scientificPublication.setPublicationTheme("Gamma radiation");
        scientificPublication.setPublicationMagazine("Time");
        return scientificPublication;
    }
}
