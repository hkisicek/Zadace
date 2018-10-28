/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_1;

import Datoteke.Datoteka;
import Datoteke.DatotekaFactory;
import Datoteke.IzlazDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
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

    private String datotekaMjesta, datotekaSenzori, datotekaAktuatori, algoritam, izlaznaDatoteka;
    private int sjeme, trajanjeCiklusa, brojCiklusa;

    private Generator generator = Generator.getInstance();
    private IzlazDatoteka datotekaIzlaz = IzlazDatoteka.getInstance();
    private DatotekaFactory datotekaFactory;
    private GlavnaDretva dretva;
    private Statistika statistika;
    private int i = 0;

    List<Aktuator> aktuatori = new ArrayList<>();
    List<Senzor> senzori = new ArrayList<>();
    List<Mjesto> mjesta;

    private Aplikacija() {}

    public static Aplikacija instanciraj() {
        if (instance == null) {
            instance = new Aplikacija();
        }
        return instance;
    }

    public void konfiguriraj(int sjeme, String datotekaMjesta, String datotekaSenzori, String datotekaAktuatori, String algoritam, int trajanjeCiklusa, int brojCiklusa, String izlaznaDatoteka) {
        this.sjeme = sjeme;
        generator.setSjeme(sjeme);
        this.datotekaMjesta = datotekaMjesta;
        this.datotekaSenzori = datotekaSenzori;
        this.datotekaAktuatori = datotekaAktuatori;
        this.algoritam = algoritam;
        this.trajanjeCiklusa = trajanjeCiklusa;
        this.brojCiklusa = brojCiklusa;
        this.izlaznaDatoteka = izlaznaDatoteka;
        this.datotekaIzlaz.setNaziv(izlaznaDatoteka);
    }

    public void procitajDatoteke() {

        datotekaFactory = new DatotekaFactory();

        Datoteka datoteka = datotekaFactory.otvoriDatoteku(datotekaMjesta, "Mjesta");

        mjesta = datoteka.vratiMjesta();
        
        for(Mjesto mjesto : mjesta){
            mjesto.setJednoznacniBroj(generator.dajSlucajniBroj(1, 1000));
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
        
        datotekaIzlaz.otvoriDatoteku();
    }

    public void postaviSustav() {

        //u ovo spremaju uređaji koji po svom tipu (1-vanjski,2-unutranji i vanjski) odgovaraju tipu mjesta
        List<Aktuator> pomocniAktuatori = new ArrayList<>();
        List<Senzor> pomocniSenzori = new ArrayList<>();

        System.out.println("\n---------------------------KRECEM U POSTAVLJANJE UREDJAJA...-----------------------");
        datotekaIzlaz.spremiuSpremnik("\n---------------------------KRECEM U POSTAVLJANJE UREDJAJA...-----------------------");

        //prolaz kroz sva mjesta
        for (Mjesto mjesto : mjesta) {
            int tip = mjesto.getTip();
            int brojSenzora = mjesto.brojSenzora;
            int brojAktuatora = mjesto.brojAktuatora;

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

            for (int k = 0; k < brojSenzora; k++ ) {
                //ako postoji vise senzora koji odgovaraju tipu onda random uzima
                if (pomocniSenzori.size() > 1) {
                    Senzor senzor = pomocniSenzori.get(generator.dajSlucajniBroj(0, pomocniSenzori.size() - 1));
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Senzor s = senzor.clone();
                        s.setSlucajniBroj(broj);
                        mjesto.dodajSenzor(s);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesto.naziv + " dodajem senzor " + senzor.getNaziv());
                    datotekaIzlaz.spremiuSpremnik("\nNa mjesto: " + mjesto.naziv + " dodajem senzor " + senzor.getNaziv());
                } else if (pomocniSenzori.size() == 1) {
                    Senzor senzor = pomocniSenzori.get(0);
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Senzor s = senzor.clone();
                        s.setSlucajniBroj(broj);
                        mjesto.dodajSenzor(s);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesto.naziv + " dodajem senzor " + senzor.getNaziv());
                    datotekaIzlaz.spremiuSpremnik("\nNa mjesto: " + mjesto.naziv + " dodajem senzor " + senzor.getNaziv());
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
                        mjesto.dodajAktuator(a);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesto.naziv + " dodajem aktuator " + aktuator.getNaziv());
                    datotekaIzlaz.spremiuSpremnik("\nNa mjesto: " + mjesto.naziv + " dodajem aktuator " + aktuator.getNaziv());
                } else if (pomocniAktuatori.size() == 1) {
                    Aktuator aktuator = pomocniAktuatori.get(0);
                    try {
                        int broj = generator.dajSlucajniBroj(1, 1000);
                        Aktuator a = aktuator.clone();
                        a.setSlucajniBroj(broj);
                        mjesto.dodajAktuator(a);
                    } catch (Exception e) {
                        System.out.println("Doslo je do pogreske!");
                    }
                    System.out.println("Na mjesto: " + mjesto.naziv + " dodajem aktuator " + aktuator.getNaziv());
                    datotekaIzlaz.spremiuSpremnik("\nNa mjesto: " + mjesto.naziv + " dodajem aktuator " + aktuator.getNaziv());
                }
            }
            System.out.println("\n---------------------------------------------------------------------------------");

        }
    }

    public void inicijalizirajSustav() {
        
        System.out.println("---------------------------KRECEM U INICIJALIZACIJU SUSTAVA----------------------------");
        for(Mjesto mjesto : mjesta){
            System.out.println("Provjera za mjesto: " + mjesto.getNaziv());
            List<Senzor> senzori = mjesto.getSenzoriMjesta();
            List<Aktuator> aktuatori = mjesto.getAktuatoriMjesta();
           
            List<Senzor> neispravniSenzori = new ArrayList<>();
            List<Aktuator> neispravniAktatori = new ArrayList<>();
            
            for(Senzor senzor : senzori){
                int broj = generator.dajSlucajniBroj(1, 100);
                if(broj > 90){
                    System.out.println("Senzor: " + senzor.getNaziv() + "je vratio status 0! Brisem ga iz upotrebe.");
                    senzor.setIspravnost(false);
                    neispravniSenzori.add(senzor);
                }
            }
            
            for(Aktuator aktuator : aktuatori){
                System.out.println("Aktuator: " + aktuator.getNaziv() + "je vratio status 0! Brisem ga iz upotrebe.");
                int broj = generator.dajSlucajniBroj(1, 90);
                if(broj > 90){
                    aktuator.setIspravnost(false);
                    neispravniAktatori.add(aktuator);
                }
            }
            
            //brisanje senzora
            for(Senzor senzor : neispravniSenzori){
                mjesto.brisiSenzor(senzor);
            }
            
            //brisanje aktuatora
            for(Aktuator aktuator : neispravniAktatori){
                mjesto.brisiAktuator(aktuator);
            }
        }
    }
    
    public void pokreniAplikaciju() {
        procitajDatoteke();
        postaviSustav();
        inicijalizirajSustav();
        dretva = new GlavnaDretva(trajanjeCiklusa, brojCiklusa, mjesta, algoritam);
        dretva.start();
        datotekaIzlaz.zapisi();
    }
}
