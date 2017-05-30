/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.report;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author ai
 */
public class PengeluaranKas {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/report/pengeluaran_kas.jrxml");

    public static void init() throws FileNotFoundException {
        if(!Style2.f.exists())Style2.init();
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        PrintWriter o=new PrintWriter(f);
        o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        awalan(o);
        title(o);
        o.close();
    }

    private static void awalan(PrintWriter o) {
        o.print("<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" ");
        o.print("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        o.print("xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports ");
        o.print("http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"pengeluaran_kas\" pageWidth=\"595\" pageHeight=\"842\" ");
        o.print("columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" ");
        o.print("uuid=\"d40611c7-7673-4b15-bcc1-27e02b7623c0\">");
        o.print("<template><![CDATA[\"tema2.jrtx\"]]></template>");
        o.print("<queryString><![CDATA[]]></queryString>");
        o.print("<field name=\"judul\" class=\"java.lang.String\"/>");
        o.print("<field name=\"sub_judul\" class=\"java.lang.String\"/>");
        o.print("<field name=\"tgl\" class=\"java.sql.Date\"/>");
        o.print("<field name=\"rek\" class=\"java.lang.String\"/>");
        o.print("<field name=\"akumulasi\" class=\"raden.janoko.kubera.util.Rupiah\"/>");
        o.print("<field name=\"jumlah\" class=\"raden.janoko.kubera.util.Rupiah\"/>");
        o.print("<field name=\"ref\" class=\"java.lang.String\"/>");
        o.print("<field name=\"ket\" class=\"java.lang.String\"/>");
        o.print("<background><band splitType=\"Stretch\"/></background>");
    }

    private static void title(PrintWriter o) {
        o.print("<title><band height=\"63\" splitType=\"Stretch\">");
    }
}