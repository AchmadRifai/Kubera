/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Pendapatan;

/**
 *
 * @author ai
 */
public class DAOPendapatan implements DAO<Pendapatan>{
    private raden.janoko.kubera.util.Db d;

    public DAOPendapatan(raden.janoko.kubera.util.Db d){
        this.d = d;
    }

    @Override
    public void createTable() throws SQLException {
        d.exec("create table pendapatan(kode varchar(20)primary key,"
                + "ket text not null,sk text not null,"
                + "jumlah bigint not null,deleted boolean not null)").close();
    }

    @Override
    public void insert(Pendapatan v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into pendapatan values(?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getKet());
        p.setString(3, v.getSk());
        p.setLong(4, v.getJumlah().getData());
        p.setBoolean(5, v.isDeleted());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Pendapatan w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pendapatan set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Pendapatan w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from pendapatan where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Pendapatan a, Pendapatan b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pendapatan set ket=?,sk=?,jumlah=? where kode=?");
        p.setString(1, b.getKet());
        p.setString(2, b.getSk());
        p.setLong(3, b.getJumlah().getData());
        p.setString(4, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Pendapatan> all() throws SQLException {
        List<Pendapatan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pendapatan where not deleted");
        while(r.next()){
            Pendapatan p=new Pendapatan();
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setSk(r.getString("sk"));
            l.add(p);
        }r.close();
        return l;
    }

    @Override
    public List<Pendapatan> sampah() throws SQLException {
        List<Pendapatan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pendapatan where deleted");
        while(r.next()){
            Pendapatan p=new Pendapatan();
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setSk(r.getString("sk"));
            l.add(p);
        }r.close();
        return l;
    }
}