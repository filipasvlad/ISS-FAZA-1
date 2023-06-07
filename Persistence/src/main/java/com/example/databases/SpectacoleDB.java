package com.example.databases;

import com.example.Spectacol;
import com.example.User;
import com.example.Utils.JdbcUtils;
import com.example.interfaces.SpectacoleInterface;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SpectacoleDB implements SpectacoleInterface {

    private final JdbcUtils jdbcUtils;

    public SpectacoleDB(Properties props) {
        this.jdbcUtils = new JdbcUtils(props);
    }


    @Override
    public Spectacol findOne(Long aLong) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("select * from spectacole where id= ?")) {
            preStmt.setLong(1, aLong);
            try(ResultSet result = preStmt.executeQuery()) {
                if(result.next()) {
                    String nume = result.getString("nume");
                    Date data = result.getDate("data");
                    return new Spectacol(nume, data);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
        } catch (Exception e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }

    @Override
    public Iterable<Spectacol> findAll() throws SQLException {
        Connection con = jdbcUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from spectacole")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while(result.next()) {
                    Long id = result.getLong("id");
                    String nume = result.getString("nume");
                    Date data = result.getDate("data");
                    Spectacol spectacol = new Spectacol(nume, data);
                    spectacol.setId(id);
                    spectacole.add(spectacol);
                }

            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return spectacole;
        }
    }

    @Override
    public Spectacol save(Spectacol entity) throws FileNotFoundException {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into spectacole(nume, data) values (?,?)")) {
            preStmt.setString(1, entity.getNume());
            preStmt.setDate(2, (java.sql.Date) entity.getData());
            preStmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }

    @Override
    public Spectacol delete(Long aLong) {
        Connection con = jdbcUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM spectacole WHERE id = ?")) {
            preStmt.setInt(1, Math.toIntExact(aLong));
            preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB: " + e);
        }
        return null;
    }

    @Override
    public Spectacol update(Spectacol entity1, Spectacol entity2) {
        return null;
    }
}
