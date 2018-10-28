/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmiProvjere;

import hkisicek_zadaca_3.Mjesto;

/**
 *
 * @author helena
 */
public class AlgoritamFactory {

    public Mjesto[] mjesta;

    public AlgoritamFactory(Mjesto[] mjesta) {
        this.mjesta = mjesta;
    }

    public Algoritam odaberiAlgoritam(String naziv) {
        switch (naziv) {
            case "AlgoritamSlijedno":
                return new AlgoritamSlijedno(mjesta);
            case "AlgoritamAbecedno":
                return new AlgoritamUzlazno(mjesta);
            case "AlgoritamSlucajno":
                return new AlgoritamSlucajno(mjesta);
            default:
                return null;
        }
    }
}
