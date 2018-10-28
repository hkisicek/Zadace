/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import Generator.Generator;
import Iterator.IteratorMjesta;
import hkisicek_zadaca_2.Mjesto;
import java.util.Random;

/**
 *
 * @author helena
 */
public class AlgoritamUzlazno implements Algoritam {

    public Mjesto[] mjesta;
    IteratorMjesta iterator, pomocniIterator;
    private Random random;

    public AlgoritamUzlazno(Mjesto[] mjesta) {
        this.mjesta = mjesta;
    }

    @Override
    public Mjesto dajMjesto(int indeks) {
        mjesta = sortiraj(mjesta);
        iterator = new IteratorMjesta(mjesta);
        if (indeks < mjesta.length) {
            return iterator.trenutni(indeks);
        } else {
            indeks = indeks % mjesta.length;
            return iterator.trenutni(indeks);
        }
    }

    public Mjesto[] sortiraj(Mjesto[] mjesta) {
        int brojac = 0;
        Mjesto[] sortiranaMjesta = this.mjesta;
        int duljina = sortiranaMjesta.length - 1;
        pomocniIterator = new IteratorMjesta(sortiranaMjesta);
        boolean zamijenjeno = true;

        for (int j = duljina; zamijenjeno && j > 0; j--) {
            zamijenjeno = false;
            for (int k = 0; k < j; k++) {
                if (sortiranaMjesta[k].getJednoznacniBroj() > sortiranaMjesta[k + 1].getJednoznacniBroj()) {
                    Mjesto zamjena = sortiranaMjesta[k];
                    sortiranaMjesta[k] = sortiranaMjesta[k + 1];
                    sortiranaMjesta[k + 1] = zamjena;
                    zamijenjeno = true;
                }
            }
        }
        return sortiranaMjesta;
    }

}
