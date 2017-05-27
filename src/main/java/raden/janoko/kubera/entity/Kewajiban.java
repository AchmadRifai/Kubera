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
public class Kewajiban {
    private String kode,nama;
    private raden.janoko.kubera.util.Rupiah jumlah;
    private Type tipe;
    private boolean selesai,deleted,pajak;

    public Kewajiban(String k, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from kewajiban where kode=?");
        p.setString(1, k);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            kode=r.getString("kode");
            nama=r.getString("nama");
            jumlah=new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah"));
            tipe=Type.valueOf(r.getString("tipe"));
            selesai=r.getBoolean("selesai");
            deleted=r.getBoolean("deleted");
            pajak=r.getBoolean("pajak");
        }r.close();
        p.close();
    }

    public Kewajiban() {}

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public raden.janoko.kubera.util.Rupiah getJumlah() {
        return jumlah;
    }

    public void setJumlah(raden.janoko.kubera.util.Rupiah jumlah) {
        this.jumlah = jumlah;
    }

    public Type getTipe() {
        return tipe;
    }

    public void setTipe(Type tipe) {
        this.tipe = tipe;
    }

    public boolean isSelesai() {
        return selesai;
    }

    public void setSelesai(boolean selesai) {
        this.selesai = selesai;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPajak() {
        return pajak;
    }

    public void setPajak(boolean pajak) {
        this.pajak = pajak;
    }

    public enum Type {panjang,pendek}
}