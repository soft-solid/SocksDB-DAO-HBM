package org.courses.domain.hbm;

import javax.persistence.*;

@Entity
@Table(name = "Storage")
public class Storage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Socks socks;

    @Column(name = "added")
    private String added;

    @Column(name = "retired")
    private String retired;

    @Column(name = "usage")
    private Integer usage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socks getSocks() {
        return socks;
    }

    public void setSocks(Socks socks) {
        this.socks = socks;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getRetired() {
        return retired;
    }

    public void setRetired(String retired) {
        this.retired = retired;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }
}
