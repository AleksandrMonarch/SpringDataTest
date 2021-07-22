package com.example.springdatatest.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PASSPORT")
@Setter
@Getter
public class Passport {

//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "ID")
//    private String id;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PASSPORT_NUMBER")
    private Long passportNumber;

    @OneToOne(mappedBy = "passport")
    private Student student;
}
