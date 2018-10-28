/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import hkisicek_zadaca_1.Mjesto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author helena
 */
public class AlgoritamObrnuto implements Algoritam {

    public List<Mjesto> mjesta;
    private Random random;

    public AlgoritamObrnuto(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    @Override
    public List<Mjesto> dajMjesta() {
        List<Mjesto> sortiranaMjesta = new ArrayList<>();
        for(int i= mjesta.size()-1; i<=0; i--){
            sortiranaMjesta.add(mjesta.get(i));
        }  
        return sortiranaMjesta;
    }
}
