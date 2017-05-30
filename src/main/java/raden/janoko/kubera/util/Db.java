/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.util;

import java.sql.SQLException;

/**
 *
 * @author ai
 */
public class Db {
    private String host,name;
    private int port;
    private String user,pass;
    private java.sql.Connection c;
    private java.sql.Statement s;

    public Db(String host, String name, int port, String user, String pass) throws SQLException {
        this.host = host;
        this.name = name;
        this.port = port;
        this.user = user;
        this.pass = pass;
        start();
    }

    public void close() throws SQLException{
        s.close();
        c.close();
    }

    private void start() throws SQLException {
        c=java.sql.DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+name, user, pass);
        s=c.createStatement();
    }

    public java.sql.ResultSet exec(String sql) throws SQLException{
        return s.executeQuery(sql);
    }

    public java.sql.PreparedStatement execPrepare(String sql) throws SQLException{
        return c.prepareStatement(sql);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) throws SQLException {
        close();
        this.host = host;
        start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        close();
        this.name = name;
        start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) throws SQLException {
        close();
        this.port = port;
        start();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) throws SQLException {
        close();
        this.user = user;
        start();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) throws SQLException {
        close();
        this.pass = pass;
        start();
    }

    public java.sql.Statement getS() {
        return s;
    }

    public void setS(java.sql.Statement s) {
        this.s = s;
    }
}