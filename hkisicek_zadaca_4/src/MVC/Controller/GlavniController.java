/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import Datoteke.AktuatorDatoteka;
import Datoteke.MjestaDatoteka;
import Datoteke.SenzorDatoteka;
import Dretve.GlavnaDretva;
import MVC.Model.Model;
import MVC.View.GlavniView;
import Memento.Caretaker;
import Memento.Memento;
import Memento.Originator;
import Uređaji.Aktuator;
import Uređaji.Senzor;
import Utils.Opcije;
import hkisicek_zadaca_4.Aplikacija;
import hkisicek_zadaca_4.Mjesto;
import hkisicek_zadaca_4.Statistika;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Helena
 */
public class GlavniController {

    private GlavniView view;
    private Model podaci;
    private Model zapisi;
    private Scanner komanda;

    private List<Senzor> senzori = new ArrayList<>();
    private List<Aktuator> aktuatori = new ArrayList<>();

    public GlavniController(GlavniView view, Model podaci, Model zapisi, List<Senzor> senzori, List<Aktuator> aktuatori) {
        this.view = view;
        this.podaci = podaci;
        this.zapisi = zapisi;
        this.senzori = senzori;
        this.aktuatori = aktuatori;
    }

    public void procesirajPocetak() {
        view.ispisiGornjiProzor();
        view.azurirajGornjiProzor(zapisi.getPodaci());
        view.ispisiInputProzor();
    }

    public void procesirajOdabir() {

        List<Mjesto> mjesta = Aplikacija.dohvatiMjesta();
        Originator originator = null;
        List<String> problem = new ArrayList<>();
        int trajanje_ciklusa = Aplikacija.dohvatiTrajanje();
        Caretaker caretaker = new Caretaker();
        originator = new Originator(mjesta, senzori, aktuatori, caretaker);
        originator.kreirajTockuSpremanja("tockaSpremanja");
        view.ispisiIzbornik();
        String odabir = "";
        int ispravnost = 0;

        do {
            komanda = new Scanner(System.in);
            odabir = komanda.nextLine();
            String[] atributi = odabir.trim().split(" ");
            odabir = atributi[0];
            switch (odabir) {
                //statistika mora biti incijalizirana
                case "M":
                    Memento m = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistika = new Statistika(m.getMjesta(), m.getSenzori(), m.getAktuatori());
                    try {
                        String id = atributi[1];
                        if (MjestaDatoteka.postojiMjesto(id)) {
                            view.obrisiPrviProzor();
                            view.azurirajGornjiProzorFormat(statistika.dohvatiInfoMj(Integer.parseInt(id)));
                        }
                    } catch (Exception e) {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "S":
                    Memento ms = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistikas = new Statistika(ms.getMjesta(), ms.getSenzori(), ms.getAktuatori());
                    if (atributi.length == 2) {
                        try {
                            String id = atributi[1];
                            view.obrisiPrviProzor();
                            view.obrisiDrugiProzor();
                            view.azurirajGornjiProzorFormat(statistikas.dohvatiInfoSe(Integer.parseInt(id)));
                        } catch (Exception e) {
                        }
                    } else if (atributi.length == 1) {
                        view.obrisiPrviProzor();
                        view.obrisiDrugiProzor();
                        view.azurirajGornjiProzor(statistikas.dohvatiStatistiku());
                    } else {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "A":
                    Memento msa = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistikasa = new Statistika(msa.getMjesta(), msa.getSenzori(), msa.getAktuatori());
                    try {
                        String id = atributi[1];
                        view.obrisiPrviProzor();
                        view.azurirajGornjiProzorFormat(statistikasa.dohvatiInfoAkt(Integer.parseInt(id)));
                    } catch (Exception e) {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "SM":
                    Memento msm = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistikasm = new Statistika(msm.getMjesta(), msm.getSenzori(), msm.getAktuatori());
                    try {
                        String id = atributi[1];
                        view.obrisiPrviProzor();
                        view.azurirajGornjiProzorFormat(statistikasm.ispisStruktureMjesta(Integer.parseInt(id)));
                    } catch (Exception e) {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "TS":
                    Memento mts = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistikats = new Statistika(mts.getMjesta(), mts.getSenzori(), mts.getAktuatori());
                    try {
                        String id = atributi[1];
                        view.obrisiPrviProzor();
                        view.azurirajGornjiProzorFormat(statistikats.dohvatiInfoKolekcijaSenz(Integer.parseInt(id)));
                    } catch (Exception e) {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "TA":
                    Memento mta = caretaker.dohvatiMemento("tockaSpremanja");
                    Statistika statistikata = new Statistika(mta.getMjesta(), mta.getSenzori(), mta.getAktuatori());
                    try {
                        String id = atributi[1];
                        view.obrisiPrviProzor();
                        view.azurirajGornjiProzorFormat(statistikata.dohvatiInfoKolekcijaAkt(Integer.parseInt(id)));
                    } catch (Exception e) {
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "SP":
                    originator = new Originator(mjesta, senzori, aktuatori, caretaker);
                    originator.kreirajTockuSpremanja("tockaSpremanja");
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "VP":
                    if (originator != null) {
                        originator.vrati();
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "C":
                    if (atributi.length == 2) {
                        int brojCiklusa = Integer.parseInt(atributi[1]);
                        if (ispravnost != 0) {
                            int broj_ciklusa = Integer.parseInt(atributi[1]);
                            GlavnaDretva dretva = new GlavnaDretva(trajanje_ciklusa, broj_ciklusa, mjesta, ispravnost);
                            dretva.start();
                            view.obrisiPrviProzor();
                            view.obrisiDrugiProzor();
                            view.azurirajGornjiProzor(dretva.dohvatiZapise());
                        } else {
                            view.obrisiPrviProzor();
                            view.obrisiDrugiProzor();
                            problem.add("Ne mogu pokrenuti dretvu. Ispravnost nije unesena.");
                            view.azurirajGornjiProzor(problem);
                        }
                    }
                    view.obrisiPrviProzor();
                    view.ispisiIzbornik();
                    break;
                case "PI":
                    if (atributi.length == 2) {
                        if (Integer.parseInt(atributi[1]) >= 1 && Integer.parseInt(atributi[1]) <= 100) {
                            ispravnost = Integer.parseInt(atributi[1]);
                        }
                    }
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.ispisiIzbornik();
                    break;
                case "H":
                    view.obrisiPrviProzor();
                    view.obrisiDrugiProzor();
                    view.azurirajGornjiProzor(Opcije.opcije());
                    view.ispisiIzbornik();
                    break;
                default:
                    //view.obrisiPrviProzor();
                    //view.ispisiIzbornik();
                    view.donji_uvecani++;
                    view.azurirajDonjiProzor();
                    break;
            }

        } while (!odabir.equalsIgnoreCase("I"));

    }
}
