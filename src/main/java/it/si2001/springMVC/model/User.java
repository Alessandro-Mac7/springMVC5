package it.si2001.springMVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name", nullable = false, columnDefinition = "varchar(45)")
    private String name;

    @Column(name="lastName", nullable = false, columnDefinition = "varchar(45)")
    private String lastName;

    @Column(name="date")
    private Date date;

    @Column(name="email", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="salt")
    private String salt;

    @OneToMany( mappedBy = "user" )
    private Set<Booking> bookings;

    @ManyToOne( fetch= FetchType.EAGER )
    private Typology typology;

    public User() {}

    public User(String name, String lastName, Date date, String email, String password, String salt, Set<Booking> bookings, Typology typology) {
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.bookings = bookings;
        this.typology = typology;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Typology getTypology() {
        return typology;
    }

    public void setTypology(Typology typology) {
        this.typology = typology;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", lastName=" + lastName
                + ", email=" + email + ", password=" + password + ", date=" + date
                + "]";
    }
}
