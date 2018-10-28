/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uređaji;

/**
 *
 * @author helena
 */
public class Senzor {

    private String naziv;
    private int tip, vrsta, slucajniBroj;
    private float minVrijednost, maxVrijednost;

    public Senzor(String naziv, int tip, int vrsta, float minVrijednost, float maxVrijednost) {
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

    public float getMinVrijednost() {
        return minVrijednost;
    }

    public void setMinVrijednost(float minVrijednost) {
        this.minVrijednost = minVrijednost;
    }

    public float getMaxVrijednost() {
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
    public Senzor clone() throws CloneNotSupportedException {
        return new Senzor(this.naziv, this.tip, this.vrsta, this.minVrijednost, this.maxVrijednost);
    }
}
