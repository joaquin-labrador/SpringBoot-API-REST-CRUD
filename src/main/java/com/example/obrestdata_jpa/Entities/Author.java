package com.example.obrestdata_jpa.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String completeName;
    private LocalDate birthDate;
    private LocalDate deathDate;

    private String country;

    public Author() {
    }

    public Author(Long id, String completeName, LocalDate birthDate, LocalDate deathDate, String country) {
        this.id = id;
        this.completeName = completeName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + completeName + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", country='" + country + '\'' +
                '}';
    }
}
