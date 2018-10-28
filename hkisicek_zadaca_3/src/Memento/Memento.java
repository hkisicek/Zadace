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
public class Memento {

    private List<Mjesto> mjesta;

    public Memento(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
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
    
    
}
