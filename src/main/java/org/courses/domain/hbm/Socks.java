package org.courses.domain.hbm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Socks {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    private double size;

    @Column(name = "colour")
    private int colour;

    @ManyToOne
    private Manufacture manufacture;

    @ManyToOne
    private Type type;

    @OneToMany
    private List<Composition> composition = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }
}
