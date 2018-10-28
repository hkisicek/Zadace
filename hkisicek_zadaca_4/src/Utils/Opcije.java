/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Opcije {

    public static List<String> opcije() {
        List<String> komande = new ArrayList<String>();
        komande.add("M x - ispis podataka mjesta x");
        komande.add("S x - ispis podataka senzora x");
        komande.add("A x - ispis podataka aktuatora x");
        komande.add("SM x - ispis strukture mjesta x");
        komande.add("TS x  - ispis podataka o kolekciji modela senzora x (npr. max broj, trenutni broj, broj zamjena, broj nabavljana itd.");
        komande.add("TA x  - ispis podataka o kolekciji modela aktuatora x (npr. max broj, trenutni broj, broj zamjena, broj nabavljana itd.");
        komande.add("S - ispis statistike");
        komande.add("SP - spremi podatke (mjesta, uređaja)");
        komande.add("VP - vrati spremljene podatke (mjesta, uređaja)");
        komande.add("C n - izvršavanje n ciklusa dretve (1-100)");
        komande.add("CP n - broj ciklusa dretve nakon kojih je uređaj popravljen i vraća se na raspolaganje (1-99)");
        komande.add("PI n - prosječni % ispravnosti uređaja (0-100)");
        komande.add("H - pomoć, ispis dopuštenih komandi i njihov opis");
        komande.add("I - izlaz");
        return komande;
    }
}
