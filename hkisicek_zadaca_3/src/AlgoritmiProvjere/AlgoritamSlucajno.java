/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import Iterator.IteratorMjesta;
import hkisicek_zadaca_3.Mjesto;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author helena
 */
public class AlgoritamSlucajno implements Algoritam {

    public Mjesto[] mjesta;
    IteratorMjesta iterator;
    private Random random;

    public AlgoritamSlucajno(Mjesto[] mjesta) {
        this.mjesta = mjesta;
        this.iterator = new IteratorMjesta(mjesta);
    }

    @Override
    public Mjesto dajMjesto(int indeks) {
        int duljina=mjesta.length;
        int generiraniBroj=random.nextInt(mjesta.length);
        return iterator.trenutni(generiraniBroj);
    }
}
