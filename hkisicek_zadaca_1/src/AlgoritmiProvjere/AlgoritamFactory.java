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
public class AlgoritamFactory {

    public List<Mjesto> mjesta;

    public AlgoritamFactory(List<Mjesto> mjesta) {
        this.mjesta = mjesta;
    }

    public Algoritam odaberiAlgoritam(String naziv) {
        switch (naziv) {
            case "AlgoritamSlijedno":
                return new AlgoritamSlijedno(mjesta);
            case "AlgoritamObrnuto":
                return new AlgoritamObrnuto(mjesta);
            case "AlgoritamSlucajno":
                return new AlgoritamUzlazno(mjesta);
            default:
                return null;
        }
    }
}
