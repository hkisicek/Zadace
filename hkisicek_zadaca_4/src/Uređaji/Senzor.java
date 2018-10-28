/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uređaji;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helena
 */
public class Senzor {

    private String naziv;
    private int id, tip, vrsta, slucajniBroj;
    private double minVrijednost, maxVrijednost;
    private int provjera = 0;
    private int razina_greske;
    private boolean popravljen = false;
    private double ocitanaVrijednost;
    private int brojUredjaja;
    
    private List<Senzor> kolekcijaSenzora = new ArrayList<>();
    
    private int trenutniBroj, maksimalanBroj, minimalanBroj;
    private int brojZamjena = 0;
    private int brojNabave = 0;

    public Senzor(int id, String naziv, int tip, int vrsta, double minVrijednost, double maxVrijednost) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.minVrijednost = minVrijednost;
        this.maxVrijednost = maxVrijednost;
    }

    public Senzor(int id, String naziv, int tip, int vrsta, double minVrijednost, double maxVrijednost, int slucajniBroj) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.minVrijednost = minVrijednost;
        this.maxVrijednost = maxVrijednost;
        this.slucajniBroj = slucajniBroj;
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

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }

    public double getMinVrijednost() {
        return minVrijednost;
    }

    public void setMinVrijednost(float minVrijednost) {
        this.minVrijednost = minVrijednost;
    }

    public double getMaxVrijednost() {
        return maxVrijednost;
    }

    public void setMaxVrijednost(float maxVrijednost) {
        this.maxVrijednost = maxVrijednost;
    }

    public int getSlucajniBroj() {
        return slucajniBroj;
    }

    public void setSlucajniBroj(int slucajniBroj) {
        this.slucajniBroj = slucajniBroj;
    }

    @Override
    public Senzor clone() {
        return new Senzor(this.id, this.naziv, this.tip, this.vrsta, this.minVrijednost, this.maxVrijednost, this.slucajniBroj);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvjera(int provjera) {
        this.provjera = provjera;
    }

    public int getProvjera() {
        return provjera;
    }

    public int getRazina_greske() {
        return razina_greske;
    }

    public void setRazina_greske(int razina_greske) {
        this.razina_greske = razina_greske;
    }

    public boolean isPopravljen() {
        return popravljen;
    }

    public void setPopravljen(boolean popravljen) {
        this.popravljen = popravljen;
    }

    public double getOcitanaVrijednost() {
        return ocitanaVrijednost;
    }

    public void setOcitanaVrijednost(double ocitanaVrijednost) {
        this.ocitanaVrijednost = ocitanaVrijednost;
    }

    public void postaviVrijednost() {
        if (this.tip == 0) {
            this.setOcitanaVrijednost((int) minVrijednost);
        } else if (this.tip == 3) {
            this.setOcitanaVrijednost((int) minVrijednost);
        } else {
            this.setOcitanaVrijednost(minVrijednost);
        }
    }

    public int getBrojUredjaja() {
        return brojUredjaja;
    }

    public void setBrojUredjaja(int brojUredjaja) {
        this.brojUredjaja = brojUredjaja;
    }
    
    public void dodajSenzorUKolekciju(Senzor senzor){
        this.kolekcijaSenzora.add(senzor);
    }

    public List<Senzor> getKolekcijaSenzora() {
        return kolekcijaSenzora;
    }

    public void setKolekcijaSenzora(List<Senzor> kolekcijaSenzora) {
        this.kolekcijaSenzora = kolekcijaSenzora;
    }

    public int getTrenutniBroj() {
        return trenutniBroj;
    }

    public void setTrenutniBroj(int trenutniBroj) {
        this.trenutniBroj = trenutniBroj;
    }

    public int getMaksimalanBroj() {
        return maksimalanBroj;
    }

    public void setMaksimalanBroj(int maksimalanBroj) {
        this.maksimalanBroj = maksimalanBroj;
    }

    public int getBrojZamjena() {
        return brojZamjena;
    }

    public void setBrojZamjena(int brojZamjena) {
        this.brojZamjena = brojZamjena;
    }

    public int getBrojNabave() {
        return brojNabave;
    }

    public void setBrojNabave(int brojNabave) {
        this.brojNabave = brojNabave;
    }

    public int getMinimalanBroj() {
        return minimalanBroj;
    }

    public void setMinimalanBroj(int minimalanBroj) {
        this.minimalanBroj = minimalanBroj;
    }
    
    
}
