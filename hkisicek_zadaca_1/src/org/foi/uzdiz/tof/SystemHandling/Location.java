/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.SystemHandling;

/**
 * singleton class
 * @author helena
 */
public class Location {

    protected String naziv;
    protected int tip;
    protected int broj_senzora;
    protected int broj_aktuatora;

    public Location(
            String naziv,
            int tip,
            int broj_senzora,
            int broj_aktuatora
    ) {
        this.naziv = naziv;
        this.tip = tip;
        this.broj_senzora = broj_senzora;
        this.broj_aktuatora = broj_aktuatora;
    }

    
}
