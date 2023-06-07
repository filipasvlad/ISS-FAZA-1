package com.example;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@javax.persistence.Entity
@Table( name = "rezervari" )
public class Rezervare extends com.example.Entity<Long>{
    private Long id;
    private String username;
    private Long idSpectacol;
    private String locuriRezervate;

    public Rezervare(String locuriRezervate, String username, Long idSpectacol) {
        this.username = username;
        this.idSpectacol = idSpectacol;
        this.locuriRezervate = locuriRezervate;
    }

    public Rezervare() {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIdSpectacol() {
        return idSpectacol;
    }

    public void setIdSpectacol(Long idSpectacol) {
        this.idSpectacol = idSpectacol;
    }

    public String getLocuriRezervate() {
        return locuriRezervate;
    }

    public void setLocuriRezervate(String locuriRezervate) {
        this.locuriRezervate = locuriRezervate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rezervare rezervare)) return false;
        return Objects.equals(getId(), rezervare.getId()) && Objects.equals(getUsername(), rezervare.getUsername()) && Objects.equals(getIdSpectacol(), rezervare.getIdSpectacol()) && Objects.equals(getLocuriRezervate(), rezervare.getLocuriRezervate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getIdSpectacol(), getLocuriRezervate());
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", idSpectacol=" + idSpectacol +
                ", locuriRezervate='" + locuriRezervate + '\'' +
                '}';
    }
}
