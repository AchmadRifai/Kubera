/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.entity;

import java.sql.SQLException;

/**
 *
 * @author ai
 */
public class Pemasukan {
    private String kode,ket,sumber;
    private java.sql.Date tgl;
    private Aset rek;
    private raden.janoko.kubera.util.Rupiah jumlah,akumulasi;
    private boolean deleted;

    public Pemasukan(String k, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from pemasukan where kode=?");
        p.setString(1, k);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            kode=r.getString("kode");
            ket=r.getString("ket");
            sumber=r.getString("sumber");
            tgl=r.getDate("tgl");
            rek=new Aset(r.getString("rek"),d);
            jumlah=new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah"));
            akumulasi=new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi"));
            deleted=r.getBoolean("deleted");
        }r.close();
        p.close();
    }

    public Pemasukan() {}

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public Aset getRek() {
        return rek;
    }

    public void setRek(Aset rek) {
        this.rek = rek;
    }

    public raden.janoko.kubera.util.Rupiah getJumlah() {
        return jumlah;
    }

    public void setJumlah(raden.janoko.kubera.util.Rupiah jumlah) {
        this.jumlah = jumlah;
    }

    public raden.janoko.kubera.util.Rupiah getAkumulasi() {
        return akumulasi;
    }

    public void setAkumulasi(raden.janoko.kubera.util.Rupiah akumulasi) {
        this.akumulasi = akumulasi;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}