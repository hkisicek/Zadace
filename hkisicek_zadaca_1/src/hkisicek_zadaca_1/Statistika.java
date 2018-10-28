/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_1;

import Datoteke.IzlazDatoteka;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Statistika {
    
    private IzlazDatoteka izlazDat = IzlazDatoteka.getInstance();
    private List<Mjesto> mjesta;

    public Statistika(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    public void ispis() {
        for (Mjesto mjesto : mjesta) {

            System.out.println("Statistika mjesto: " + mjesto.getNaziv());
            System.out.println("Broj aktuatora: " + mjesto.getBrojAktuatora());
            System.out.println("Broj senzora: " + mjesto.getBrojAktuatora());
            System.out.println("Broj provjera" + mjesto.getBrojProvjera());

            izlazDat.spremiuSpremnik("Statistika mjesto: " + mjesto.getNaziv());
            izlazDat.spremiuSpremnik("Broj aktuatora: " + mjesto.getBrojAktuatora());
            izlazDat.spremiuSpremnik("Broj senzora: " + mjesto.getBrojAktuatora());
            izlazDat.spremiuSpremnik("Broj provjera" + mjesto.getBrojProvjera());
            izlazDat.spremiuSpremnik("Tip mjesta: " + mjesto.getTip());

            for (Senzor senzor : mjesto.getSenzoriMjesta()) {

                System.out.println("Senzor nazic: " + senzor.getNaziv());
                System.out.println("Broj pogresaka: " + senzor.getBroj_pogresaka());
                System.out.println("Max : " + senzor.getMaxVrijednost());
                System.out.println("Min: " + senzor.getMinVrijednost());
                System.out.println("Tip: " + senzor.getTip());
                System.out.println("Vrijednost: " + senzor.getVrijednost());
                
                izlazDat.spremiuSpremnik("Senzor naziv" + senzor.getNaziv());
                izlazDat.spremiuSpremnik("Broj pogresaka" + senzor.getBroj_pogresaka());
                izlazDat.spremiuSpremnik("Max: " + senzor.getMaxVrijednost());
                izlazDat.spremiuSpremnik("Min: " + senzor.getMinVrijednost());
                izlazDat.spremiuSpremnik("Tip: " + senzor.getTip());
                izlazDat.spremiuSpremnik("Vrijednost: " + senzor.getVrijednost());
            }

            for (Aktuator aktuator : mjesto.getAktuatoriMjesta()) {
                
                System.out.println("Aktuator naziv: " + aktuator.getNaziv());
                System.out.println("Broj pogresaka: " + aktuator.getBroj_pogresaka());
                System.out.println("Max : " + aktuator.getMaxVrijednost());
                System.out.println("Min: " + aktuator.getMinVrijednost());
                System.out.println("Tip: " + aktuator.getTip());
                System.out.println("Vrijednost: " + aktuator.getVrijednost());
                
                izlazDat.spremiuSpremnik("Aktuator naziv" + aktuator.getNaziv());
                izlazDat.spremiuSpremnik("Broj pogresaka" + aktuator.getBroj_pogresaka());
                izlazDat.spremiuSpremnik("Max: " + aktuator.getMaxVrijednost());
                izlazDat.spremiuSpremnik("Min: " + aktuator.getMaxVrijednost());
                izlazDat.spremiuSpremnik("Tip: " + aktuator.getTip());
                izlazDat.spremiuSpremnik("Vrijednost: " + aktuator.getVrijednost());
            }
        }
    }
}
