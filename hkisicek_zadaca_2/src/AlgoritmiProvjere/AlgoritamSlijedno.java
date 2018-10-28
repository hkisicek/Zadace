/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import Generator.Generator;
import Iterator.Iterator;
import Iterator.IteratorMjesta;
import Uređaji.Senzor;
import hkisicek_zadaca_2.Mjesto;

/**
 *
 * @author helena
 */
public class AlgoritamSlijedno implements Algoritam {

    public Mjesto[] mjesta;
    IteratorMjesta iterator;
    
    public AlgoritamSlijedno(Mjesto[] mjesta) {
        this.mjesta=mjesta;
        this.iterator=new IteratorMjesta(mjesta);
    }
    
    @Override
    public Mjesto dajMjesto(int indeks) {
        if(indeks<mjesta.length){
            return iterator.trenutni(indeks);
        }else{
            indeks=indeks%mjesta.length;
            return iterator.trenutni(indeks);
        }
    }

  
}
