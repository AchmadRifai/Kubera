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
    private raden.janoko.kubera.util.Rupiah saldo;
    private Type tipe;
    private boolean deleted,kas;

    public Aset(String kode, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from aset where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            this.kode=r.getString("kode");
            nama=r.getString("nama");
            tipe=Type.valueOf(r.getString("tipe"));
            deleted=r.getBoolean("deleted");
            saldo=new raden.janoko.kubera.util.Rupiah(r.getLong("saldo"));
            kas=r.getBoolean("kas");
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

    public boolean isKas() {
        return kas;
    }

    public void setKas(boolean kas) {
        this.kas = kas;
    }

    public raden.janoko.kubera.util.Rupiah getSaldo() {
        return saldo;
    }

    public void setSaldo(raden.janoko.kubera.util.Rupiah saldo) {
        this.saldo = saldo;
    }

    public enum Type {liquid,pajak,tetap,tak_berwujud,lain}
}