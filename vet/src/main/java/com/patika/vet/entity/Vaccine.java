package com.patika.vet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "vaccine_name",nullable = false)
    private String name;

    @Column(name = "vaccine_code",nullable = false)
    private String code;

    @Column(name = "vaccine_protection_start",nullable = false)
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_protection_finish",nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id",referencedColumnName = "animal_id")
    private Animal animal;
}
