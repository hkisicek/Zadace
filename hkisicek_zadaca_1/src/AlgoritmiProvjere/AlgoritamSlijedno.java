/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import hkisicek_zadaca_1.Mjesto;
import java.util.List;

/**
 *
 * @author helena
 */
public class AlgoritamSlijedno implements Algoritam {

    private List<Mjesto> mjesta;
    
    AlgoritamSlijedno(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    @Override
    public List<Mjesto> dajMjesta() {
        return mjesta;
    }

 
}
