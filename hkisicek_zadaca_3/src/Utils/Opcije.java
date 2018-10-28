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
        komande.add("S - ispis statistike");
        komande.add("SP - spremi podatke (mjesta, uređaja)");
        komande.add("VP - vrati spremljene podatke (mjesta, uređaja)");
        komande.add("VF [argumenti] - izvršavanje vlastite funkcionalnosti, po potrebni mogući su argumenti");
        komande.add("PI n - prosječni % ispravnosti uređaja (0-100)");
        komande.add("H - pomoć, ispis dopuštenih komandi i njihov opis");
        komande.add("I - izlaz");
        return komande;
    }
}
