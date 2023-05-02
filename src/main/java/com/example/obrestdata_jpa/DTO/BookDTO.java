package com.example.obrestdata_jpa.DTO;

import com.example.obrestdata_jpa.Entities.Book;

import java.time.LocalDate;
import java.util.UUID;

public class BookDTO {
    //same info of entity

    private UUID id;

    private String title;

    private Integer pages;

    private Double price;

    private LocalDate releaseDate;
    private Boolean isEbook;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.releaseDate = book.getReleaseDate();
        this.isEbook = book.getEbook();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getEbook() {
        return isEbook;
    }

    public void setEbook(Boolean ebook) {
        isEbook = ebook;
    }

    @Override
    public String toString() {
        return "BookDTO{" + "id=" + id + ", title='" + title + '\'' + ", pages=" + pages + ", price=" + price + ", releaseDate=" + releaseDate + ", isEbook=" + isEbook + '}';
    }
}
