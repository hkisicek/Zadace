/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_3;

import Uređaji.Aktuator;
import Uređaji.Senzor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helena
 */
public class Mjesto {

    private String naziv;
    private int id, tip, brojSenzora, brojAktuatora, jednoznacniBroj;
    private List<Senzor> senzoriMjesta;
    private List<Aktuator> aktuatoriMjesta;
    private List<Integer> neispravniAktuator;
    private List<Integer> neispravniSenzor;
    private int brojProvjera = 0;

    public Mjesto(int id, String naziv, int tip, int brojSenzora, int brojAktuatora) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
        this.brojSenzora = brojSenzora;
        this.brojAktuatora = brojAktuatora;
        senzoriMjesta = new ArrayList<>();
        aktuatoriMjesta = new ArrayList<>();
    }

    public List<Senzor> dohvatiSenzore() {
        return senzoriMjesta;
    }

    public List<Aktuator> dohvatiAktuatore() {
        return aktuatoriMjesta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getBrojSenzora() {
        return brojSenzora;
    }

    public void setBrojSenzora(int brojSenzora) {
        this.brojSenzora = brojSenzora;
    }

    public int getBrojAktuatora() {
        return brojAktuatora;
    }

    public void setJednoznacniBroj(int jednoznacniBroj) {
        this.jednoznacniBroj = jednoznacniBroj;
    }

    public int getJednoznacniBroj() {
        return jednoznacniBroj;
    }

    public void setBrojAktuatora(int brojAktuatora) {
        this.brojAktuatora = brojAktuatora;
    }

    //dodavanje aktuatora           
    public void dodajAktuator(Aktuator aktuator) {
        this.aktuatoriMjesta.add(aktuator);
    }

    //brisanje aktuatora
    public void brisiAktuator(Aktuator aktuator) {
        this.aktuatoriMjesta.remove(aktuator);
    }

    //dodavanje senzora
    public void dodajSenzor(Senzor senzor) {
        this.senzoriMjesta.add(senzor);
    }

    public void brisiSenzor(Senzor senzor) {
        this.senzoriMjesta.remove(senzor);
    }

    public Senzor getSenzor(String naziv) {
        for (Senzor se : senzoriMjesta) {
            if (se.getNaziv().equals(se)) {
                return se;
            }
        }
        return null;
    }

    public Aktuator getAktuator(String naziv) {

        for (Aktuator ak : aktuatoriMjesta) {
            if (ak.getNaziv().equals(ak)) {
                return ak;
            }
        }
        return null;
    }

    public void dodajNeispravanAktuator(int id) {
        this.neispravniAktuator.add(id);
    }

    public void dodajNeispravanSenzor(int id) {
        this.neispravniSenzor.add(id);
    }

    public int getBrojProvjera() {
        return brojProvjera;
    }

    public void setBrojProvjera(int brojProvjera) {
        this.brojProvjera = brojProvjera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Senzor> getSenzoriMjesta() {
        return senzoriMjesta;
    }

    public List<Aktuator> getAktuatoriMjesta() {
        return aktuatoriMjesta;
    }

    public Senzor dohvatiSenzorSlucajni(int slucajni) {
        Senzor senzorMjesto = null;
        for (Senzor senzor : senzoriMjesta) {
            if (senzor.getSlucajniBroj() == slucajni) {
                senzorMjesto = senzor;
            }
        }
        return senzorMjesto;
    }
}
