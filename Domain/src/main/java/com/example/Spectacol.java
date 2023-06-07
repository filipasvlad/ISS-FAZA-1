package com.example;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "spectacole" )
public class Spectacol extends com.example.Entity<Long>{
    private Long id;
    private String nume;
    private Date data;

    public Spectacol(String nume, Date data){
        this.data = data;
        this.nume = nume;
    }

    public Spectacol() {

    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spectacol spectacol)) return false;
        return Objects.equals(getId(), spectacol.getId()) && Objects.equals(getNume(), spectacol.getNume()) && Objects.equals(getData(), spectacol.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNume(), getData());
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", data=" + data +
                '}';
    }
}
