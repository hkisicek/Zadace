/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Generator.Generator;
import hkisicek_zadaca_2.Mjesto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helena
 */
public class MjestaDatoteka implements Datoteka {

    private String imeDatoteke;
    List<Mjesto> mjesta;
    BufferedReader reader;

    public MjestaDatoteka(String imeDatoteke) {
        this.imeDatoteke = imeDatoteke;
    }
    
    @Override
    public void otvoriDatoteku() {
        try {
            reader = new BufferedReader(new FileReader(this.imeDatoteke));
        } catch (FileNotFoundException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void provjeriDatoteku() {
        String red;
        mjesta = new ArrayList<>();

        try {
            Mjesto mjesto;
            red = reader.readLine();
            boolean prvaLinija = true;
            while (red != null) {

                String[] atributi = red.split(";");
                red = reader.readLine();
                if (prvaLinija) {
                    prvaLinija = false;
                } else {
                    if (Integer.parseInt(atributi[1]) >= 0 && Integer.parseInt(atributi[1]) < 2) {

                        mjesto = new Mjesto(atributi[0],
                                Integer.parseInt(atributi[1]),
                                Integer.parseInt(atributi[2]),
                                Integer.parseInt(atributi[3]));

                        mjesta.add(mjesto);
                    }
                }
            }
        } catch (IOException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }

        System.out.println("\n----Čitam datoteku mjesta------");

        mjesta.stream().map((s) -> {
            System.out.println("");
            return s;
        }).map((s) -> {
            System.out.println("Naziv: " + s.getNaziv()
                    + "\nTip: " + s.getTip()
                    + "\nBroj senzora: " + s.getBrojSenzora()
                    + "\nBroj aktuatora: " + s.getBrojAktuatora());
            
            return s;

        }).forEachOrdered((s) -> {

        });
    }

    @Override
    public void zatvoriDatoteku() {
        try {
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void zapisi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Mjesto[] vratiMjesta() {
        otvoriDatoteku();
        provjeriDatoteku();
        zatvoriDatoteku();
        List<Object> listaMjesta = new ArrayList<>(mjesta);
        Mjesto[] popisMjesta = mjesta.toArray(new Mjesto[mjesta.size()]);
        return popisMjesta;
    }

    @Override
    public List<Object> vratiProcitano() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
