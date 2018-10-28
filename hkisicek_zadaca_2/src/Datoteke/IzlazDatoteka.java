/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Visitor.Visitable;
import Visitor.Visitor;
import Visitor.VisitorInterface;
import hkisicek_zadaca_2.Mjesto;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
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
public class IzlazDatoteka implements Datoteka, Visitable {

    public Writer writer = null;
    public List<String> podaci=new ArrayList<>();
    public String naziv;
    public int brojLinija;
    VisitorInterface visitor = new Visitor();
    private static IzlazDatoteka instance;

    private IzlazDatoteka() {}

    public static IzlazDatoteka getInstance() {
        if (instance == null) {
            instance = new IzlazDatoteka();
        }
        return instance;
    }

    public void spremiuSpremnik(String linija) {
        podaci.add(linija);
        if (podaci.size() > brojLinija) {
            accept(visitor);
            otvoriDatoteku();
            podaci.clear();
        }
    }

    @Override
    public void otvoriDatoteku() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(naziv,true), "utf-8"));
            for (int i = 0; i <= podaci.size()-1; i++) {
                writer.write(podaci.get(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(IzlazDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(IzlazDatoteka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zapisi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> vratiProcitano() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mjesto[] vratiMjesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
