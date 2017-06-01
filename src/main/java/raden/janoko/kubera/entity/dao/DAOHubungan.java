/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import raden.janoko.kubera.entity.Hubungan;

/**
 *
 * @author ai
 */
public class DAOHubungan implements DAO<Hubungan>{
    private raden.janoko.kubera.util.Db d;

    public DAOHubungan(raden.janoko.kubera.util.Db d){
        this.d = d;
    }

    @Override
    public void createTable() throws SQLException {
        d.getS().executeUpdate("create table hubungan(asetKredit varchar(20),"
                + "asetDebit varchar(20),sumberMasuk varchar(30),"
                + "sumberKeluar varchar(30),bebanKredit varchar(20),"
                + "bebanDebit varchar(20),wajibKredit varchar(20),"
                + "wajibDebit varchar(20),dapatKredit varchar(20),"
                + "dapatDebit varchar(20),deleted boolean not null)");
    }

    @Override
    public void insert(Hubungan v) throws SQLException {
        PreparedStatement p=d.execPrepare("insert into hubungan values(?,?,?,?,?,?,?,?,?,?,?)");
        nextPrepare(p,v);
        p.setBoolean(11, v.isDeleted());
        p.execute();
        p.close();
    }

    @Override
    public void delete(Hubungan v) throws SQLException {
        PreparedStatement p=d.execPrepare("update hubungan set deleted=? where asetKredit=? and asetDebit=? and sumberMasuk=? and "
                + "sumberKeluar=? and bebanKredit=? and bebanDebit=? and wajibKredit=? and wajibDebit=? and dapatKredit=? and dapatDebit=?");
        p.setBoolean(1, true);
        p.setString(2, v.getAsetKredit().getKode()!=null?v.getAsetKredit().getKode():"");
        p.setString(3, v.getAsetDebit().getKode()!=null?v.getAsetDebit().getKode():"");
        p.setString(4, v.getSumberMasuk().getKode()!=null?v.getSumberMasuk().getKode():"");
        p.setString(5, v.getSumberKeluar().getKode()!=null?v.getSumberKeluar().getKode():"");
        p.setString(6, v.getBebanKredit().getKode()!=null?v.getBebanKredit().getKode():"");
        p.setString(7, v.getBebanDebit().getKode()!=null?v.getBebanDebit().getKode():"");
        p.setString(8, v.getWajibKredit().getKode()!=null?v.getWajibKredit().getKode():"");
        p.setString(9, v.getWajibDebit().getKode()!=null?v.getWajibDebit().getKode():"");
        p.setString(10, v.getDapatKredit().getKode()!=null?v.getDapatKredit().getKode():"");
        p.setString(11, v.getDapatDebit().getKode()!=null?v.getDapatDebit().getKode():"");
        p.execute();
        p.close();
    }

    @Override
    public void trueDelete(Hubungan w) throws SQLException {
        PreparedStatement p=d.execPrepare("delete from hubungan where asetKredit=? and asetDebit=? and sumberMasuk=? and sumberKeluar=? "
                + "and bebanKredit=? and bebanDebit=? and wajibKredit=? and wajibDebit=? and dapatKredit=? and dapatDebit=?");
        nextPrepare(p, w);
        p.execute();
        p.close();
    }

    @Override
    public void update(Hubungan v, Hubungan b) throws SQLException {
        PreparedStatement p=d.execPrepare("update hubungan set asetKredit=?,asetDebit=?,sumberMasuk=?,sumberKeluar=?,bebanKredit=?,"
                + "bebanDebit=?,wajibKredit=?,wajibDebit=?,dapatKredit=?,dapatDebit=? where asetKredit=? and asetDebit=? and "
                + "sumberMasuk=? and sumberKeluar=? and bebanKredit=? and bebanDebit=? and wajibKredit=? and wajibDebit=? and "
                + "dapatKredit=? and dapatDebit=?");
        nextPrepare(p, b);
        p.setString(11, v.getAsetKredit().getKode()!=null?v.getAsetKredit().getKode():"");
        p.setString(12, v.getAsetDebit().getKode()!=null?v.getAsetDebit().getKode():"");
        p.setString(13, v.getSumberMasuk().getKode()!=null?v.getSumberMasuk().getKode():"");
        p.setString(14, v.getSumberKeluar().getKode()!=null?v.getSumberKeluar().getKode():"");
        p.setString(15, v.getBebanKredit().getKode()!=null?v.getBebanKredit().getKode():"");
        p.setString(16, v.getBebanDebit().getKode()!=null?v.getBebanDebit().getKode():"");
        p.setString(17, v.getWajibKredit().getKode()!=null?v.getWajibKredit().getKode():"");
        p.setString(18, v.getWajibDebit().getKode()!=null?v.getWajibDebit().getKode():"");
        p.setString(19, v.getDapatKredit().getKode()!=null?v.getDapatKredit().getKode():"");
        p.setString(20, v.getDapatDebit().getKode()!=null?v.getDapatDebit().getKode():"");
        p.execute();
        p.close();
    }

    @Override
    public List<Hubungan> all() throws SQLException {
        List<Hubungan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from hubungan where not deleted");
        while(r.next()){
            Hubungan h=new Hubungan();
            h.setAsetDebit(new raden.janoko.kubera.entity.Aset(r.getString("asetDebit"), d));
            h.setAsetKredit(new raden.janoko.kubera.entity.Aset(r.getString("asetKredit"), d));
            h.setBebanDebit(new raden.janoko.kubera.entity.Beban(r.getString("bebanDebit"), d));
            h.setBebanKredit(new raden.janoko.kubera.entity.Beban(r.getString("bebanKredit"), d));
            h.setDapatDebit(new raden.janoko.kubera.entity.Pendapatan(r.getString("dapatDebit"), d));
            h.setDapatKredit(new raden.janoko.kubera.entity.Pendapatan(r.getString("dapatDebit"), d));
            h.setDeleted(r.getBoolean("deleted"));
            h.setSumberKeluar(new raden.janoko.kubera.entity.Pengeluaran(r.getString("sumberKeluar"), d));
            h.setSumberMasuk(new raden.janoko.kubera.entity.Pemasukan(r.getString("sumberMasuk"), d));
            h.setWajibDebit(new raden.janoko.kubera.entity.Kewajiban(r.getString("wajibDebit"), d));
            h.setWajibKredit(new raden.janoko.kubera.entity.Kewajiban(r.getString("wajibKredit"), d));
            l.add(h);
        }r.close();
        return l;
    }

    @Override
    public List<Hubungan> sampah() throws SQLException {
        List<Hubungan>l=new java.util.LinkedList<>();
        java.sql.ResultSet r=d.exec("select*from hubungan where deleted");
        while(r.next()){
            Hubungan h=new Hubungan();
            h.setAsetDebit(new raden.janoko.kubera.entity.Aset(r.getString("asetDebit"), d));
            h.setAsetKredit(new raden.janoko.kubera.entity.Aset(r.getString("asetKredit"), d));
            h.setBebanDebit(new raden.janoko.kubera.entity.Beban(r.getString("bebanDebit"), d));
            h.setBebanKredit(new raden.janoko.kubera.entity.Beban(r.getString("bebanKredit"), d));
            h.setDapatDebit(new raden.janoko.kubera.entity.Pendapatan(r.getString("dapatDebit"), d));
            h.setDapatKredit(new raden.janoko.kubera.entity.Pendapatan(r.getString("dapatDebit"), d));
            h.setDeleted(r.getBoolean("deleted"));
            h.setSumberKeluar(new raden.janoko.kubera.entity.Pengeluaran(r.getString("sumberKeluar"), d));
            h.setSumberMasuk(new raden.janoko.kubera.entity.Pemasukan(r.getString("sumberMasuk"), d));
            h.setWajibDebit(new raden.janoko.kubera.entity.Kewajiban(r.getString("wajibDebit"), d));
            h.setWajibKredit(new raden.janoko.kubera.entity.Kewajiban(r.getString("wajibKredit"), d));
            l.add(h);
        }r.close();
        return l;
    }

    private void nextPrepare(PreparedStatement p, Hubungan v) throws SQLException {
        p.setString(1, v.getAsetKredit()!=null?v.getAsetKredit().getKode():"");
        p.setString(2, v.getAsetDebit()!=null?v.getAsetDebit().getKode():"");
        p.setString(3, v.getSumberMasuk()!=null?v.getSumberMasuk().getKode():"");
        p.setString(4, v.getSumberKeluar()!=null?v.getSumberKeluar().getKode():"");
        p.setString(5, v.getBebanKredit()!=null?v.getBebanKredit().getKode():"");
        p.setString(6, v.getBebanDebit()!=null?v.getBebanDebit().getKode():"");
        p.setString(7, v.getWajibKredit()!=null?v.getWajibKredit().getKode():"");
        p.setString(8, v.getWajibDebit()!=null?v.getWajibDebit().getKode():"");
        p.setString(9, v.getDapatKredit()!=null?v.getDapatKredit().getKode():"");
        p.setString(10, v.getDapatDebit()!=null?v.getDapatDebit().getKode():"");
    }
}