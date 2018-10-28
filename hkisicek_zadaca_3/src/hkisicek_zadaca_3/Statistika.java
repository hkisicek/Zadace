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
 * @author Helena
 */
public class Statistika {

    private List<Mjesto> mjesta;

    public Statistika(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }
    
    public List<String> dohvatiStatistiku() {
        List<String> statistika = new ArrayList<>();
        statistika.add("Ukupan broj mjesta: " + "|" + mjesta.size());
        statistika.add("-------------------------------------------------");
        for (Mjesto mjesto : mjesta) {
            statistika.add("Mjesto: " + mjesto.getNaziv());
            statistika.add("Broj aktuatora: " + mjesto.getBrojAktuatora());
            statistika.add("Broj senzora: " + mjesto.getBrojSenzora());
            statistika.add("Broj provjera: " + mjesto.getBrojProvjera());
            for (Senzor senzor : mjesto.getSenzoriMjesta()) {
                statistika.add("Senzor naziv: " + senzor.getNaziv());
            }
            for (Aktuator aktuator : mjesto.getAktuatoriMjesta()) {
                statistika.add("Aktuator naziv: " + aktuator.getNaziv());
            }
        }
        return statistika;
    }

    public List<String> dohvatiInfoMj(int id) {
        List<String> infoMjesto = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            if (mjesto.getId() == id) {
                infoMjesto.add("Id mjesta: " + mjesto.getId());
                infoMjesto.add("Naziv mjesta: " + mjesto.getNaziv());
                infoMjesto.add("Tip mjesta: " + mjesto.getTip());
                infoMjesto.add("Broj aktuatora: " + mjesto.getBrojAktuatora());
                infoMjesto.add("Broj senzora: " + mjesto.getBrojSenzora());
            }
        }
        return infoMjesto;
    }

    public List<String> dohvatiInfoSe(int id) {
        List<String> infoSenzor = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            for (Senzor senzor : mjesto.getSenzoriMjesta()) {
                if (senzor.getSlucajniBroj() == id) {
                    infoSenzor.add("Senzor ID: " + senzor.getId());
                    infoSenzor.add("Senzor naziv: " + senzor.getNaziv());
                    infoSenzor.add("Senzor tip: " + senzor.getTip());
                    infoSenzor.add("Senzor vrsta: " + senzor.getVrsta());
                    infoSenzor.add("Senzor max: " + senzor.getMaxVrijednost());
                    infoSenzor.add("Senzor min: " + senzor.getMinVrijednost());
                    infoSenzor.add("Ocitana vrijednost: " + senzor.getOcitanaVrijednost());
                }
            }
        }
        return infoSenzor;
    }

    public List<String> dohvatiInfoAkt(int id) {
        List<String> infoAktuator = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            for (Aktuator aktuator : mjesto.getAktuatoriMjesta()) {
                if (aktuator.getSlucajniBroj() == id) {
                    infoAktuator.add("Senzor ID: " + aktuator.getId());
                    infoAktuator.add("Senzor naziv: " + aktuator.getNaziv());
                    infoAktuator.add("Senzor tip: " + aktuator.getTip());
                    infoAktuator.add("Senzor vrsta: " + aktuator.getVrsta());
                    infoAktuator.add("Senzor max: " + aktuator.getMaxVrijednost());
                    infoAktuator.add("Senzor min: " + aktuator.getMinVrijednost());
                    infoAktuator.add("Ocitana vrijednost: " + aktuator.getOcitanaVrijednost());
                }
            }
        }
        return infoAktuator;
    }
}
