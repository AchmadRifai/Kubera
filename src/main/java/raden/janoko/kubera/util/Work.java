/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author ai
 */
public class Work {
    public static java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/k");

    public static void styling() 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        String o=System.getProperty("os.name");
        if(o.contains("Linux"))style("GTK+");
        else if(o.contains("Windows"))style("Windows");
        else if(o.contains("Mac"))style("cocos");
        else style("Nimbus");
    }

    public static void hindar(Exception e){
        java.sql.Date d=java.sql.Date.valueOf(LocalDate.now());
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/error/"+d.getDate()+"-"+d.getMonth()+"-"+d.getYear()+"/"+
        d.getHours()+"-"+d.getMinutes()+"-"+d.getSeconds()+".log");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();try {
            java.io.PrintStream o=new java.io.PrintStream(f);
            e.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Work.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void style(String s) throws ClassNotFoundException, InstantiationException, 
            IllegalAccessException, UnsupportedLookAndFeelException {
        for(javax.swing.UIManager.LookAndFeelInfo l:javax.swing.UIManager.getInstalledLookAndFeels())
            if(s == null ? l.getName() == null : s.equals(l.getName())){
            javax.swing.UIManager.setLookAndFeel(l.getClassName());
            break;
        }
    }

    public static void initDB(String host, String nama, int port, String user, String pass) throws SQLException {
        Db d=new Db(host,"mysql",port,user,pass);
        d.exec("create database "+nama);
        d.setName(nama);
        d.close();
    }

    public static void saveDB(String host, String nama, int port, String user, String pass) throws GeneralSecurityException, 
            TransformerException, ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        org.w3c.dom.Element e=d.createElement("kkk");
        d.appendChild(e);
        e.appendChild(XMLDataDB("d",host,d));
        e.appendChild(XMLDataDB("dd",nama,d));
        e.appendChild(XMLDataDB("ddd",""+port,d));
        e.appendChild(XMLDataDB("dddd",user,d));
        e.appendChild(XMLDataDB("ddddd",pass,d));
        simpanXML(d);
    }

    private static Node XMLDataDB(String s, String v, Document d) throws GeneralSecurityException, IOException, ClassNotFoundException {
        org.w3c.dom.Element e=d.createElement(s);
        RSA r=loadRSA();
        e.setTextContent(r.encrypt(v));
        return e;
    }

    private static RSA loadRSA() throws GeneralSecurityException, IOException{
        return new RSA(new java.io.File(System.getProperty("user.home")+"/.kubera/kkk"),
        new java.io.File(System.getProperty("user.home")+"/.kubera/kk"));
    }

    private static void simpanXML(Document d) throws TransformerException {
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        javax.xml.transform.dom.DOMSource ds=new javax.xml.transform.dom.DOMSource(d);
        javax.xml.transform.stream.StreamResult sr=new javax.xml.transform.stream.StreamResult(f);
        javax.xml.transform.Transformer t=javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        t.transform(ds, sr);
    }
}