package com.example.databases;

import com.example.Rezervare;
import com.example.Spectacol;
import com.example.Utils.JdbcUtils;
import com.example.interfaces.RezervariInterface;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class RezervariDB implements RezervariInterface {

    private final JdbcUtils jdbcUtils;

    public RezervariDB(Properties props) {
        this.jdbcUtils = new JdbcUtils(props);
    }
    @Override
    public Rezervare findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Rezervare> findAll() throws SQLException {
        Connection con = jdbcUtils.getConnection();
        List<Rezervare> rezervari = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from rezervari")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while(result.next()) {
                    Long id = result.getLong("id");
                    String locuriRezervate = result.getString("locuriRezervate");
                    String username = result.getString("username");
                    Long idSpectacol = result.getLong("idSpectacol");
                    Rezervare rezervare = new Rezervare(locuriRezervate, username, idSpectacol);
                    rezervare.setId(id);
                    rezervari.add(rezervare);
                }

            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return rezervari;
        }
    }

    @Override
    public Rezervare save(Rezervare entity) throws FileNotFoundException {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into rezervari(locuriRezervate, username, idSpectacol) values (?,?,?)")) {
            preStmt.setString(1, entity.getLocuriRezervate());
            preStmt.setString(2, entity.getUsername());
            preStmt.setLong(3, entity.getIdSpectacol());
            preStmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }

    @Override
    public Rezervare delete(Long aLong) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM rezervari WHERE id = ?")) {
            preStmt.setInt(1, Math.toIntExact(aLong));
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
        return null;
    }

    @Override
    public Rezervare update(Rezervare entity1, Rezervare entity2) {
        return null;
    }
}
