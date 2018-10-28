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
public class Sensor extends Device {

    public Sensor(
            String naziv,
            int tip,
            int vrsta,
            double min_vrijednost,
            double max_vrijednost,
            String komentar
    ) {
        super(naziv,tip,vrsta,min_vrijednost,max_vrijednost,komentar);
    }
}
