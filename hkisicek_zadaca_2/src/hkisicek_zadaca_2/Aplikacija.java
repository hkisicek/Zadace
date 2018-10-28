/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_2;

import Datoteke.Datoteka;
import Datoteke.DatotekaFactory;
import Datoteke.IzlazDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
import Iterator.Iterator;
import Iterator.IteratorMjesta;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * SINGLETON
 *
 * @author helena
 */
public class Aplikacija {

    private static Aplikacija instance = null;

    public String datotekaMjesta, datotekaSenzori, datotekaAktuatori, algoritam, izlaznaDatoteka;
    public int sjeme, trajanjeCiklusa, brojCiklusa;

    public Generator generator = Generator.getInstance();
    public IzlazDatoteka datoteka = IzlazDatoteka.getInstance();
    public DatotekaFactory datotekaFactory;
    public IteratorMjesta iterator;
    public IteratorMjesta pomocniIterator;
    public int i = 0;

    List<Aktuator> aktuatori = new ArrayList<>();
    List<Senzor> senzori = new ArrayList<>();
    Mjesto[] mjesta;

    private Aplikacija() {
    }

    public static Aplikacija instanciraj() {
        if (instance == null) {
            instance = new Aplikacija();
        }
        return instance;
    }

    public void konfiguriraj(int sjeme, String datotekaMjesta, String datotekaSenzori, String datotekaAktuatori, String algoritam, int trajanjeCiklusa, int brojCiklusa, String izlaznaDatoteka, int brojLinija) {
        this.sjeme = sjeme;
        this.datotekaMjesta = datotekaMjesta;
        this.datotekaSenzori = datotekaSenzori;
        this.datotekaAktuatori = datotekaAktuatori;
        this.algoritam = algoritam;
        this.trajanjeCiklusa = trajanjeCiklusa;
        this.brojCiklusa = brojCiklusa;
        this.izlaznaDatoteka = izlaznaDatoteka;
        this.datoteka.setBrojLinija(brojLinija);
        this.datoteka.setNaziv(izlaznaDatoteka);

    }

    public void procitajDatoteke() {

        datotekaFactory = new DatotekaFactory();

        Datoteka datoteka = datotekaFactory.otvoriDatoteku(datotekaMjesta, "Mjesta");

        mjesta = datoteka.vratiMjesta();
        for (int j = 0; j < mjesta.length; j++) {
            mjesta[j].setJednoznacniBroj(generator.dajSlucajniBroj(1, 1000));
        }

        datoteka = datotekaFactory.otvoriDatoteku(datotekaSenzori, "Senzori");
        for (Object o : datoteka.vratiProcitano()) {
            senzori.add((Senzor) o);
        }

        datoteka = datotekaFactory.otvoriDatoteku(datotekaAktuatori, "Aktuatori");
        for (Object o : datoteka.vratiProcitano()) {
            aktuatori.add((Aktuator) o);
        }

        File f = new File(izlaznaDatoteka);
        if (f.exists()) {
            f.delete();
        }
    }

    public void postaviSustav() {

        //u ovo spremaju uređaji koji po svom tipu (1-vanjski,2-unutranji i vanjski) odgovaraju tipu mjesta
        List<Aktuator> pomocniAktuatori = new ArrayList<>();
        List<Senzor> pomocniSenzori = new ArrayList<>();

        System.out.println("\n---------------------------KRECEM U POSTAVLJANJE UREDJAJA...-----------------------");
        datoteka.spremiuSpremnik("\n---------------------------KRECEM U POSTAVLJANJE UREDJAJA...-----------------------");

        //prolaz kroz sva mjesta
        for (int i = 0; i < mjesta.length; i++) {
            int tip = mjesta[i].getTip();
            int brojSenzora = mjesta[i].brojSenzora;
            int brojAktuatora = mjesta[i].brojAktuatora;

            //prolaz kroz sve aktuatore
            for (Aktuator aktuator : aktuatori) {
                //provjera tipova i spremanje u listu
                if (aktuator.getTip() == tip || aktuator.getTip() == 2) {
                    pomocniAktuatori.add(aktuator);
                }
            }
            //prolaz kroz sve senzore
            for (Senzor senzor : senzori) {
                //provjera tipova i spremanje u listu
                if (senzor.getTip() == tip || senzor.getTip() == 2) {
                    pomocniSenzori.add(senzor);
                }
            }

            for (int j = 0; j < brojSenzora; j++) {
                //ako postoji vise senzora koji odgovaraju tipu onda random uzima
                if (pomocniSenzori.size() > 1) {
                    Senzor senzor = pomocniSenzori.get(generator.dajSlucajniBroj(0, pomocniSenzori.size() - 1));
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Senzor s = senzor.clone();
                        s.setSlucajniBroj(broj);
                        mjesta[i].dodajSenzor(s);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesta[i].naziv + " dodajem senzor " + senzor.getNaziv());
                    datoteka.spremiuSpremnik("\nNa mjesto: " + mjesta[i].naziv + " dodajem senzor " + senzor.getNaziv());
                } else if (pomocniSenzori.size() == 1) {
                    Senzor senzor = pomocniSenzori.get(0);
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Senzor s = senzor.clone();
                        s.setSlucajniBroj(broj);
                        mjesta[i].dodajSenzor(s);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesta[i].naziv + " dodajem senzor " + senzor.getNaziv());
                    datoteka.spremiuSpremnik("\nNa mjesto: " + mjesta[i].naziv + " dodajem senzor " + senzor.getNaziv());
                }
            }

            //brojAktuatora pokazuje koliko aktuatora treba dodijeliti
            for (int k = 0; k < brojAktuatora; k++) {
                //analogno kak je kod senzora
                if (pomocniAktuatori.size() > 1) {
                    Aktuator aktuator = pomocniAktuatori.get(generator.dajSlucajniBroj(0, pomocniAktuatori.size() - 1));
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Aktuator a = aktuator.clone();
                        a.setSlucajniBroj(broj);
                        mjesta[i].dodajAktuator(a);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesta[i].naziv + " dodajem aktuator " + aktuator.getNaziv());
                    datoteka.spremiuSpremnik("\nNa mjesto: " + mjesta[i].naziv + " dodajem aktuator " + aktuator.getNaziv());
                } else if (pomocniAktuatori.size() == 1) {
                    Aktuator aktuator = pomocniAktuatori.get(0);
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Aktuator a = aktuator.clone();
                        a.setSlucajniBroj(broj);
                        mjesta[i].dodajAktuator(a);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesta[i].naziv + " dodajem aktuator " + aktuator.getNaziv());
                    datoteka.spremiuSpremnik("\nNa mjesto: " + mjesta[i].naziv + " dodajem aktuator " + aktuator.getNaziv());
                }
            }
            System.out.println("\n---------------------------------------------------------------------------------");

        }
    }

    public void inicijalizirajSustav() {

        Mjesto trenutnoMjesto;
        List<Senzor> popisSenzora, neispravniSenzori;
        List<Aktuator> popisAktuatora, neispravniAktuatori;
        int indeks = 0;
        int pomocniIndeks = 0;

        System.out.println("\n---------------------KRECEM U INICIJALIZACIJU SUSTAVA.....-----------------------");
        datoteka.spremiuSpremnik("\n---------------------KRECEM U INICIJALIZACIJU SUSTAVA.....-----------------------");
        Mjesto[] sortiranaMjesta = sortirajMjesta(mjesta);
        iterator = new IteratorMjesta(sortiranaMjesta);

        //provjera statusa uredjaja
        while (iterator.postojiSljedeci(indeks)) {
            trenutnoMjesto = iterator.trenutni(indeks);
            popisAktuatora = trenutnoMjesto.dohvatiAktuatore();
            popisSenzora = trenutnoMjesto.dohvatiSenzore();
            neispravniSenzori = new ArrayList<>();
            neispravniAktuatori = new ArrayList<>();

            for (Senzor senzor : popisSenzora) {
                if (senzor.getSlucajniBroj() >= 900 && senzor.getSlucajniBroj() <= 1000) {
                    neispravniSenzori.add(senzor);
                }
            }

            for (Senzor senzor : neispravniSenzori) {
                trenutnoMjesto.brisiSenzor(senzor);
                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("S mjesta" + trenutnoMjesto.getNaziv() + " uklanjam neispravan uređaj " + senzor.getNaziv());
                System.out.println("----------------------------------------------------------------------------------------");
                datoteka.spremiuSpremnik("\nS mjesta" + trenutnoMjesto.getNaziv() + " uklanjam neispravan uređaj " + senzor.getNaziv());
            }

            //provjeri ispravnost aktuatora i ukloni ga/dodaj senzore
            for (Aktuator aktuator : popisAktuatora) {
                if (aktuator.getSlucajniBroj() >= 900 && aktuator.getSlucajniBroj() <= 1000) {
                    neispravniAktuatori.add(aktuator);
                }
            }

            for (Aktuator aktuator : neispravniAktuatori) {
                trenutnoMjesto.brisiAktuator(aktuator);
                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("S mjesta " + trenutnoMjesto.getNaziv() + " uklanjam neispravan uređaj" + aktuator.getNaziv());
                System.out.println("----------------------------------------------------------------------------------------");
                datoteka.spremiuSpremnik("\nS mjesta " + trenutnoMjesto.getNaziv() + " uklanjam neispravan uređaj" + aktuator.getNaziv());
            }

            for (Aktuator aktuator : popisAktuatora) {
                int slucajniBroj = generator.dajSlucajniBroj(1, popisSenzora.size());
                for (int j = 0; j < slucajniBroj; j++) {
                    aktuator.dodajSenzor(popisSenzora.get(generator.dajSlucajniBroj(0, popisSenzora.size() - 1)));
                    System.out.println("Aktuatoru " + aktuator.getNaziv() + "na mjestu " + trenutnoMjesto.getNaziv() + " dodajem senzor");
                    datoteka.spremiuSpremnik("\nAktuatoru " + aktuator.getNaziv() + "na mjestu " + trenutnoMjesto.getNaziv() + " dodajem senzor");
                }
            }
            indeks++;
        }

        System.out.println("\n--------------------------ZAVRSAVAM FAZU OPREMANJA-------------------------------");
        datoteka.spremiuSpremnik("\n--------------------------ZAVRSAVAM FAZU OPREMANJA-------------------------------");
        while (iterator.postojiSljedeci(pomocniIndeks)) {
            Mjesto dohvacenoMjesto = iterator.trenutni(pomocniIndeks);
            List<Aktuator> listaAktuatora = dohvacenoMjesto.dohvatiAktuatore();
            for (Aktuator aktuator : listaAktuatora) {
                List<Senzor> listaSenzora = aktuator.getSenzori();
                for (Senzor senzor : listaSenzora) {
                    System.out.println("Aktuatoru " + aktuator.getNaziv() + " dodijeljen je senzor " + senzor.getNaziv());
                    datoteka.spremiuSpremnik("\nAktuatoru " + aktuator.getNaziv() + " dodijeljen je senzor " + senzor.getNaziv());
                }
            }
            pomocniIndeks++;

        }
    }

    public Mjesto[] sortirajMjesta(Mjesto[] mjesta) {

        int brojac = 0;
        Mjesto[] sortiranaMjesta = mjesta;
        int duljina = sortiranaMjesta.length - 1;
        pomocniIterator = new IteratorMjesta(sortiranaMjesta);
        boolean zamijenjeno = true;

        for (int j = duljina; zamijenjeno && j > 0; j--) {
            zamijenjeno = false;
            for (int k = 0; k < j; k++) {
                if (sortiranaMjesta[k].getJednoznacniBroj() > sortiranaMjesta[k + 1].getJednoznacniBroj()) {
                    Mjesto zamjena = sortiranaMjesta[k];
                    sortiranaMjesta[k] = sortiranaMjesta[k + 1];
                    sortiranaMjesta[k + 1] = zamjena;
                    zamijenjeno = true;
                }
            }
        }
        return sortiranaMjesta;
    }

    public void provjeriMjesta() {
        GlavnaDretva dretva = new GlavnaDretva(1, 3, mjesta, this.algoritam);
        dretva.start();
    }
}
