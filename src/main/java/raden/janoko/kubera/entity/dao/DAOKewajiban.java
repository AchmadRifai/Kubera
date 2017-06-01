/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Kewajiban;

/**
 *
 * @author ai
 */
public class DAOKewajiban implements DAO<Kewajiban>{
    private raden.janoko.kubera.util.Db d;

    public DAOKewajiban(raden.janoko.kubera.util.Db d){
        this.d=d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table kewajiban(kode varchar(20)primary key,"
                + "nama varchar(30)not null,jumlah bigint not null,"
                + "tipe varchar(7)not null,selesai boolean not null,"
                + "deleted boolean not null,pajak boolean not null)");
    }

    @Override
    public void insert(Kewajiban v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into kewajiban values(?,?,?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getNama());
        p.setLong(3, v.getJumlah().getData());
        p.setString(4, ""+v.getTipe());
        p.setBoolean(5, v.isSelesai());
        p.setBoolean(6, v.isDeleted());
        p.setBoolean(7, v.isPajak());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Kewajiban w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update kewajiban set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Kewajiban w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from kewajiban where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Kewajiban a, Kewajiban b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update kewajiban set nama=?,jumlah=?,tipe=?,selesai=?,pajak=? where kode=?");
        p.setString(1, b.getNama());
        p.setLong(2, b.getJumlah().getData());
        p.setString(3, ""+b.getTipe());
        p.setBoolean(4, b.isSelesai());
        p.setBoolean(5, b.isPajak());
        p.setString(6, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Kewajiban> all() throws SQLException {
        List<Kewajiban>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from kewajiban where not deleted");
        while(r.next()){
            Kewajiban k=new Kewajiban();
            k.setDeleted(r.getBoolean("deleted"));
            k.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            k.setKode(r.getString("kode"));
            k.setNama(r.getString("nama"));
            k.setPajak(r.getBoolean("pajak"));
            k.setSelesai(r.getBoolean("selesai"));
            k.setTipe(Kewajiban.Type.valueOf(r.getString("tipe")));
            l.add(k);
        }r.close();
        return l;
    }

    @Override
    public List<Kewajiban> sampah() throws SQLException {
        List<Kewajiban>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from kewajiban where not deleted");
        while(r.next()){
            Kewajiban k=new Kewajiban();
            k.setDeleted(r.getBoolean("deleted"));
            k.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            k.setKode(r.getString("kode"));
            k.setNama(r.getString("nama"));
            k.setPajak(r.getBoolean("pajak"));
            k.setSelesai(r.getBoolean("selesai"));
            k.setTipe(Kewajiban.Type.valueOf(r.getString("tipe")));
            l.add(k);
        }r.close();
        return l;
    }

    public Kewajiban pajak() throws SQLException{
        Kewajiban k=null;
        java.sql.ResultSet r=d.exec("select kode from kewajiban where pajak and not deleted");
        if(r.next())k=new Kewajiban(r.getString("kode"),d);
        r.close();
        return k;
    }
}