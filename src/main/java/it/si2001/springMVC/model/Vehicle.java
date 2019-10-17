package it.si2001.springMVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="uniqueId", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String uniqueId;

    @Column(name="manufacturer", nullable = false, columnDefinition = "varchar(45)")
    private String manufacturer;

    @Column(name="model", nullable = false, columnDefinition = "varchar(45)")
    private String model;

    @Column(name="carRegistration", nullable = false)
    private Date carRegistration;

    @ManyToOne
    private Category category;

    @OneToMany( mappedBy = "vehicle")
    private Set<Booking> bookings;

    public Vehicle() {}

    public Vehicle(String uniqueId, String manufacturer, String model, Date carRegistration, Category category, Set<Booking> bookings) {
        this.uniqueId = uniqueId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.carRegistration = carRegistration;
        this.category = category;
        this.bookings = bookings;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
