/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import hkisicek_zadaca_1.Mjesto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SINGLETON
 *
 * @author helena
 */
public class IzlazDatoteka implements Datoteka {

    public List<String> podaci = new ArrayList<>();
    public int brojLinija;
    private static IzlazDatoteka instance;

    String naziv;
    File file;
    BufferedWriter writer;

    private IzlazDatoteka() {
    }

    public static IzlazDatoteka getInstance() {
        if (instance == null) {
            instance = new IzlazDatoteka();
        }
        return instance;
    }

    public void spremiuSpremnik(String linija) {
        podaci.add(linija);
    }

    @Override
    public void otvoriDatoteku() {
        file = new File(naziv);
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException ex) {
            Logger.getLogger(IzlazDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setBrojLinija(int brojLinija) {
        this.brojLinija = brojLinija;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public void provjeriDatoteku() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zatvoriDatoteku() {
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(IzlazDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void zapisi() {
        otvoriDatoteku();
        try {
            for(String podatak : podaci){
            writer.write(podatak);
            }
        } catch (IOException ex) {
            Logger.getLogger(IzlazDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        }
        zatvoriDatoteku();
    }

    @Override
    public List<Object> vratiProcitano() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mjesto> vratiMjesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
