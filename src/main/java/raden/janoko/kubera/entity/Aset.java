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
public class Aset {
    private String kode,nama;
    private Type tipe;
    private boolean deleted;

    public Aset(String kode, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from aset where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            this.kode=r.getString("kode");
            nama=r.getString("nama");
            tipe=Type.valueOf(r.getString("tipe"));
            deleted=r.getBoolean("deleted");
        }r.close();
        p.close();
    }

    public Aset() {
    }

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

    public Type getTipe() {
        return tipe;
    }

    public void setTipe(Type tipe) {
        this.tipe = tipe;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public enum Type {liquid,pajak,tetap,tak_berwujud,lain}
}