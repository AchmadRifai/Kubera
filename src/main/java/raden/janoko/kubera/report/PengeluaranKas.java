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
        columnHeader(o);
        detail(o);
        footer(o);
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
        o.print("<textField><reportElement style=\"judul1\" x=\"0\" y=\"0\" width=\"550\" ");
        o.print("height=\"30\" uuid=\"9fc9d2b1-a8a8-47d4-b35c-14ac3af78623\"/>");
        o.print("<textFieldExpression><![CDATA[$F{judul}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"subJudul\" x=\"0\" y=\"30\" width=\"550\" ");
        o.print("height=\"30\" uuid=\"42a00cd0-841d-4f48-bf0e-de023b44f825\"/>");
        o.print("<textFieldExpression><![CDATA[$F{sub_judul}]]></textFieldExpression>");
        o.print("</textField></band></title>");
    }

    private static void columnHeader(PrintWriter o) {
        o.print("<columnHeader><band height=\"50\" splitType=\"Stretch\">");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"0\" y=\"0\" width=\"100\" ");
        o.print("height=\"30\" uuid=\"18d48488-399e-44cf-bf9f-078c9c880ecd\"/>");
        o.print("<text><![CDATA[Tanggal]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"100\" y=\"0\" width=\"80\" ");
        o.print("height=\"30\" uuid=\"388667a1-e10c-4e2c-9926-04122faf2641\"/>");
        o.print("<text><![CDATA[Kode Rek. Lawan]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"180\" y=\"0\" width=\"170\" ");
        o.print("height=\"30\" uuid=\"496667f5-2f77-4d3f-9f52-4cca5b22671b\"/>");
        o.print("<text><![CDATA[Uraian]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"470\" y=\"0\" width=\"80\" ");
        o.print("height=\"30\" uuid=\"59e10411-ee74-4440-b858-a1b85da1a908\"/>");
        o.print("<text><![CDATA[Akumulasi]]></text></staticText>");
        columnH2(o);
    }

    private static void columnH2(PrintWriter o) {
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"400\" y=\"0\" width=\"70\" ");
        o.print("height=\"30\" uuid=\"f130561c-dc9d-4bd3-8448-8a8728060361\"/>");
        o.print("<text><![CDATA[Jumlah]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"350\" y=\"0\" width=\"50\" ");
        o.print("height=\"30\" uuid=\"ae4a9e4d-2c74-4702-8367-66204e48dda3\"/>");
        o.print("<text><![CDATA[Ref.]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"0\" y=\"30\" width=\"100\" ");
        o.print("height=\"20\" uuid=\"9795fc3d-5e2a-4f95-a777-7dd10028e525\"/>");
        o.print("<text><![CDATA[1]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"100\" y=\"30\" width=\"80\" ");
        o.print("height=\"20\" uuid=\"5f3ee85c-e7cc-4f29-9c41-3d16f3ec98bd\"/>");
        o.print("<text><![CDATA[2]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"470\" y=\"30\" width=\"80\" ");
        o.print("height=\"20\" uuid=\"f5ec2885-eb33-4062-9f57-5447add9d5d5\"/>");
        o.print("<text><![CDATA[6]]></text></staticText>");
        columnH3(o);
    }

    private static void columnH3(PrintWriter o) {
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"400\" y=\"30\" width=\"70\" ");
        o.print("height=\"20\" uuid=\"694bfa86-e0db-46f2-9549-48f53fc6910e\"/>");
        o.print("<text><![CDATA[5]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"350\" y=\"30\" width=\"50\" ");
        o.print("height=\"20\" uuid=\"3b139487-2405-4ec1-97f6-ffefdce16b37\"/>");
        o.print("<text><![CDATA[4]]></text></staticText>");
        o.print("<staticText><reportElement style=\"TblHeader2\" x=\"180\" y=\"30\" width=\"170\" ");
        o.print("height=\"20\" uuid=\"289931f5-4991-4dae-b72d-bd9a4ebe0cf1\"/>");
        o.print("<text><![CDATA[3]]></text></staticText>");
        o.print("</band></columnHeader>");
    }

    private static void detail(PrintWriter o) {
        o.print("<detail><band height=\"22\" splitType=\"Stretch\">");
        o.print("<textField><reportElement style=\"data1\" x=\"0\" y=\"0\" width=\"100\" ");
        o.print("height=\"20\" uuid=\"77ac6239-0f82-43e0-908b-4a6b3684fa97\"/>");
        o.print("<textFieldExpression><![CDATA[$F{tgl}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"data1\" x=\"100\" y=\"0\" width=\"80\"");
        o.print(" height=\"20\" uuid=\"52202c1f-1cda-4c7a-9cb8-166fb9999866\"/>");
        o.print("<textFieldExpression><![CDATA[$F{rek}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"dataUang\" x=\"470\" y=\"0\" width=\"80\"");
        o.print(" height=\"20\" uuid=\"68e10aa1-4428-4d75-a523-34df975cc3b1\"/>");
        o.print("<textFieldExpression><![CDATA[$F{akumulasi}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"dataUang\" x=\"400\" y=\"0\" width=\"70\"");
        o.print(" height=\"20\" uuid=\"85b564af-ab9d-4eb5-b715-fb90eb16c741\"/>");
        o.print("<textFieldExpression><![CDATA[$F{jumlah}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"data1\" x=\"350\" y=\"0\" width=\"50\"");
        o.print(" height=\"20\" uuid=\"17dc2424-e783-4883-ad85-0961b47dde01\"/>");
        o.print("<textFieldExpression><![CDATA[$F{ref}]]></textFieldExpression></textField>");
        detail2(o);
    }

    private static void detail2(PrintWriter o) {
        o.print("<textField><reportElement style=\"data1\" x=\"180\" y=\"0\" width=\"170\"");
        o.print(" height=\"20\" uuid=\"9a00ad8b-0db7-4656-9ddb-db39840d1b58\"/>");
        o.print("<textFieldExpression><![CDATA[$F{ket}]]></textFieldExpression>");
        o.print("</textField></band></detail>");
    }

    private static void footer(PrintWriter o) {
        o.print("<pageFooter><band height=\"39\" splitType=\"Stretch\">");
        o.print("<textField><reportElement x=\"450\" y=\"0\" width=\"100\"");
        o.print(" height=\"30\" uuid=\"66f7e9ea-37db-49df-b159-779cf37685f0\"/>");
        o.print("<textFieldExpression><![CDATA[$V{PAGE_NUMBER}]]></textFieldExpression>");
        o.print("</textField></band></pageFooter></jasperReport>");
    }
}