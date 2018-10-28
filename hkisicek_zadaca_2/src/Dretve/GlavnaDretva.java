/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import AlgoritmiProvjere.Algoritam;
import AlgoritmiProvjere.AlgoritamFactory;
import AlgoritmiProvjere.AlgoritamSlijedno;
import Generator.Generator;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import hkisicek_zadaca_2.Mjesto;
import java.util.List;

/**
 *
 * @author helena
 */
public class GlavnaDretva extends Thread {

    private int trajanjeCiklusa, brojCiklusa;
    private String algoritam;
    private Mjesto[] mjesta;
    private Mjesto dohvacenoMjesto;

    public GlavnaDretva(int trajanjeCiklusa, int brojCiklusa, Mjesto[] mjesta, String algoritam) {

        this.trajanjeCiklusa = trajanjeCiklusa * 1000;
        this.brojCiklusa = brojCiklusa;
        this.mjesta = mjesta;
        this.algoritam = algoritam;
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        AlgoritamFactory tvornica = new AlgoritamFactory(mjesta);
        Algoritam odabraniAlgoritam = tvornica.odaberiAlgoritam(algoritam);
        Generator generator = Generator.getInstance();

        List<Senzor> listaSenzora;
        List<Aktuator> listaAktuatora;

        int indeks = 0;
        long trenutnoVrijeme = 0, zavrsetak = 0;

        System.out.println("\n-----------------ULAZIM U PROVJERAVANJE UREDJAJA--------------");
        while (indeks < brojCiklusa) {

            dohvacenoMjesto = odabraniAlgoritam.dajMjesto(indeks);
            dohvacenoMjesto.setBrojProvjera(dohvacenoMjesto.getBrojProvjera() + 1);

            trenutnoVrijeme = System.currentTimeMillis();
            zavrsetak = trenutnoVrijeme + trajanjeCiklusa;

            //provjeravanje uredjaja i ostale akcije
            while (System.currentTimeMillis() < zavrsetak) {
                listaSenzora = dohvacenoMjesto.dohvatiSenzore();
                listaAktuatora = dohvacenoMjesto.dohvatiAktuatore();

                System.out.println("\nProvjeravam aktuatore");
                for (Aktuator aktuator : listaAktuatora) {
                    int broj = generator.dajSlucajniBroj(1, 1000);
                    if (broj >= 900 && broj <= 1000) {
                        dohvacenoMjesto.dodajNeispravan(aktuator.getNaziv() + ";" + aktuator.getSlucajniBroj());

                    }
                }
                System.out.println("\nProvjeravam senzore");
                for (Senzor senzor : listaSenzora) {
                    int broj = generator.dajSlucajniBroj(1, 1000);
                    if (broj >= 900 && broj <= 1000) {
                        dohvacenoMjesto.dodajNeispravan(senzor.getNaziv() + ";" + senzor.getSlucajniBroj());
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

}
