package it.si2001.springMVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "typology")
public class Typology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="type", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String type;

    @OneToMany( mappedBy = "typology" )
    private Set<User> user;

    public Typology() {}

    public Typology(String type, Set<User> user) {
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
