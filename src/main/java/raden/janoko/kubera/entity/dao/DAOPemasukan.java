/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Pemasukan;

/**
 *
 * @author ai
 */
public class DAOPemasukan implements DAO<Pemasukan>{
    private raden.janoko.kubera.util.Db d;

    public DAOPemasukan(raden.janoko.kubera.util.Db d){
        this.d=d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table pemasukan(kode varchar(30)primary key,"
                + "ket text not null,sumber varchar(20)not null,"
                + "tgl date not null,rek varchar(20)not null,"
                + "jumlah bigint not null,akumulasi bigint not null,"
                + "deleted boolean not null)");
        d.getS().executeUpdate("alter table pemasukan add foreign key(rek)references aset(kode)on update cascade on delete cascade");
    }

    @Override
    public void insert(Pemasukan v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into pemasukan values(?,?,?,?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getKet());
        p.setString(3, v.getSumber());
        p.setDate(4, v.getTgl());
        p.setString(5, v.getRek().getKode());
        p.setLong(6, v.getJumlah().getData());
        p.setLong(7, v.getAkumulasi().getData());
        p.setBoolean(8, v.isDeleted());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Pemasukan w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pemasukan set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Pemasukan w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from pemasukan where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Pemasukan a, Pemasukan b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pemasukan set ket=?,sumber=?,tgl=?,rek=?,jumlah=?,akumulasi=? where kode=?");
        p.setString(1, b.getKet());
        p.setString(2, b.getSumber());
        p.setDate(3, b.getTgl());
        p.setString(4, b.getRek().getKode());
        p.setLong(5, b.getJumlah().getData());
        p.setLong(6, b.getAkumulasi().getData());
        p.setString(7, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Pemasukan> all() throws SQLException {
        List<Pemasukan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pemasukan where not deleted order by tgl desc");
        while(r.next()){
            Pemasukan p=new Pemasukan();
            p.setAkumulasi(new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi")));
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setRek(new raden.janoko.kubera.entity.Aset(r.getString("rek"), d));
            p.setSumber(r.getString("sumber"));
            p.setTgl(r.getDate("tgl"));
            l.add(p);
        }r.close();
        return l;
    }

    @Override
    public List<Pemasukan> sampah() throws SQLException {
        List<Pemasukan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pemasukan where deleted order by tgl desc");
        while(r.next()){
            Pemasukan p=new Pemasukan();
            p.setAkumulasi(new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi")));
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setRek(new raden.janoko.kubera.entity.Aset(r.getString("rek"), d));
            p.setSumber(r.getString("sumber"));
            p.setTgl(r.getDate("tgl"));
            l.add(p);
        }r.close();
        return l;
    }
}