/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Helena
 */
public class Caretaker {

    private final Map<String, Memento> tockaSpremanja = new HashMap<String, Memento>();

    public void spremiMemento(Memento memento, String savepointName) {
        tockaSpremanja.put(savepointName, memento);
    }

    public Memento dohvatiMemento(String savepointName) {
        return tockaSpremanja.get(savepointName);
    }

    public void obrisiTockeSpremanja() {
        tockaSpremanja.clear();
    }

}
