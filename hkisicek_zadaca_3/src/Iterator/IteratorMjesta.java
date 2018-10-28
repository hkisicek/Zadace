/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import hkisicek_zadaca_3.Mjesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helena
 */
public class IteratorMjesta implements Iterator {

    private Mjesto[] mjesta;
    int indeks = 0;

    public IteratorMjesta(Mjesto[] mjesta) {
        this.mjesta = mjesta;
    }

    @Override
    public Mjesto prvi() {
        if (mjesta != null) {
            return mjesta[0];
        } else {
            return null;
        }
    }

    @Override
    public Mjesto trenutni(int indeks) {
        if (mjesta != null) {
            return mjesta[indeks];
        }
        return null;
    }

    @Override
    public boolean postojiSljedeci(int indeks) {
        if (indeks >= mjesta.length) {
            return false;
        }else if (mjesta[indeks++] != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Mjesto sljedeci(int indeks) {
        if (postojiSljedeci(indeks)) {
            return mjesta[indeks++];
        } else {
            return null;
        }
    }

    @Override
    public void dodaj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
