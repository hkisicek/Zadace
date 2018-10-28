/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof;
import org.foi.uzdiz.tof.ThreadHandling.DretvaBuilder;
import org.foi.uzdiz.tof.ThreadHandling.Dretva;
import org.foi.uzdiz.tof.FileHandling.FilesReader;
import org.foi.uzdiz.tof.FileHandling.LocationsAdapter;
import org.foi.uzdiz.tof.FileHandling.SensorsAdapter;
import org.foi.uzdiz.tof.FileHandling.ActuatorAdapter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author helena
 */
public class ToF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Dretva dretva = new DretvaBuilder().buildDretva(args);

        if(dretva.init() == false) {
            ///error
        }
        dretva.pokreni();

    }
}
