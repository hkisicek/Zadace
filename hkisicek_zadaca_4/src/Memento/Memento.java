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
public class Memento {

    private List<Mjesto> mjesta;
    private List<Senzor> senzori;
    private List<Aktuator> aktuatori;

    public Memento(List<Mjesto> mjesta, List<Senzor> senzori, List<Aktuator> aktuatori) {
        this.mjesta = mjesta;
        this.senzori = senzori;
        this.aktuatori = aktuatori;
    }

    public List<Mjesto> getState() {
        return mjesta;
    }

    public List<Mjesto> getMjesta() {
        return mjesta;
    }

    public void setMjesta(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    public List<Senzor> getSenzori() {
        return senzori;
    }

    public List<Aktuator> getAktuatori() {
        return aktuatori;
    }
    
    
    
}
