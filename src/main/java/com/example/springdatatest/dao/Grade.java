package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GRADE")
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name = "GRADE_NUMBER")
    private Long gradeNumber;

    @Column(name = "GRADE_DESCRIPTION")
    private String gradeDescription;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> students;

    public void addStudent(Student student) {
        if (Objects.isNull(students)) {
            students = new ArrayList<>();
        }
        students.add(student);
        student.setGrade(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setGrade(null);
    }
}
