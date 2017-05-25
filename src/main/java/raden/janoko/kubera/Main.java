/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ai
 */
public class Main {
    public static void main(String[]args){
        try {
            raden.janoko.kubera.util.Work.styling();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            raden.janoko.kubera.util.Work.hindar(ex);
        }java.awt.EventQueue.invokeLater(()->{
            if(!raden.janoko.kubera.util.Work.f.exists())new raden.janoko.kubera.ui.DBCon().setVisible(true);
        });
    }
}