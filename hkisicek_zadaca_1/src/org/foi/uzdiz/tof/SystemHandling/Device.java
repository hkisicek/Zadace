/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.SystemHandling;

/**
 *
 * @author helena
 */
abstract class Device {
    protected String naziv;
    protected int tip;
    protected int vrsta;
    protected double min_vrijednost;
    protected double max_vrijednost;
    protected String komentar;

    Device(
            String naziv,
            int tip,
            int vrsta,
            double min_vrijednost,
            double max_vrijednost,
            String komentar
    ){
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.min_vrijednost = min_vrijednost;
        this.max_vrijednost = max_vrijednost;
        this.komentar = komentar;
    }
}
