package com.example.springdatatest.controller;

import com.example.springdatatest.dao.*;
import com.example.springdatatest.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/base")
@Slf4j
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
    public void getStudent(@PathVariable Long id) {
//        why doesn't this line delete a row in db?
        homeAddressRepository.deleteById(id);
//        HomeAddress homeAddress = homeAddressRepository.findById(id).get();
//        System.out.println(homeAddress);
//        homeAddressRepository.delete(homeAddress);
    }

    @PostMapping
    public void processStudent() {
        Student student = new Student();
        student.setFirstName("Bruce");
        student.setSecondName("Banner");
        student.setPassport(createPassport());
        Grade grade1 = createGrade();
        HomeAddress homeAddress = createHomeAddress();
        StudentGroup studentGroup = createStudentGroup();
        ScientificPublication scientificPublication = createScientificPublication();
        grade1.addStudent(student);
        Grade grade2 = new Grade();
        grade2.addStudent(student);
        homeAddress.addStudent(student);
        studentGroup.addStudent(student);
        student.addScientificPublication(scientificPublication);
        studentRepository.save(student);
        System.out.println(student.getId());
    }

    @PostMapping("/problem")
    public void checkProblem() {
        Student student1 = new Student();
        student1.setFirstName("AAA");
        Student student2 = new Student();
        student2.setFirstName("BBB");
        Student student3 = new Student();
        student3.setFirstName("CCC");

        Grade grade1 = new Grade();
        grade1.setGradeNumber(1L);
        grade1.addStudent(student1);

        Grade grade2 = new Grade();
        grade2.setGradeNumber(2L);
        grade2.addStudent(student2);
        grade2.addStudent(student3);

        Course course = new Course();
        course.addStudent(student1);

        studentRepository.saveAll(Arrays.asList(student1, student2, student3));
    }

    @GetMapping("/problem")
    public void getProblem() {
        Student student = studentRepository.findStudentByFirstNameEquals("AAA").get();
        log.info("{} students were updated",
                studentRepository.updateStudentByFirstName("DDD", student.getId()));
    }


    @PatchMapping("/{id}")
    public void changePassport(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        System.out.println(student);
        ScientificPublication scientificPublication = new ScientificPublication();
        scientificPublication.setPublicationNumber(65674L);
        scientificPublication.setPublicationDate(new Date());
        scientificPublication.setPublicationTheme("Atomic theme");
        scientificPublication.setPublicationMagazine("Life");
        scientificPublication.setStudent(student);
        student.getScientificPublications().add(scientificPublication);
        System.out.println(studentRepository.save(student));
    }

    @PutMapping
    public void request() {
        Student student = new Student();
        student.setFirstName("AAA");
        student.setSecondName("BBB");
        student.setGrade(createGrade());
        student.setStudentGroup(createStudentGroup());
        student.setHomeAddress(createHomeAddress());
        studentRepository.save(student);
        Passport passport = createPassport();
        passport.setId(5748572L);
        student.setPassport(passport);
        studentRepository.save(student);
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
