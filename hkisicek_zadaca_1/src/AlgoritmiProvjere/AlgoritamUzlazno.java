/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import Generator.Generator;
import hkisicek_zadaca_1.Mjesto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author helena
 */
public class AlgoritamUzlazno implements Algoritam {

    private List<Mjesto> mjesta;
    private Random random;

    public AlgoritamUzlazno(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    @Override
    public List<Mjesto> dajMjesta() {
        List<Mjesto> sortiranaMjesta = mjesta;
        int brojac = 0;
        boolean zamijenjeno = true;
        int duljina = mjesta.size() - 1;
        for (int j = duljina; zamijenjeno && j > 0; j--) {
            zamijenjeno = false;
            for (int k = 0; k < j; k++) {
                if (sortiranaMjesta.get(k).getJednoznacniBroj() > sortiranaMjesta.get(k + 1).getJednoznacniBroj()) {
                    Mjesto zamjena = sortiranaMjesta.get(k);
                    sortiranaMjesta.set(k, sortiranaMjesta.get(k + 1));
                    sortiranaMjesta.set(k + 1, zamjena);
                    zamijenjeno = true;
                }
            }
        }
        return sortiranaMjesta;
    }
}
