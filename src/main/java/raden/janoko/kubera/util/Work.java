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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
        java.util.Date d=new java.util.Date();
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.kubera/error/"+d.getDay()+"-"+d.getMonth()+"-"+d.getYear()+"/"+
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
        d.getS().executeUpdate("create database "+nama);
        d.setName(nama);
        new raden.janoko.kubera.entity.dao.DAOAset(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOBeban(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOKewajiban(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOPendapatan(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOPemasukan(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOPengeluaran(d).createTable();
        new raden.janoko.kubera.entity.dao.DAOHubungan(d).createTable();
        d.close();
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
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "8");
        t.transform(ds, sr);
    }

    public static void simpanDBConfig(DBConfig db) throws ParserConfigurationException, IOException, ClassNotFoundException, 
            GeneralSecurityException, TransformerException{
        Document d=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        org.w3c.dom.Element e=d.createElement("kkk");
        d.appendChild(e);
        e.appendChild(XMLDataDB("d",db.getHost(),d));
        e.appendChild(XMLDataDB("dd",db.getName(),d));
        e.appendChild(XMLDataDB("ddd",""+db.getPort(),d));
        e.appendChild(XMLDataDB("dddd",db.getUser(),d));
        e.appendChild(XMLDataDB("ddddd",db.getPass(),d));
        e.appendChild(XMLDataDB("dddddd",db.getNp(),d));
        simpanXML(d);
    }

    public static void createReport() throws FileNotFoundException {
        if(!raden.janoko.kubera.report.PenerimaanKas.f.exists())raden.janoko.kubera.report.PenerimaanKas.init();
        if(!raden.janoko.kubera.report.PengeluaranKas.f.exists())raden.janoko.kubera.report.PengeluaranKas.init();
    }

    public static DBConfig loadDB() throws ParserConfigurationException, SAXException, IOException, GeneralSecurityException, ClassNotFoundException {
        DBConfig d=new DBConfig();
        Document doc=javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        d.setHost(loadDataXML("d",doc));
        d.setName(loadDataXML("dd",doc));
        d.setNp(loadDataXML("dddddd",doc));
        d.setPass(loadDataXML("ddddd",doc));
        d.setPort(Integer.parseInt(loadDataXML("ddd",doc)));
        d.setUser(loadDataXML("dddd",doc));
        return d;
    }

    private static String loadDataXML(String s, Document d) throws GeneralSecurityException, IOException, ClassNotFoundException {
        org.w3c.dom.NodeList nl=d.getElementsByTagName(s);
        String st="";
        RSA r=loadRSA();
        for(int x=0;x<nl.getLength();x++)if(nl.item(x).getNodeType()==org.w3c.dom.Node.ELEMENT_NODE){
            org.w3c.dom.Element e=(org.w3c.dom.Element) nl.item(x);
            st=r.decrypt(e.getTextContent());
            break;
        }return st;
    }

    public static boolean isKurangKas(DBConfig d) throws SQLException {
        boolean b=true;
        Db db=d.genDB();
        raden.janoko.kubera.entity.dao.DAOAset dao=new raden.janoko.kubera.entity.dao.DAOAset(db);
        b&=null!=dao.kas();
        db.close();
        return b;
    }
}