/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Pengeluaran;

/**
 *
 * @author ai
 */
public class DAOPengeluaran implements DAO<Pengeluaran>{
    private raden.janoko.kubera.util.Db d;

    public DAOPengeluaran(raden.janoko.kubera.util.Db d){
        this.d=d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table pengeluaran(kode varchar(30)primary key,"
                + "refe text not null,ket text not null,"
                + "rek varchar(20)not null,jumlah bigint not null,"
                + "tgl date not null,akumulasi bigint not null,"
                + "deleted boolean not null)");
        d.getS().executeUpdate("alter table pengeluaran add foreign key(rek)references beban(kode)on update cascade on delete cascade");
    }

    @Override
    public void insert(Pengeluaran v) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("insert into pengeluaran values(?,?,?,?,?,?,?,?)");
        p.setString(1, v.getKode());
        p.setString(2, v.getRefe());
        p.setString(3, v.getKet());
        p.setString(4, v.getRek().getKode());
        p.setLong(5, v.getJumlah().getData());
        p.setDate(6, v.getTgl());
        p.setLong(7, v.getAkumulasi().getData());
        p.setBoolean(8, v.isDeleted());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Pengeluaran w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pengeluaran set deleted=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Pengeluaran w) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("delete from pengeluaran where kode=?");
        p.setString(1, w.getKode());
        p.execute();
        p.close();
    }

    @Override
    public void update(Pengeluaran a, Pengeluaran b) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("update pengeluaran set refe=?,ket=?,rek=?,jumlah=?,tgl=?,akumulasi=? where kode=?");
        p.setString(1, b.getRefe());
        p.setString(2, b.getKet());
        p.setString(3, b.getRek().getKode());
        p.setLong(4, b.getJumlah().getData());
        p.setDate(5, b.getTgl());
        p.setLong(6, b.getAkumulasi().getData());
        p.setString(7, a.getKode());
        p.execute();
        p.close();
    }

    @Override
    public List<Pengeluaran> all() throws SQLException {
        List<Pengeluaran>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pengeluaran where not deleted order by tgl desc");
        while(r.next()){
            Pengeluaran p=new Pengeluaran();
            p.setAkumulasi(new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi")));
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setRefe(r.getString("refe"));
            p.setRek(new raden.janoko.kubera.entity.Beban(r.getString("rek"), d));
            p.setTgl(r.getDate("tgl"));
            l.add(p);
        }r.close();
        return l;
    }

    @Override
    public List<Pengeluaran> sampah() throws SQLException {
        List<Pengeluaran>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from pengeluaran where deleted order by tgl desc");
        while(r.next()){
            Pengeluaran p=new Pengeluaran();
            p.setAkumulasi(new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi")));
            p.setDeleted(r.getBoolean("deleted"));
            p.setJumlah(new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah")));
            p.setKet(r.getString("ket"));
            p.setKode(r.getString("kode"));
            p.setRefe(r.getString("refe"));
            p.setRek(new raden.janoko.kubera.entity.Beban(r.getString("rek"), d));
            p.setTgl(r.getDate("tgl"));
            l.add(p);
        }r.close();
        return l;
    }
}