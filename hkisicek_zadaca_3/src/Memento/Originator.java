/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import hkisicek_zadaca_3.Mjesto;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Originator {

    private List<Mjesto> mjesta;
    private String zadnjeSpremanje;
    Caretaker caretaker = new Caretaker();

    public Originator(List<Mjesto> mjesta, Caretaker caretaker) {
        this.mjesta = mjesta;
        this.caretaker = caretaker;
    }

    public void kreirajTockuSpremanja(String nazivTocke) {
        caretaker.spremiMemento(new Memento(mjesta), nazivTocke);
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
