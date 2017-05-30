/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Beban;

/**
 *
 * @author ai
 */
public class DAOBeban implements DAO<Beban>{
    private raden.janoko.kubera.util.Db d;

    public DAOBeban(raden.janoko.kubera.util.Db d){
        this.d=d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table beban(kode varchar(20)primary key,"
                + "nama varchar(20)not null,tipe varchar(9)not null,"
                + "deleted boolean not null,jumlah bigint not null)");
    }

    @Override
    public void insert(Beban v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into beban values(?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getNama());
        p.setString(3, ""+v.getTipe());
        p.setBoolean(4, v.isDeleted());
        p.setLong(5, v.getJumlah().getData());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Beban w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update beban set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Beban w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from beban where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Beban a, Beban b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update beban set nama=?,tipe=?,jumlah=? where kode=?");
        p.setString(1, b.getNama());
        p.setString(2, ""+b.getTipe());
        p.setLong(3, b.getJumlah().getData());
        p.setString(4, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Beban> all() throws SQLException {
        List<Beban>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from beban where not deleted");
        while(r.next()){
            Beban b=new Beban();
            b.setDeleted(r.getBoolean("deleted"));
            b.setKode(r.getString("kode"));
            b.setNama(r.getString("nama"));
            b.setTipe(Beban.Type.valueOf(r.getString("tipe")));
            b.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            l.add(b);
        }r.close();
        return l;
    }

    @Override
    public List<Beban> sampah() throws SQLException {
        List<Beban>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from beban where deleted");
        while(r.next()){
            Beban b=new Beban();
            b.setDeleted(r.getBoolean("deleted"));
            b.setKode(r.getString("kode"));
            b.setNama(r.getString("nama"));
            b.setTipe(Beban.Type.valueOf(r.getString("tipe")));
            b.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            l.add(b);
        }r.close();
        return l;
    }
}