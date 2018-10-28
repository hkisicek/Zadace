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
import hkisicek_zadaca_3.Mjesto;
import java.util.ArrayList;
import java.util.List;

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
        zapisi.add("POKRECEM DRETVU");
        Generator generator = Generator.getInstance();

        List<Senzor> listaSenzora;
        List<Aktuator> listaAktuatora;

        int indeks = 0;
        long trenutnoVrijeme = 0, zavrsetak = 0;

        while (indeks < brojCiklusa) {
            
            trenutnoVrijeme = System.currentTimeMillis();
            zavrsetak = trenutnoVrijeme + trajanjeCiklusa;
            
            while (System.currentTimeMillis() < zavrsetak) {

                for (Mjesto mjesto : mjesta) {
                    listaSenzora = mjesto.getSenzoriMjesta();
                    listaAktuatora = mjesto.getAktuatoriMjesta();
                    listaNeispravnihSenzora = new ArrayList<>();
                    listaNeispravnihAktuatora = new ArrayList<>();
                    mjesto.setBrojProvjera(mjesto.getBrojProvjera() + 1);

                    for (Aktuator aktuator : listaAktuatora) {
                        int broj = generator.dajSlucajniBroj(1, 100);
                        if (aktuator.getProvjera() >= 3) {
                            listaNeispravnihAktuatora.add(aktuator);
                            zapisi.add("Kloniram neispravan aktuator:" + aktuator.getId() + " " + aktuator.getNaziv());

                        }
                        if (broj > ispravnost) {
                            mjesto.dodajNeispravanAktuator(aktuator.getId());
                            aktuator.setProvjera(aktuator.getProvjera() + 1);
                        }
                        
                        double ocitanaVrijednost = aktuator.getOcitanaVrijednost();
                        zapisi.add("Mijenjam aktuatoru vrijednost:" + aktuator.getId() + " " + aktuator.getNaziv());
                        if(ocitanaVrijednost < aktuator.getMaxVrijednost()){
                            
                        }
                    }

                    for (Senzor senzor : listaSenzora) {
                        int broj = generator.dajSlucajniBroj(1, 100);
                        if (senzor.getProvjera() >= 3) {
                            listaNeispravnihSenzora.add(senzor);
                            zapisi.add("Kloniram neispravan senzor:" + senzor.getId() + " " + senzor.getNaziv());
                        }
                        if (broj > ispravnost) {
                            mjesto.dodajNeispravanSenzor(senzor.getId());
                            senzor.setProvjera(senzor.getProvjera() + 1);
                        }
                        
                        double vrijednost = senzor.getOcitanaVrijednost();
                        zapisi.add("Mijenjam senzoru vrijednost:" + senzor.getId() + " " + senzor.getNaziv());
                    }

                    for (Aktuator aktuator : listaNeispravnihAktuatora) {
                        Aktuator a = aktuator.clone();
                        a.setId(AktuatorDatoteka.dohvatiZadnjiID() + 1);
                        mjesto.brisiAktuator(aktuator);
                        mjesto.dodajAktuator(a);
                    }

                    for (Senzor senzor : listaNeispravnihSenzora) {
                        Senzor s = senzor.clone();
                        s.setId(SenzorDatoteka.dohvatiZadnjiID() + 1);
                        mjesto.brisiSenzor(senzor);
                        mjesto.dodajSenzor(s);
                    }
                }
            }
            indeks++;
        }

        //super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<String> dohvatiZapise() {
        return zapisi;
    }
}
