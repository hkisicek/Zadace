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
import hkisicek_zadaca_1.Mjesto;
import hkisicek_zadaca_1.Statistika;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helena
 */
public class GlavnaDretva extends Thread {

    private int trajanjeCiklusa, brojCiklusa;
    private String algoritam;
    private List<Mjesto> mjesta;
    private Mjesto dohvacenoMjesto = null;
    private Statistika statistika;

    public GlavnaDretva(int trajanjeCiklusa, int brojCiklusa, List<Mjesto> mjesta, String algoritam) {

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

        List<Senzor> listaSenzora = new ArrayList<>();
        List<Aktuator> listaAktuatora = new ArrayList<>();

        List<Senzor> listaNesipravnihSenzora = new ArrayList<>();
        List<Aktuator> listaNeispravnihAktuatora = new ArrayList<>();

        List<Senzor> listaNovihSenzora = new ArrayList<>();
        List<Aktuator> listaNovihAktuatora = new ArrayList<>();

        int indeks = 0;
        long trenutnoVrijeme = 0, zavrsetak = 0;

        List<Mjesto> algMjesta = new ArrayList<>();
        algMjesta = odabraniAlgoritam.dajMjesta();

        System.out.println("\n-----------------ULAZIM U PROVJERAVANJE UREDJAJA--------------");
        while (indeks < brojCiklusa) {

            trenutnoVrijeme = System.currentTimeMillis();
            zavrsetak = trenutnoVrijeme + trajanjeCiklusa;

            if(indeks < algMjesta.size()){
            dohvacenoMjesto = algMjesta.get(indeks);
            } else{
                dohvacenoMjesto = algMjesta.get(indeks % algMjesta.size());
            }
            dohvacenoMjesto.setBrojProvjera(dohvacenoMjesto.getBrojProvjera() + 1);
            //provjeravanje uredjaja i ostale akcije
            while (System.currentTimeMillis() < zavrsetak) {
                listaSenzora = dohvacenoMjesto.dohvatiSenzore();
                listaAktuatora = dohvacenoMjesto.dohvatiAktuatore();

                for (Aktuator aktuator : listaAktuatora) {
                    System.out.println("\nProvjeravam aktuator: " + aktuator.getNaziv() + " " + " mjesta: " + dohvacenoMjesto.getNaziv());
                    int broj = generator.dajSlucajniBroj(1, 1000);
                    if (broj >= 900 && broj <= 1000) {
                        System.out.println("Uređaj: " + aktuator.getNaziv() + "mjesta: " + dohvacenoMjesto.getNaziv() + "je vratio status 0.");
                        dohvacenoMjesto.dodajNeispravan(aktuator.getNaziv() + ";" + aktuator.getSlucajniBroj());
                        aktuator.setBroj_pogresaka(aktuator.getBroj_pogresaka() + 1);
                    }

                    if (aktuator.getBroj_pogresaka() == 3) {
                        System.out.println("Zamjenjujem uređaj: " + aktuator.getNaziv() + "" + "na mjestu: " + dohvacenoMjesto.getNaziv());
                        Aktuator a = aktuator.clone();
                        a.setBroj_pogresaka(0);
                        listaNeispravnihAktuatora.add(aktuator);
                        listaNovihAktuatora.add(a);

                        //inicijalizacija novo dodanog
                        int brojG = generator.dajSlucajniBroj(1, 100);
                        System.out.println("Inicijalizacija novog uredjaja.");
                        if (brojG > 90) {
                            System.out.println("Uredjaj: " + a.getNaziv() + "je neispravan. Brisem uredjaj.");
                            listaNeispravnihAktuatora.add(a);
                        }
                    }
                }

                for (Senzor senzor : listaSenzora) {
                    System.out.println("\nProvjeravam senzor: " + senzor.getNaziv() + " " + "mjesta: " + dohvacenoMjesto.getNaziv());
                    int broj = generator.dajSlucajniBroj(1, 1000);
                    if (broj >= 900 && broj <= 1000) {
                        System.out.println("Uređaj: " + senzor.getNaziv() + "mjesta: " + dohvacenoMjesto.getNaziv() + "je vratio status 0.");
                        dohvacenoMjesto.dodajNeispravan(senzor.getNaziv() + ";" + senzor.getSlucajniBroj());
                        senzor.setBroj_pogresaka(senzor.getBroj_pogresaka() + 1);
                    }

                    if (senzor.getBroj_pogresaka() == 3) {
                        System.out.println("Zamjenjujem uređaj: " + senzor.getNaziv() + "" + "na mjestu: " + dohvacenoMjesto.getNaziv());
                        Senzor s = senzor.clone();
                        s.setBroj_pogresaka(0);
                        listaNesipravnihSenzora.add(senzor);
                        listaNovihSenzora.add(s);

                        //inicijalizacija novo dodanog
                        int brojG = generator.dajSlucajniBroj(1, 100);
                        System.out.println("Inicijalizacija novog uredjaja.");
                        if (brojG > 90) {
                            System.out.println("Uredjaj: " + s.getNaziv() + "je neispravan. Brisem uredjaj.");
                            listaNesipravnihSenzora.add(s);
                        }

                    }

                }

                for (Aktuator aktuator : listaNeispravnihAktuatora) {
                    dohvacenoMjesto.brisiAktuator(aktuator);
                }

                for (Senzor senzor : listaNesipravnihSenzora) {
                    dohvacenoMjesto.brisiSenzor(senzor);
                }

                for (Senzor senzor : listaNovihSenzora) {
                    dohvacenoMjesto.dodajSenzor(senzor);
                }

                for (Aktuator aktuator : listaNovihAktuatora) {
                    dohvacenoMjesto.dodajAktuator(aktuator);
                }

                for (Senzor senzor : dohvacenoMjesto.getSenzoriMjesta()) {
                    promijeniVrijednostS(senzor);
                    System.out.println("Ocitavam vrijednost senzora:" + senzor.getVrijednost());
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                    }
                }

                for (Aktuator aktuator : dohvacenoMjesto.getAktuatoriMjesta()) {
                    promijeniVrijednostA(aktuator);
                    System.out.println("Ocitavam vrijednost aktuatora: " + aktuator.getVrijednost());
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            indeks++;

        }
        //super.run(); //To change body of generated methods, choose Tools | Templates.
        statistika = new Statistika(mjesta);
        statistika.ispis();
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void promijeniVrijednostS(Senzor senzor) {
        int tip = senzor.getTip();
        if (tip == 0) {
            double min = senzor.getMinVrijednost();
            double max = senzor.getMaxVrijednost();

            if (senzor.getVrijednost() >= min && senzor.getVrijednost() < max) {
                senzor.setVrijednost(min + 1);
            } else {
                senzor.setVrijednost(min);
            }
        } else if (tip == 1) {

            double min = senzor.getMinVrijednost();
            double max = senzor.getMaxVrijednost();

            if (senzor.getVrijednost() >= min && senzor.getVrijednost() < max) {
                senzor.setVrijednost(min + 1.2);
            } else {
                senzor.setVrijednost(min);
            }

        } else if (tip == 2) {
            double min = senzor.getMinVrijednost();
            double max = senzor.getMaxVrijednost();

            if (senzor.getVrijednost() > min && senzor.getVrijednost() < max) {
                senzor.setVrijednost(min + 1.12345);
            } else {
                senzor.setVrijednost(min);
            }
        } else {
            if (senzor.getVrijednost() == 1) {
                senzor.setVrijednost(0);
            } else {
                senzor.setVrijednost(1);
            }
        }
    }
    
    private void promijeniVrijednostA(Aktuator aktuator) {
        int tip = aktuator.getTip();
        if (tip == 0) {
            double min = aktuator.getMinVrijednost();
            double max = aktuator.getMaxVrijednost();

            if (aktuator.getVrijednost() >= min && aktuator.getVrijednost() < max) {
                aktuator.setVrijednost(min + 1);
            } else {
                aktuator.setVrijednost(min);
            }
        } else if (tip == 1) {

            double min = aktuator.getMinVrijednost();
            double max = aktuator.getMaxVrijednost();

            if (aktuator.getVrijednost() >= min && aktuator.getVrijednost() < max) {
                aktuator.setVrijednost(min + 1.2);
            } else {
                aktuator.setVrijednost(min);
            }

        } else if (tip == 2) {
            double min = aktuator.getMinVrijednost();
            double max = aktuator.getMaxVrijednost();

            if (aktuator.getVrijednost() > min && aktuator.getVrijednost() < max) {
                aktuator.setVrijednost(min + 1.12345);
            } else {
                aktuator.setVrijednost(min);
            }
        } else {
            if (aktuator.getVrijednost() == 1) {
                aktuator.setVrijednost(0);
            } else {
                aktuator.setVrijednost(1);
            }
        }
    }
}
