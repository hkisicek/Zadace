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
public class Aktuator {

    private String naziv;
    private int tip, vrsta, minVrijednost, maxVrijednost, slucajniBroj;
    private List<Senzor> senzori=new ArrayList<>();
    private boolean ispravnost;
    private int broj_pogresaka = 0;
    private double vrijednost;

    public Aktuator(String naziv, int tip, int vrsta, int minVrijednost, int maxVrijednost) {
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.minVrijednost = minVrijednost;
        this.maxVrijednost = maxVrijednost;
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

    public int getMinVrijednost() {
        return minVrijednost;
    }

    public void setMinVrijednost(int minVrijednost) {
        this.minVrijednost = minVrijednost;
    }

    public int getMaxVrijednost() {
        return maxVrijednost;
    }

    public void setMaxVrijednost(int maxVrijednost) {
        this.maxVrijednost = maxVrijednost;
    }

    public int getSlucajniBroj() {
        return slucajniBroj;
    }

    public void setSlucajniBroj(int slucajniBroj) {
        this.slucajniBroj = slucajniBroj;
    }

    public void setSenzori(List<Senzor> senzori) {
        this.senzori = senzori;
    }

    public List<Senzor> getSenzori() {
        return senzori;
    }
    
    public void dodajSenzor(Senzor senzor){
        this.senzori.add(senzor);
    }

    @Override
    public Aktuator clone(){
        return new Aktuator(this.naziv, this.tip, this.vrsta, this.minVrijednost, this.maxVrijednost);
    }

    public boolean isIspravnost() {
        return ispravnost;
    }

    public void setIspravnost(boolean ispravnost) {
        this.ispravnost = ispravnost;
    }

    public int getBroj_pogresaka() {
        return broj_pogresaka;
    }

    public void setBroj_pogresaka(int broj_pogresaka) {
        this.broj_pogresaka = broj_pogresaka;
    }   

    public double getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(double vrijednost) {
        this.vrijednost = vrijednost;
    }
    
    
}
