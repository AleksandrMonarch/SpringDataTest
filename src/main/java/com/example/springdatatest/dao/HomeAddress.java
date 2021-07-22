package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "HOME_ADDRESS")
@Getter
@Setter
public class HomeAddress {

//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "ID")
//    private String id;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "FLAT", nullable = false)
    private Long flat;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "homeAddress")
    private List<Student> students;

    public void addStudent(Student student) {
        if (Objects.isNull(students)) {
            students = new ArrayList<>();
        }
        students.add(student);
        student.setHomeAddress(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setHomeAddress(null);
    }

    @Override
    public String toString() {
        return "HomeAddress{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", flat=" + flat +
                '}';
    }
}
