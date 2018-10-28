/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import Datoteke.AktuatorDatoteka;
import Datoteke.SenzorDatoteka;
import Generator.Generator;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import hkisicek_zadaca_4.Mjesto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author helena
 */
public class GlavnaDretva extends Thread {

    private int trajanjeCiklusa, brojCiklusa, ispravnost;
    private List<Mjesto> mjesta;
    private static List<String> zapisi = new ArrayList<>();
    private List<Senzor> listaNeispravnihSenzora;
    private List<Aktuator> listaNeispravnihAktuatora;
    private Generator generator = Generator.getInstance();
    
    public GlavnaDretva(int trajanjeCiklusa, int brojCiklusa, List<Mjesto> mjesta, int ispravnost) {

        this.trajanjeCiklusa = trajanjeCiklusa * 1000;
        this.brojCiklusa = brojCiklusa;
        this.mjesta = mjesta;
        this.ispravnost = ispravnost;
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run() {
        
        long trenutnoVrijeme = System.currentTimeMillis();
        long zavrsnoVrijeme = trenutnoVrijeme + trajanjeCiklusa;
        
        for (int i = 0; i < brojCiklusa - 1; i++) {
            
            for(Mjesto mjesto : mjesta){
                
                List<Senzor> listaSenzora = mjesto.getSenzoriMjesta();
                List<Aktuator> listaAktuatora = mjesto.getAktuatoriMjesta();
                List<Senzor> listaNeispravnihS = new ArrayList<>();
                List<Aktuator> listaNeispravnihA = new ArrayList<>();
                
                //provjera statusa uređaja
                for(Senzor senzor : listaSenzora){
                    int broj = generator.dajSlucajniBroj(0, 100);
                    if(broj > ispravnost){
                        zapisi.add("Neispravan senzor: " + senzor.getNaziv());
                        mjesto.dodajNeispravniSenzor(senzor);
                    } else{
                        zapisi.add("Mijenjam vrijednosti senzora: " + senzor.getNaziv());
                        senzor.postaviVrijednost();
                    }
                }
                
                for(Aktuator aktuator : listaAktuatora){
                    int broj = generator.dajSlucajniBroj(0, 100);
                    if(broj > ispravnost){
                        zapisi.add("Imam neispravan aktuator.");
                        mjesto.dodajNeispravniAktuator(aktuator);
                    } else{
                        zapisi.add("Mijenjam vrijednosti aktuatora: " + aktuator.getNaziv());
                        aktuator.postaviVrijednost();
                    }
                }
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> dohvatiZapise() {
        return zapisi;
    }
}
