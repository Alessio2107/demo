package com.acqualand.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attrazioni")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attrazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descrizione;
    private double latitudine;
    private double longitudine;
    private boolean isOpen;
    private int minAge;
    private double minHeight;
}