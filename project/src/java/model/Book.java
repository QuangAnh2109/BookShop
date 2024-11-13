/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author anh21
 */
public class Book {
    private String name, introduce, size, authorName;
    private int quantity, numberPages, publishingYear,id;
    private BigDecimal price;
    private float discount, weight;
    private CoverType type;
    private Category category;
    private Company company;

    public Book() {
    }

    public Book(int id, String name, String introduce, String size, String authorName, int quantity, int numberPages, int publishingYear, BigDecimal price, float discount, float weight, CoverType type, Category category, Company company) {
        this.name = name;
        this.introduce = introduce;
        this.size = size;
        this.authorName = authorName;
        this.quantity = quantity;
        this.numberPages = numberPages;
        this.publishingYear = publishingYear;
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.weight = weight;
        this.type = type;
        this.category = category;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public CoverType getType() {
        return type;
    }

    public void setType(CoverType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
