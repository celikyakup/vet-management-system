package com.patika.vet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name",nullable = false)
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    @Column(name = "customer_mail",nullable = false)
    private String mail;

    @Column(name = "customer_address",nullable = false)
    private String address;

    @Column(name = "customer_city",nullable = false)
    private String city;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animalList;

}
