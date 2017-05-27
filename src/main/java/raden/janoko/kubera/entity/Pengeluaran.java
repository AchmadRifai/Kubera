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
public class Pengeluaran {
    private String kode,refe,ket;
    private Beban rek;
    private raden.janoko.kubera.util.Rupiah jumlah,akumulasi;
    private java.sql.Date tgl;
    private boolean deleted;

    public Pengeluaran(String k, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from pengeluaran where kode=?");
        p.setString(1, k);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            kode=r.getString("kode");
            refe=r.getString("refe");
            ket=r.getString("ket");
            rek=new Beban(r.getString("rek"),d);
            jumlah=new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah"));
            akumulasi=new raden.janoko.kubera.util.Rupiah(r.getLong("akumulasi"));
            deleted=r.getBoolean("deleted");
            tgl=r.getDate("tgl");
        }r.close();
        p.close();
    }

    public Pengeluaran() {}

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getRefe() {
        return refe;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public Beban getRek() {
        return rek;
    }

    public void setRek(Beban rek) {
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

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }
}