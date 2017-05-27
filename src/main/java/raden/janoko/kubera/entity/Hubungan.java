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
public class Hubungan {
    private Aset asetKredit,asetDebit;
    private Pemasukan sumberMasuk;
    private Pengeluaran sumberKeluar;
    private Beban bebanKredit,bebanDebit;
    private Kewajiban wajibKredit,wajibDebit;
    private Pendapatan dapatKredit,dapatDebit;
    private boolean deleted;

    public Hubungan() {}

    public Hubungan(Pemasukan m, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from hubungan where sumberMasuk=?");
        p.setString(1, m.getKode());
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            asetKredit=new Aset(r.getString("asetKredit"),d);
            asetDebit=new Aset(r.getString("asetDebit"),d);
            sumberMasuk=new Pemasukan(r.getString("sumberMasuk"),d);
            sumberKeluar=new Pengeluaran(r.getString("sumberKeluar"),d);
            bebanKredit=new Beban(r.getString("bebanKredit"),d);
            bebanDebit=new Beban(r.getString("bebanDebit"),d);
            wajibKredit=new Kewajiban(r.getString("wajibKredit"),d);
            wajibDebit=new Kewajiban(r.getString("wajibDebit"),d);
            dapatKredit=new Pendapatan(r.getString("dapatKredit"),d);
            dapatDebit=new Pendapatan(r.getString("dapatDebit"),d);
            deleted=r.getBoolean("deleted");
        }r.close();
        p.close();
    }

    public Hubungan(Pengeluaran k, raden.janoko.kubera.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.execPrepare("select*from hubungan where sumberKeluar=?");
        p.setString(1, k.getKode());
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            asetKredit=new Aset(r.getString("asetKredit"),d);
            asetDebit=new Aset(r.getString("asetDebit"),d);
            sumberMasuk=new Pemasukan(r.getString("sumberMasuk"),d);
            sumberKeluar=new Pengeluaran(r.getString("sumberKeluar"),d);
            bebanKredit=new Beban(r.getString("bebanKredit"),d);
            bebanDebit=new Beban(r.getString("bebanDebit"),d);
            wajibKredit=new Kewajiban(r.getString("wajibKredit"),d);
            wajibDebit=new Kewajiban(r.getString("wajibDebit"),d);
            dapatKredit=new Pendapatan(r.getString("dapatKredit"),d);
            dapatDebit=new Pendapatan(r.getString("dapatDebit"),d);
            deleted=r.getBoolean("deleted");
        }r.close();
        p.close();
    }

    public Aset getAsetKredit() {
        return asetKredit;
    }

    public void setAsetKredit(Aset asetKredit) {
        this.asetKredit = asetKredit;
    }

    public Aset getAsetDebit() {
        return asetDebit;
    }

    public void setAsetDebit(Aset asetDebit) {
        this.asetDebit = asetDebit;
    }

    public Pemasukan getSumberMasuk() {
        return sumberMasuk;
    }

    public void setSumberMasuk(Pemasukan sumberMasuk) {
        this.sumberMasuk = sumberMasuk;
    }

    public Pengeluaran getSumberKeluar() {
        return sumberKeluar;
    }

    public void setSumberKeluar(Pengeluaran sumberKeluar) {
        this.sumberKeluar = sumberKeluar;
    }

    public Beban getBebanKredit() {
        return bebanKredit;
    }

    public void setBebanKredit(Beban bebanKredit) {
        this.bebanKredit = bebanKredit;
    }

    public Beban getBebanDebit() {
        return bebanDebit;
    }

    public void setBebanDebit(Beban bebanDebit) {
        this.bebanDebit = bebanDebit;
    }

    public Kewajiban getWajibKredit() {
        return wajibKredit;
    }

    public void setWajibKredit(Kewajiban wajibKredit) {
        this.wajibKredit = wajibKredit;
    }

    public Kewajiban getWajibDebit() {
        return wajibDebit;
    }

    public void setWajibDebit(Kewajiban wajibDebit) {
        this.wajibDebit = wajibDebit;
    }

    public Pendapatan getDapatKredit() {
        return dapatKredit;
    }

    public void setDapatKredit(Pendapatan dapatKredit) {
        this.dapatKredit = dapatKredit;
    }

    public Pendapatan getDapatDebit() {
        return dapatDebit;
    }

    public void setDapatDebit(Pendapatan dapatDebit) {
        this.dapatDebit = dapatDebit;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}