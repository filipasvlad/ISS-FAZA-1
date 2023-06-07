package com.example.databases;

import com.example.User;
import com.example.Utils.JdbcUtils;
import com.example.interfaces.UserInterface;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserDB implements UserInterface {


    private final JdbcUtils jdbcUtils;

    public UserDB(Properties props) {
        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public User findOne(String username) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("select * from user where username= ?")) {
            preStmt.setString(1, username);
            try(ResultSet result = preStmt.executeQuery()) {
                if(result.next()) {
                    String pass = result.getString("pass");
                    String type = result.getString("type");
                    return new User(username, pass, type);
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
    public Iterable<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User save(User entity) throws FileNotFoundException {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into user(username, pass, type) values (?,?,?)")) {
            preStmt.setString(1, entity.getUsername());
            preStmt.setString(2, entity.getPass());
            preStmt.setString(3, entity.getType());
            preStmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
        return null;
    }

    @Override
    public User delete(String aLong) {
        return null;
    }

    @Override
    public User update(User entity1, User entity2) {
        return null;
    }
}
