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
class Style1 {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/report/tema1.jrtx");

    static void init() throws FileNotFoundException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        PrintWriter o=new PrintWriter(f);
        o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        o.print("<!DOCTYPE jasperTemplate PUBLIC \"-//JasperReports//DTD Template//EN\" ");
        o.print("\"http://jasperreports.sourceforge.net/dtds/jaspertemplate.dtd\">");
        o.print("<jasperTemplate>");
        gaya1(o);
        o.print("</jasperTemplate>");
        o.close();
    }

    private static void gaya1(PrintWriter o) {
        o.print("<style name=\"judul1\" mode=\"Opaque\" forecolor=\"#000000\" backcolor=\"#FFFFFF\" ");
        o.print("hTextAlign=\"Center\" vTextAlign=\"Middle\" fontName=\"Times New Roman\" ");
        o.print("fontSize=\"14\" isBold=\"true\"/>");
        o.print("<style name=\"subJudul\" mode=\"Opaque\" forecolor=\"#000000\" backcolor=\"#FFFFFF\" ");
        o.print("hTextAlign=\"Center\" vTextAlign=\"Middle\" fontName=\"Times New Roman\" fontSize=\"14\"/>");
        o.print("<style name=\"tblHeader1\" mode=\"Opaque\" forecolor=\"#000000\" backcolor=\"#88E4F2\" ");
        o.print("hTextAlign=\"Center\" vTextAlign=\"Middle\" fontName=\"Times New Roman\" fontSize=\"12\">");
        o.print("<pen lineStyle=\"Solid\" lineColor=\"#000000\"/>");
        o.print("</style>");
        gaya2(o);
    }

    private static void gaya2(PrintWriter o) {
        o.print("<style name=\"data1\" mode=\"Opaque\" forecolor=\"#000000\" backcolor=\"#FFFFFF\" ");
        o.print("hTextAlign=\"Left\" vTextAlign=\"Middle\" fontName=\"Times New Roman\" fontSize=\"12\">");
        o.print("<pen lineStyle=\"Solid\"/>");
        o.print("</style>");
        o.print("<style name=\"dataUang\" style=\"data1\" hTextAlign=\"Right\"/>");
    }
}