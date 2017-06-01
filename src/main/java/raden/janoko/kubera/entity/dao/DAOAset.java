/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Aset;

/**
 *
 * @author ai
 */
public class DAOAset implements DAO<Aset>{
    private raden.janoko.kubera.util.Db d;

    public DAOAset(raden.janoko.kubera.util.Db d){
        this.d=d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table aset(kode varchar(20)primary key,"
                + "nama varchar(40)not null,saldo bigint not null,"
                + "tipe varchar(12)not null,"
                + "kas boolean not null,deleted boolean not null)");
    }

    @Override
    public void insert(Aset v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into aset values(?,?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getNama());
        p.setLong(3, v.getSaldo().getData());
        p.setString(4, ""+v.getTipe());
        p.setBoolean(5, v.isKas());
        p.setBoolean(6, v.isDeleted());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Aset w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update aset set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Aset w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from aset where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Aset a, Aset b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update aset set nama=?,saldo=?,tipe=?,kas=? where kode=?");
        p.setString(1, b.getNama());
        p.setLong(2, b.getSaldo().getData());
        p.setString(3, ""+b.getTipe());
        p.setBoolean(4, b.isKas());
        p.setString(5, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Aset> all() throws SQLException {
        List<Aset>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from aset where not deleted");
        while(r.next()){
            Aset a=new Aset();
            a.setDeleted(r.getBoolean("deleted"));
            a.setKas(r.getBoolean("kas"));
            a.setKode(r.getString("kode"));
            a.setNama(r.getString("nama"));
            a.setSaldo(new raden.janoko.kubera.util.Rupiah(r.getLong("saldo")));
            a.setTipe(Aset.Type.valueOf(r.getString("tipe")));
            l.add(a);
        }r.close();
        return l;
    }

    @Override
    public List<Aset> sampah() throws SQLException {
        List<Aset>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from aset where deleted");
        while(r.next()){
            Aset a=new Aset();
            a.setDeleted(r.getBoolean("deleted"));
            a.setKas(r.getBoolean("kas"));
            a.setKode(r.getString("kode"));
            a.setNama(r.getString("nama"));
            a.setSaldo(new raden.janoko.kubera.util.Rupiah(r.getLong("saldo")));
            a.setTipe(Aset.Type.valueOf(r.getString("tipe")));
            l.add(a);
        }r.close();
        return l;
    }

    public Aset kas() throws SQLException{
        Aset a=null;
        java.sql.ResultSet r=d.exec("select kode from aset where kas and tipe='liquid' and not deleted");
        if(r.next())a=new Aset(r.getString("kode"),d);
        r.close();
        return a;
    }
}