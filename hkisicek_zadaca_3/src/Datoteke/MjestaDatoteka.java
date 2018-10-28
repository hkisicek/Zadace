/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import Generator.Generator;
import Utils.Konstante;
import Utils.Greske;
import hkisicek_zadaca_3.Mjesto;
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

    private final String imeDatoteke;
    private static List<Mjesto> mjesta;
    private List<String> zapisi;
    private BufferedReader reader;

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
        zapisi = new ArrayList<>();
        try {
            Mjesto mjesto;
            red = reader.readLine();
            boolean prvaLinija = true;
            zapisi.add("ČITAM DATOTEKU MJESTA...");
            while (red != null) {
                String[] atributi = red.split(";");
                if (prvaLinija) {
                    prvaLinija = false;
                } else {
                    if (!postojiMjesto(atributi[0]) && provjeriMjesto(red).equals("Ispravno")) {
                        mjesto = new Mjesto(Integer.parseInt(atributi[0]),
                                atributi[1],
                                Integer.parseInt(atributi[2]),
                                Integer.parseInt(atributi[3]),
                                Integer.parseInt(atributi[4]));

                        mjesta.add(mjesto);

                    } else {
                        if (postojiMjesto(atributi[0])) {
                            zapisi.add(Greske.vratiGreskuMjesta(5) + " U retku: " + red);
                        } else {
                            zapisi.add(provjeriMjesto(red));
                        }
                    }
                }
                red = reader.readLine();
            }
        } catch (IOException e) {
            Logger.getLogger(SenzorDatoteka.class.getName()).log(Level.SEVERE, null, e);
        }
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
    public List<Object> vratiProcitano() {
        otvoriDatoteku();
        provjeriDatoteku();
        zatvoriDatoteku();
        List<Object> listaMjesta = new ArrayList<>(mjesta);
        return listaMjesta;
    }

    public static boolean postojiMjesto(String id_mjesta) {
        boolean postojiMjesto = false;
        for (Mjesto mjesto : mjesta) {
            if (mjesto.getId() == Integer.parseInt(id_mjesta)) {
                postojiMjesto = true;
            }
        }
        return postojiMjesto;
    }

    public String provjeriMjesto(String red) {
        String[] atributi = red.split(";");
        if (atributi.length == 5) {
            if (!"".equals(atributi[0]) && !"".equals(atributi[1]) && !"".equals(atributi[2]) && !"".equals(atributi[3]) && !"".equals(atributi[4])) {
                try {
                    int id = Integer.parseInt(atributi[0]);
                    int tip = Integer.parseInt(atributi[2]);
                    int broj_senzora = Integer.parseInt(atributi[3]);
                    int broj_aktuatora = Integer.parseInt(atributi[4]);
                } catch (NumberFormatException e) {
                    return Greske.vratiGreskuMjesta(3);
                }
                if (atributi[2].equals("0") || atributi[2].equals("1")) {
                    return "Ispravno";
                } else {
                    return Greske.vratiGreskuMjesta(4);
                }
            } else {
                return Greske.vratiGreskuMjesta(3);
            }
        } else {
            return Greske.vratiGreskuMjesta(2);
        }
    }

    @Override
    public List<String> dohvatiZapise() {
        return this.zapisi;
    }
}
