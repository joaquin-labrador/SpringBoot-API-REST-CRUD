package com.example.obrestdata_jpa.DTO;

import com.example.obrestdata_jpa.Entities.Author;

import java.time.LocalDate;

public class AuthorDTO {

    private Long id;

    private String completeName;
    private LocalDate birthDate;
    private LocalDate deathDate;

    private String country;

    public AuthorDTO() {
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.completeName = author.getCompleteName();
        this.birthDate = author.getBirthDate();
        this.deathDate = author.getDeathDate();
        this.country = author.getCountry();
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
        return "AuthorDTO{" + "id=" + id + ", completeName='" + completeName + '\'' + ", birthDate=" + birthDate + ", deathDate=" + deathDate + ", country='" + country + '\'' + '}';
    }
}
