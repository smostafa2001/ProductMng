package ir.ac.qom.final_project.data.entity;

import java.util.Date;

public class Product {
    Long id;
    String nameOfProduct;
    Date dateOfManufacture;
    Double price;
    Integer quantity;

    public Product() {
    }

    public Product(String nameOfProduct, Date dateOfManufacture, Double price, Integer quantity) {
        this.nameOfProduct = nameOfProduct;
        this.dateOfManufacture = dateOfManufacture;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(Long id, String nameOfProduct, Date dateOfManufacture, Double price, Integer quantity) {
        this.id = id;
        this.nameOfProduct = nameOfProduct;
        this.dateOfManufacture = dateOfManufacture;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfProduct() {
        return this.nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateOfManufacture() {
        return this.dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }
}
