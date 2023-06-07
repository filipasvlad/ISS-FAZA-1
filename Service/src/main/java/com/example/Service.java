package com.example;

import com.example.databases.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Objects;

public class Service {
    private UserHibernateDB repoUser;
    private SpectacoleHibernateDB repoSpectacole;
    private RezervariHibernateDB repoRezervari;

    public Service(UserHibernateDB repoUser, SpectacoleHibernateDB repoSpectacole, RezervariHibernateDB repoRezervari){

        this.repoUser = repoUser;
        this.repoSpectacole = repoSpectacole;
        this.repoRezervari = repoRezervari;
    }

    public void addUser(User user) throws FileNotFoundException {
        repoUser.save(user);
    }

    public User getUser(String username){
        return repoUser.findOne(username);
    }

    public Iterable<Spectacol> getAllSpectacole() throws SQLException {
        return repoSpectacole.findAll();
    }

    public void addSpectacol(Spectacol spectacol) throws FileNotFoundException {
        repoSpectacole.save(spectacol);
    }

    public void deleteSpectacol(Long id) throws SQLException {
        for (Rezervare r:repoRezervari.findAll()){
            if (Objects.equals(r.getIdSpectacol(), id)){
                repoRezervari.delete(r.getId());
            }
        }
        repoSpectacole.delete(id);
    }

    public Iterable<Rezervare> getRezervari() throws SQLException {
        return repoRezervari.findAll();
    }

    public void addRezervare(Rezervare rezervare) throws FileNotFoundException {
        repoRezervari.save(rezervare);
    }

    public Spectacol getSpectacol(Long id){
        return repoSpectacole.findOne(id);
    }

    public void deleteRezervare(Long idSelectat) {
        repoRezervari.delete(idSelectat);
    }
}
