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
class Style2 {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/report/tema2.jrtx");

    static void init() throws FileNotFoundException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        PrintWriter o=new PrintWriter(f);
        o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        awalan(o);
        o.close();
    }

    private static void awalan(PrintWriter o) {
        o.print("<!DOCTYPE jasperTemplate PUBLIC \"-//JasperReports//DTD Template//EN\" ");
        o.print("\"http://jasperreports.sourceforge.net/dtds/jaspertemplate.dtd\">");
        o.print("<jasperTemplate>");
        o.print("<template><![CDATA[tema1.jrtx]]></template>");
        o.print("<style name=\"TblHeader2\" style=\"tblHeader1\" backcolor=\"#FA666E\"/>");
        o.print("</jasperTemplate>");
    }
}