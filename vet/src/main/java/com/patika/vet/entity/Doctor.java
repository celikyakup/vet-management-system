package com.patika.vet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "doctor_name",nullable = false)
    private String name;

    @Column(name = "doctor_phone",nullable = false)
    private String phone;

    @Column(name = "doctor_mail",nullable = false)
    private String mail;

    @Column(name = "doctor_address",nullable = false)
    private String address;

    @Column(name = "doctor_city",nullable = false)
    private String city;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDateList;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointmentList;
}
