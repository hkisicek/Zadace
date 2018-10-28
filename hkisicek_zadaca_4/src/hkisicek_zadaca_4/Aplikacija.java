/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_4;

import Datoteke.Datoteka;
import Datoteke.DatotekaFactory;
import Datoteke.IzlazDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
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
    public DatotekaFactory datotekaFactory;
    private final List<String> datoteka_zapisi = new ArrayList<>();
    public int i = 0;

    private List<Aktuator> aktuatori = new ArrayList<>();
    private List<Senzor> senzori = new ArrayList<>();
    private List<Mjesto> mjesta = new ArrayList<>();
    private List<Object> senzorii = new ArrayList<>();
    private List<Object> aktuatorii = new ArrayList<>();
    private List<Object> raspored = new ArrayList<>();
    private static List<Mjesto> pomocnaMjestaa = new ArrayList<>();
    private List<Senzor> dodaniSenzori;
    private List<Aktuator> dodaniAktuatori;
    private static int trajanje_ciklusa;
    private int kmax, kmin, kpov;

    private Aplikacija() {
    }

    public static Aplikacija instanciraj() {
        if (instance == null) {
            instance = new Aplikacija();
        }
        return instance;
    }

    public void konfiguriraj(int broj_redaka, int broj_stupaca, int broj_komandi, int ispravnost_uredjaja, int sjeme, String datotekaMjesta, String datotekaSenzori, String datotekaAktuatori, String datotekaRaspored, int trajanjeCiklusa, int kmax, int kmin, int kpov) {
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
        trajanje_ciklusa = trajanjeCiklusa;
        this.kmax = kmax;
        this.kmin = kmin;
        this.kpov = kpov;
    }

    private void dohvatiKonfiguraciju() {
        datoteka_zapisi.add("Broj redaka na ekranu:  " + broj_redaka);
        datoteka_zapisi.add("Broj stupaca na ekranu:  " + broj_stupaca);
        datoteka_zapisi.add("Broj komandi:  " + broj_komandi);
        datoteka_zapisi.add("Prosjecna ispravnost:  " + ispravnost_uredjaja);
        datoteka_zapisi.add("Sjeme za generator:  " + sjeme);
        datoteka_zapisi.add("Datoteka mjesta:  " + datotekaMjesta);
        datoteka_zapisi.add("Datoteka senzora:  " + datotekaSenzori);
        datoteka_zapisi.add("Datoteka aktuatora:  " + datotekaAktuatori);
        datoteka_zapisi.add("Datoteka rasporeda:  " + datotekaRasporeda);
        datoteka_zapisi.add("Trajanje ciklusa:  " + trajanjeCiklusa);
        datoteka_zapisi.add("KMAX:  " + kmax);
        datoteka_zapisi.add("KMIN:  " + kmin);
        datoteka_zapisi.add("KPOV:  " + kpov);
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
            Senzor senzor = (Senzor) o;
            //minimalni broj uredjaja
            senzor.setBrojUredjaja(kmin);
            senzor.setMaksimalanBroj(kmax);
            senzor.setMinimalanBroj(kmin);
            senzori.add(senzor);
        }

        datoteka = datotekaFactory.otvoriDatoteku(datotekaAktuatori, "Aktuatori");
        aktuatorii = datoteka.vratiProcitano();
        datoteka_zapisi.addAll(datoteka.dohvatiZapise());
        for (Object o : aktuatorii) {
            Aktuator aktuator = (Aktuator) o;
            //minimalni broj uredjaja
            aktuator.setBrojUredjaja(kmin);
            aktuator.setMaksimalanBroj(kmax);
            aktuator.setMinimalanBroj(kmin);
            aktuatori.add(aktuator);
        }
        datoteka = datotekaFactory.otvoriDatoteku(datotekaRasporeda, "Raspored");
        raspored = datoteka.vratiProcitano();

        datoteka_zapisi.addAll(datoteka.dohvatiZapise());
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
                int broj_aktuatora = 0;
                int broj_senzora = 0;

                Mjesto trenutnoMjesto = null;
                for (Mjesto mjesto : mjesta) {
                    if (mjesto.getId() == mjestoId) {
                        trenutnoMjesto = mjesto;
                        broj_aktuatora = trenutnoMjesto.getBrojAktuatora();
                        broj_senzora = trenutnoMjesto.getBrojSenzora();
                    }
                }

                if (atributi[2].equals("0") && trenutnoMjesto != null) {
                    for (Senzor senzor : senzori) {
                        if (id == senzor.getId()) {
                            if ((senzor.getTip() == trenutnoMjesto.getTip() || senzor.getTip() == 2) && broj_senzora > 0) {
                                if (senzor.getBrojUredjaja() > 0) {
                                    Senzor s = senzor.clone();
                                    s.setSlucajniBroj(slucajni);
                                    trenutnoMjesto.dodajSenzor(s);
                                    senzor.dodajSenzorUKolekciju(s);
                                    senzor.setBrojUredjaja(senzor.getBrojUredjaja() - 1);
                                    broj_senzora--;
                                } else {
                                    datoteka_zapisi.add(Greske.vratiGreskuSenzor(7));
                                    datoteka_zapisi.add("Nabavljam senzore pod id-em:" + senzor.getId());
                                    nabaviSenzore(senzor);
                                    Senzor s = senzor.clone();
                                    s.setSlucajniBroj(slucajni);
                                    trenutnoMjesto.dodajSenzor(s);
                                    senzor.dodajSenzorUKolekciju(s);
                                    senzor.setBrojUredjaja(senzor.getBrojUredjaja() - 1);
                                    broj_senzora--;           
                                }
                            } else {
                                datoteka_zapisi.add(Greske.vratiGreskuMjesta(6));
                            }
                        }
                    }
                } else if (atributi[2].equals("1") && trenutnoMjesto != null) {
                    for (Aktuator aktuator : aktuatori) {
                        if (id == aktuator.getId()) {
                            if ((aktuator.getTip() == trenutnoMjesto.getTip() || aktuator.getTip() == 2) && broj_aktuatora > 0) {
                                if (aktuator.getBrojUredjaja() > 0) {
                                    Aktuator a = aktuator.clone();
                                    a.setSlucajniBroj(slucajni);
                                    trenutnoMjesto.dodajAktuator(a);
                                    aktuator.dodajAktuatorUKolekciju(a);
                                    aktuator.setBrojUredjaja(aktuator.getBrojUredjaja() - 1);
                                    broj_aktuatora--;
                                } else {
                                    datoteka_zapisi.add(Greske.vratiGreskuAktuator(7));
                                    datoteka_zapisi.add("Nabavljam aktuatore pod id-em: " + aktuator.getId());
                                    nabaviAktuatore(aktuator);
                                }
                            } else {
                                datoteka_zapisi.add(Greske.vratiGreskuMjesta(6));
                            }
                        }
                    }
                }
            } else if (atributi[0].equals("2")) {
                Mjesto strukturnoMjesto = vratiMjesto(mjesta, Integer.parseInt(atributi[1]));
                for (int j = 2; j < atributi.length; j++) {
                    if (strukturnoMjesto != null) {
                        Mjesto mjestoDodjela = vratiMjesto(mjesta, Integer.parseInt(atributi[j]));
                        if (mjestoDodjela != null && mjestoDodjela.isDodijeljeno() == false) {
                            strukturnoMjesto.dodajMjestoStruktura(mjestoDodjela);
                            mjestoDodjela.setDodijeljeno(true);
                        }
                    }
                }
            } else {
                int aktuatorId = Integer.parseInt(atributi[1]);
                String[] senzoriID = atributi[2].split(";");

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
            List<Senzor> senzoriM = mjesto.getSenzoriMjesta();
            List<Aktuator> aktuatoriM = mjesto.getAktuatoriMjesta();
            dodaniAktuatori = new ArrayList<>();
            dodaniSenzori = new ArrayList<>();
            
            for (Senzor senzor : senzoriM) {
                //    senzor.postaviVrijednost();
                int broj = generator.dajSlucajniBroj(1, 100);
                if (broj > ispravnost_uredjaja) {
                    datoteka_zapisi.add("Zamjena neispravanog senzora:" + senzor.getNaziv() + "s mjesta" + mjesto.getNaziv());
                    Senzor senzorK = vratiSenzor(senzori, senzor.getId());
                    mjesto.dodajNeispravniSenzor(senzor);
                    if (senzorK.getBrojUredjaja() > 0) {
                        dodaniSenzori.add(senzor.clone());
                        senzorK.setBrojUredjaja(senzorK.getBrojUredjaja() - 1);
                        senzorK.setBrojZamjena(senzorK.getBrojZamjena() + 1);
                    } else {
                        nabaviSenzore(senzorK);
                        dodaniSenzori.add(senzor.clone());
                        senzorK.setBrojUredjaja(senzorK.getBrojUredjaja() - 1);
                        senzorK.setBrojZamjena(senzorK.getBrojZamjena() + 1);
                    }
                }
            }
            
            for (Aktuator aktuator : aktuatoriM) {
                //  aktuator.postaviVrijednost();
                int broj = generator.dajSlucajniBroj(1, 100);
                if (broj > ispravnost_uredjaja) {
                    datoteka_zapisi.add("Zamjena neispravanog aktuatora:" + aktuator.getNaziv() + "s mjesta" + mjesto.getNaziv());
                    Aktuator aktuatorK = vratiAktuator(aktuatori, aktuator.getId());
                    mjesto.dodajNeispravniAktuator(aktuator);
                    if (aktuatorK.getBrojUredjaja() > 0) {
                        dodaniAktuatori.add(aktuator.clone());
                        aktuatorK.setBrojUredjaja(aktuatorK.getBrojUredjaja() - 1);
                        aktuatorK.setBrojZamjena(aktuatorK.getBrojZamjena() + 1);
                    } else {
                        nabaviAktuatore(aktuatorK);
                        dodaniAktuatori.add(aktuator.clone());
                        aktuatorK.setBrojUredjaja(aktuatorK.getBrojUredjaja() - 1);
                        aktuatorK.setBrojZamjena(aktuatorK.getBrojZamjena() + 1);
                    }
                }
            }
            for (Senzor senzor : dodaniSenzori) {
                mjesto.dodajSenzor(senzor);
            }
            for (Aktuator aktuator : dodaniAktuatori) {
                mjesto.dodajAktuator(aktuator);
            }
        }
        pomocnaMjestaa = this.mjesta;
    }

    private void nabaviSenzore(Senzor senzor) {
        senzor.setBrojNabave(senzor.getBrojNabave() + 1);
        for (int j = 0; j < kpov; j++) {
            Senzor noviSenzor = senzor.clone();
            senzor.dodajSenzorUKolekciju(noviSenzor);
            senzor.setBrojUredjaja(senzor.getBrojUredjaja() + 1);
        }
    }

    private void nabaviAktuatore(Aktuator aktuator) {
        aktuator.setBrojNabave(aktuator.getBrojNabave() + 1);
        for (int j = 0; j < kpov; j++) {
            Aktuator noviAktuator = aktuator.clone();
            aktuator.dodajAktuatorUKolekciju(noviAktuator);
            aktuator.setBrojUredjaja(aktuator.getBrojUredjaja() + 1);
        }
    }

    private void prikazi() {
        GlavniView prikaz = new GlavniView(this.broj_redaka, this.broj_stupaca, this.broj_komandi);
        Model podaci = new Model();
        Model zapisi = new Model();
        zapisi.setPodaci(datoteka_zapisi);
        GlavniController kontroler = new GlavniController(prikaz, podaci, zapisi, senzori, aktuatori);
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
    
    public Mjesto vratiMjesto(List<Mjesto> mjesta, int id){
        Mjesto mjestoo = null;
        for(Mjesto mjesto : mjesta){
            if(mjesto.getId() == id){
                mjestoo = mjesto; 
            }
        }
        return mjestoo;
    }
    
    public Senzor vratiSenzor(List<Senzor> senzori, int id){
        Senzor senzorr = null;
        for (Senzor senzor : senzori) {
            if (senzor.getId() == id) {
                senzorr = senzor;
            }
        }
        return senzorr;
    }
    
    public Aktuator vratiAktuator(List<Aktuator> aktuatori, int id) {
        Aktuator aktuatorr = null;
        for (Aktuator aktuator : aktuatori) {
            if (aktuator.getId() == id) {
                aktuatorr = aktuator;
            }
        }
        return aktuatorr;
    }

    public static int dohvatiTrajanje() {
        return trajanje_ciklusa;
    }
}
