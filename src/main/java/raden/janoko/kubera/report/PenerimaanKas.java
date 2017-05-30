/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.report;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author ai
 */
public class PenerimaanKas {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/report/penerimaan_kas.jrxml");

    public static void init() throws FileNotFoundException {
        if(!Style1.f.exists())Style1.init();
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        java.io.PrintStream o=new java.io.PrintStream(f);
        opener(o);
        inisial(o);
        title(o);
        columnHeader(o);
        detail(o);
        footer(o);
        o.print("</jasperReport>");
        o.close();
    }

    private static void inisial(PrintStream o) {
        o.print("<template><![CDATA[\"tema1.jrtx\"]]></template>");
        o.print("<queryString><![CDATA[]]></queryString>");
        o.print("<field name=\"judule\" class=\"java.lang.String\"/>");
        o.print("<field name=\"sub_judul\" class=\"java.lang.String\"/>");
        o.print("<field name=\"tgl\" class=\"java.sql.Date\"/>");
        o.print("<field name=\"jumlah\" class=\"raden.janoko.kubera.util.Rupiah\"/>");
        o.print("<field name=\"akumulasi\" class=\"raden.janoko.kubera.util.Rupiah\"/>");
        o.print("<field name=\"rek\" class=\"java.lang.String\"/>");
        o.print("<field name=\"ref\" class=\"java.lang.String\"/>");
        o.print("<field name=\"ket\" class=\"java.lang.String\"/>");
        o.print("<background><band splitType=\"Stretch\"/></background>");
    }

    private static void title(PrintStream o) {
        o.print("<title><band height=\"80\" splitType=\"Stretch\">");
        o.print("<textField><reportElement key=\"\" style=\"judul1\" x=\"0\" y=\"0\" width=\"550\" height=\"30\" ");
        o.print("uuid=\"2e3ef284-b59d-41ea-b142-65d88deb91f1\"/>");
        o.print("<textFieldExpression><![CDATA[$F{judule}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"subJudul\" x=\"0\" y=\"30\" width=\"550\" height=\"30\" ");
        o.print("uuid=\"fc0db69a-79fe-4b94-b108-326af6a339a0\"/>");
        o.print("<textFieldExpression><![CDATA[$F{sub_judul}]]></textFieldExpression></textField>");
        o.print("</band></title>");
    }

    private static void columnHeader(PrintStream o) {
        o.print("<columnHeader><band height=\"31\" splitType=\"Stretch\">");
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"0\" y=\"0\" width=\"100\" height=\"30\" ");
        o.print("uuid=\"1ca24cc3-2a85-4cba-bdb5-5ba85a7951d8\"/>");
        o.print("<text><![CDATA[Tanggal]]></text></staticText>");
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"100\" y=\"0\" width=\"80\" height=\"30\" ");
        o.print("uuid=\"17bf0719-2caf-41c6-9e7c-5af169e5bbe0\"/>");
        o.print("<text><![CDATA[Kode Rek. Lawan]]></text></staticText>");
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"180\" y=\"0\" width=\"160\" height=\"30\" ");
        o.print("uuid=\"6df69c42-c8ff-4601-b81e-320f4c41405e\"/>");
        o.print("<text><![CDATA[Uraian]]></text></staticText>");
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"340\" y=\"0\" width=\"60\" height=\"30\" ");
        o.print("uuid=\"b77d5e53-eb14-46ec-9e42-ca2cd4009b81\"/>");
        o.print("<text><![CDATA[Ref.]]></text></staticText>");
        closingColumnHeader(o);
    }

    private static void opener(PrintStream o) {
        o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        o.print("<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" ");
        o.print("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        o.print("xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" ");
        o.print("name=\"penerimaan_kas\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" ");
        o.print("rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"759f662f-a309-4a23-8bb0-7920dfad7d6b\">");
    }

    private static void closingColumnHeader(PrintStream o) {
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"400\" y=\"0\" width=\"80\" ");
        o.print("height=\"30\" uuid=\"49d8d4a6-6bc6-467f-8c90-8e647eb1a6e8\"/>");
        o.print("<text><![CDATA[Jumlah]]></text></staticText>");
        o.print("<staticText><reportElement style=\"tblHeader1\" x=\"480\" y=\"0\" width=\"70\" ");
        o.print("height=\"30\" uuid=\"a73a41d3-f110-4bb2-89f7-ba3f437120cb\"/>");
        o.print("<text><![CDATA[Akumulasi]]></text></staticText>");
        o.print("</band></columnHeader>");
    }

    private static void detail(PrintStream o) {
        o.print("<detail><band height=\"31\" splitType=\"Stretch\">");
        o.print("<textField><reportElement style=\"data1\" x=\"0\" y=\"0\" width=\"100\" height=\"30\" ");
        o.print("uuid=\"58dfa811-6c67-40a0-ae49-9212f1b0a93a\"/>");
        o.print("<textFieldExpression><![CDATA[$F{tgl}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"dataUang\" x=\"400\" y=\"0\" width=\"80\" ");
        o.print("height=\"30\" uuid=\"3f5985e2-a73e-4aff-ba60-0173a6af3f85\"/>");
        o.print("<textFieldExpression><![CDATA[$F{jumlah}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"dataUang\" x=\"480\" y=\"0\" width=\"70\" ");
        o.print("height=\"30\" uuid=\"79769bda-e6b9-4c95-9272-b494fd2312f9\"/>");
        o.print("<textFieldExpression><![CDATA[$F{akumulasi}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"data1\" x=\"100\" y=\"0\" width=\"80\" height=\"30\" ");
        o.print("uuid=\"eb3c2f7e-072f-4014-a79c-cd48ac451a04\"/>");
        o.print("<textFieldExpression><![CDATA[$F{rek}]]></textFieldExpression></textField>");
        closingDetail(o);
    }

    private static void closingDetail(PrintStream o) {
        o.print("<textField><reportElement style=\"data1\" x=\"340\" y=\"0\" width=\"60\" ");
        o.print("height=\"30\" uuid=\"1df449c5-af21-4c74-921c-c132cbe6e854\"/>");
        o.print("<textFieldExpression><![CDATA[$F{ref}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"data1\" x=\"180\" y=\"0\" width=\"160\" height");
        o.print("=\"30\" uuid=\"3466c021-ee7c-4df8-9361-70c5131d7a75\"/>");
        o.print("<textFieldExpression><![CDATA[$F{ket}]]></textFieldExpression></textField>");
        o.print("</band></detail>");
    }

    private static void footer(PrintStream o) {
        o.print("<pageFooter><band height=\"60\" splitType=\"Stretch\">");
        o.print("<textField><reportElement x=\"450\" y=\"30\" width=\"100\" height=\"30\" ");
        o.print("uuid=\"91948764-27a3-4633-b990-72f60fb76d50\"/>");
        o.print("<textFieldExpression><![CDATA[$V{PAGE_NUMBER}]]></textFieldExpression>");
        o.print("</textField>");
        o.print("</band></pageFooter>");
    }
}