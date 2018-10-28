/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_3;

import ChainOfRes.Profesor;
import ChainOfRes.Student;
import ChainOfRes.Tehnicar;
import Datoteke.Datoteka;
import Datoteke.DatotekaFactory;
import Datoteke.IzlazDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
import Iterator.Iterator;
import Iterator.IteratorMjesta;
import MVC.Controller.GlavniController;
import MVC.Model.Model;
import MVC.View.GlavniView;
import Memento.Caretaker;
import Memento.Originator;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import Utils.Greske;
import Utils.Konstante;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SINGLETON
 *
 * @author helena
 */
public class Aplikacija {

    private static Aplikacija instance = null;

    public String datotekaMjesta, datotekaSenzori, datotekaAktuatori, datotekaRasporeda, izlaznaDatoteka;
    public int broj_redaka, broj_stupaca, broj_komandi, ispravnost_uredjaja, sjeme, trajanjeCiklusa;

    private Statistika statistika;
    public Generator generator = Generator.getInstance();
    private Caretaker caretaker;
    public IzlazDatoteka datoteka = IzlazDatoteka.getInstance();
    public DatotekaFactory datotekaFactory;
    public IteratorMjesta iterator;
    public IteratorMjesta pomocniIterator;
    private final List<String> datoteka_zapisi = new ArrayList<>();
    public int i = 0;

    private List<Aktuator> aktuatori = new ArrayList<>();
    private List<Senzor> senzori = new ArrayList<>();
    private List<Mjesto> mjesta = new ArrayList<>();
    private List<Object> senzorii = new ArrayList<>();
    private List<Object> aktuatorii = new ArrayList<>();
    private List<Object> raspored = new ArrayList<>();
    private static List<Mjesto> pomocnaMjestaa = new ArrayList<>();
    private List<Senzor> neispravniSenzori;
    private List<Aktuator> neispravniAktuatori;
    private static List<Senzor> senzoriVF = new ArrayList<>();
    private static int trajanje_ciklusa;

    private Aplikacija() {
    }

    public static Aplikacija instanciraj() {
        if (instance == null) {
            instance = new Aplikacija();
        }
        return instance;
    }

    public void konfiguriraj(int broj_redaka, int broj_stupaca, int broj_komandi, int ispravnost_uredjaja, int sjeme, String datotekaMjesta, String datotekaSenzori, String datotekaAktuatori, String datotekaRaspored, int trajanjeCiklusa, String izlaznaDatoteka) {
        this.broj_redaka = broj_redaka;
        this.broj_stupaca = broj_stupaca;
        this.broj_komandi = broj_komandi;
        this.ispravnost_uredjaja = ispravnost_uredjaja;
        this.sjeme = sjeme;
        this.datotekaMjesta = datotekaMjesta;
        this.datotekaSenzori = datotekaSenzori;
        this.datotekaAktuatori = datotekaAktuatori;
        this.datotekaRasporeda = datotekaRaspored;
        this.trajanjeCiklusa = trajanjeCiklusa;
        this.izlaznaDatoteka = izlaznaDatoteka;
        this.datoteka.setNaziv(izlaznaDatoteka);
        trajanje_ciklusa = trajanjeCiklusa;
    }

    private void dohvatiKonfiguraciju() {
        datoteka_zapisi.add("Broj redaka na ekranu: " + broj_redaka);
        datoteka_zapisi.add("Broj stupaca na ekranu: " + broj_stupaca);
        datoteka_zapisi.add("Broj komandi: " + broj_komandi);
        datoteka_zapisi.add("Prosjecna ispravnost: " + ispravnost_uredjaja);
        datoteka_zapisi.add("Sjeme za generator: " + sjeme);
        datoteka_zapisi.add("Datoteka mjesta: " + datotekaMjesta);
        datoteka_zapisi.add("Datoteka senzora: " + datotekaSenzori);
        datoteka_zapisi.add("Datoteka aktuatora:" + datotekaAktuatori);
        datoteka_zapisi.add("Datoteka rasporeda: " + datotekaRasporeda);
        datoteka_zapisi.add("Trajanje ciklusa: " + trajanjeCiklusa);
        datoteka_zapisi.add("Izlazna datoteka: " + izlaznaDatoteka);
    }

    private void procitajDatoteke() {

        dohvatiKonfiguraciju();
        datotekaFactory = new DatotekaFactory();

        Datoteka datoteka = datotekaFactory.otvoriDatoteku(datotekaMjesta, "Mjesta");
        for (Object o : datoteka.vratiProcitano()) {
            mjesta.add((Mjesto) o);
        }
        //mozda ne radi
        datoteka_zapisi.addAll(datoteka.dohvatiZapise());

        datoteka = datotekaFactory.otvoriDatoteku(datotekaSenzori, "Senzori");
        senzorii = datoteka.vratiProcitano();
        datoteka_zapisi.addAll(datoteka.dohvatiZapise());
        for (Object o : senzorii) {
            senzori.add((Senzor) o);
        }

        datoteka = datotekaFactory.otvoriDatoteku(datotekaAktuatori, "Aktuatori");
        aktuatorii = datoteka.vratiProcitano();
        datoteka_zapisi.addAll(datoteka.dohvatiZapise());
        for (Object o : aktuatorii) {
            aktuatori.add((Aktuator) o);
        }
        datoteka = datotekaFactory.otvoriDatoteku(datotekaRasporeda, "Raspored");
        raspored = datoteka.vratiProcitano();

        datoteka_zapisi.addAll(datoteka.dohvatiZapise());

        File f = new File(izlaznaDatoteka);
        if (f.exists()) {
            f.delete();
        }
    }

    private void postaviSustav() {

        //u ovo spremaju uređaji koji po svom tipu (1-vanjski,2-unutranji i vanjski) odgovaraju tipu mjesta
        List<Aktuator> pomocniAktuatori = new ArrayList<>();
        List<Senzor> pomocniSenzori = new ArrayList<>();
        datoteka_zapisi.add("KRECEM U POSTAVLJANJE UREDJAJA...");

        for (Object zapis : raspored) {
            String podatak = zapis.toString();
            String[] atributi = podatak.split(";");

            //dodavanje senzora i aktuatora mjestima s provjerama tipa i broja
            if (atributi[0].equals("0")) {

                int id = Integer.parseInt(atributi[3]);
                int slucajni = Integer.parseInt(atributi[4]);
                int mjestoId = Integer.parseInt(atributi[1]);
                int broj_senzora;
                int broj_aktuatora;
                Mjesto trenutnoMjesto = null;
                for (Mjesto mjesto : mjesta) {
                    if (mjesto.getId() == mjestoId) {
                        trenutnoMjesto = mjesto;
                    }
                }

                if (atributi[2].equals("0") && trenutnoMjesto != null) {
                    for (Senzor senzor : senzori) {
                        if (id == senzor.getId()) {
                            if (senzor.getTip() == trenutnoMjesto.getTip() || senzor.getTip() == 2) {
                                Senzor s = senzor.clone();
                                s.setSlucajniBroj(slucajni);
                                trenutnoMjesto.dodajSenzor(s);
                            } else {
                                datoteka_zapisi.add(Greske.vratiGreskuMjesta(6));
                            }
                        }
                    }
                } else if (atributi[2].equals("1") && trenutnoMjesto != null) {
                    for (Aktuator aktuator : aktuatori) {
                        if (id == aktuator.getId()) {
                            if (aktuator.getTip() == trenutnoMjesto.getTip() || aktuator.getTip() == 2) {
                                Aktuator a = aktuator.clone();
                                a.setSlucajniBroj(slucajni);
                                trenutnoMjesto.dodajAktuator(a);
                            } else {
                                datoteka_zapisi.add(Greske.vratiGreskuMjesta(6));
                            }
                        }
                    }
                }
            } else {
                int aktuatorId = Integer.parseInt(atributi[1]);
                String[] senzoriID = atributi[2].split(",");

                for (Mjesto mjesto : mjesta) {
                    List<Aktuator> aktuatoriMjesta = mjesto.getAktuatoriMjesta();
                    for (Aktuator aktuator : aktuatoriMjesta) {
                        if (aktuator.getSlucajniBroj() == aktuatorId) {
                            for (String senzorId : senzoriID) {
                                Senzor neki = mjesto.dohvatiSenzorSlucajni(Integer.parseInt(senzorId));
                                aktuator.dodajSenzor(neki);
                            }
                        }
                    }
                }
            }
        }
    }

    private void inicijalizirajSustav() {
        datoteka_zapisi.add("KRECEM U INICIJALIZACIJU SUSTAVA...");
        for (Mjesto mjesto : this.mjesta) {
            List<Senzor> senzori = mjesto.getSenzoriMjesta();
            List<Aktuator> aktuatori = mjesto.getAktuatoriMjesta();
            neispravniAktuatori = new ArrayList<>();
            neispravniSenzori = new ArrayList<>();

            for (Senzor senzor : senzori) {
                senzor.postaviVrijednost();
                int broj = generator.dajSlucajniBroj(1, 100);
                if (broj > ispravnost_uredjaja) {
                    datoteka_zapisi.add("Brisem neispravan senzor:" + senzor.getNaziv() + "s mjesta" + mjesto.getNaziv());
                    int razina = generator.dajSlucajniBroj(0, 2);
                    senzor.setRazina_greske(razina);
                    neispravniSenzori.add(senzor);
                    senzoriVF.add(senzor);
                }
            }

            for (Aktuator aktuator : aktuatori) {
                aktuator.postaviVrijednost();
                int broj = generator.dajSlucajniBroj(1, 100);
                if (broj > ispravnost_uredjaja) {
                    datoteka_zapisi.add("Brisem neispravan aktuator:" + aktuator.getNaziv() + "s mjesta" + mjesto.getNaziv());
                    neispravniAktuatori.add(aktuator);
                }
            }

            for (Senzor senzor : neispravniSenzori) {
                mjesto.brisiSenzor(senzor);
            }

            for (Aktuator aktuator : neispravniAktuatori) {
                mjesto.brisiAktuator(aktuator);
            }
        }
        pomocnaMjestaa = this.mjesta;
    }

    private void prikazi() {
        GlavniView prikaz = new GlavniView(this.broj_redaka, this.broj_stupaca, this.broj_komandi);
        Model podaci = new Model();
        Model zapisi = new Model();
        zapisi.setPodaci(datoteka_zapisi);
        GlavniController kontroler = new GlavniController(prikaz, podaci, zapisi);
        kontroler.procesirajPocetak();
        kontroler.procesirajOdabir();
    }

    public void pokreniZadatak() {
        procitajDatoteke();
        postaviSustav();
        inicijalizirajSustav();
        prikazi();

    }

    public static List<Mjesto> dohvatiMjesta() {
        return pomocnaMjestaa;
    }

    public static int dohvatiTrajanje() {
        return trajanje_ciklusa;
    }

    public static List<String> pokreniVF() {
        List<String> zapisi = new ArrayList<>();
        Student student = new Student("Nikola", "Nikolic");
        Profesor profesor = new Profesor("Nenad", "Naglas");
        Tehnicar tehnicar = new Tehnicar("Danko", "Grgic");
        student.postaviSljedeceg(profesor);
        profesor.postaviSljedeceg(tehnicar);
        for (Senzor senzor : senzoriVF) {
            if(senzor.isPopravljen() == false)
            student.popraviUređaj(senzor);
        }
        zapisi.addAll(student.ispisiPoruku());
        zapisi.addAll(profesor.ispisiPoruku());
        zapisi.addAll(tehnicar.ispisiPoruku());
        return zapisi;
    }
}
