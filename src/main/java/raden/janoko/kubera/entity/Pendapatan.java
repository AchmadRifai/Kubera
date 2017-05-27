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
public class Pendapatan {
    private String kode,ket,sk;
    private raden.janoko.kubera.util.Rupiah jumlah;
    private boolean deleted;

    public Pendapatan(String k, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from pendapatan where kode=?");
        p.setString(1, k);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            kode=r.getString("kode");
            ket=r.getString("ket");
            sk=r.getString("sk");
            jumlah=new raden.janoko.kubera.util.Rupiah(r.getLong("jumlah"));
            deleted=r.getBoolean("deleted");
        }r.close();
        p.close();
    }

    public Pendapatan() {}

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

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public raden.janoko.kubera.util.Rupiah getJumlah() {
        return jumlah;
    }

    public void setJumlah(raden.janoko.kubera.util.Rupiah jumlah) {
        this.jumlah = jumlah;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}