/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import Uređaji.Aktuator;
import Uređaji.Senzor;
import hkisicek_zadaca_4.Mjesto;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Originator {

    private List<Mjesto> mjesta;
    private List<Senzor> senzori;
    private List<Aktuator> aktuatori;
    private String zadnjeSpremanje;
    Caretaker caretaker = new Caretaker();

    public Originator(List<Mjesto> mjesta, List<Senzor> senzori, List<Aktuator> aktuatori, Caretaker caretaker) {
        this.mjesta = mjesta;
        this.senzori = senzori;
        this.aktuatori = aktuatori;
        this.caretaker = caretaker;
    }

    public void kreirajTockuSpremanja(String nazivTocke) {
        caretaker.spremiMemento(new Memento(mjesta, senzori, aktuatori), nazivTocke);
        zadnjeSpremanje = nazivTocke;
    }

    public void vrati() {
        postaviStanje(zadnjeSpremanje);
    }

    public void vrati(String tockaSpremanja) {
        postaviStanje(tockaSpremanja);
    }

    public void vratiSve() {
        postaviStanje("POCETNO");
    }

    private void postaviStanje(String tockaSpremanja) {
        Memento mem = caretaker.dohvatiMemento(tockaSpremanja);
        this.mjesta = mem.getMjesta();
    }

}
