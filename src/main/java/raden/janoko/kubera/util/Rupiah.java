/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raden.janoko.kubera.util;

/**
 *
 * @author ai
 */
public class Rupiah {
    private long data;

    public Rupiah(long data) {
        this.data = data;
    }

    public Rupiah(){}

    @Override
    public String toString() {
        if(data<=0)return"-";
        String duwek="Rp ",ongko=",00";
        long l=data;
        int x=0;
        while(l>0){
            if(x==3){
                ongko="."+ongko;
                x=0;
            }long i=l%10;
            ongko=""+i+ongko;
            l=l/10;
            x++;
        }return duwek+ongko;
    }
}