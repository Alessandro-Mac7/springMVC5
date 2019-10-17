package it.si2001.springMVC.dto;

import java.io.Serializable;
import java.sql.Date;

public class VehicleDTO implements Serializable {

    private int id;
    private String uniqueId;
    private String manufacturer;
    private String model;
    private Date carRegistration;
    private int categoryId;

    public VehicleDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(Date carRegistration) {
        this.carRegistration = carRegistration;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
