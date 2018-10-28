/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_1;

import AlgoritmiProvjere.AlgoritamSlijedno;
import Datoteke.AktuatorDatoteka;
import Datoteke.MjestaDatoteka;
import Datoteke.SenzorDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author helena
 */
public class Hkisicek_zadaca_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        String datotekaMjesta = "", datotekaSenzori = "", datotekaAktuatori = "", algoritam = "", izlaznaDatoteka = "";
        int sjeme, trajanjeCiklusa, brojCiklusa = 0;

        //717 DZ_1_mjesta.txt DZ_1_senzori.txt DZ_1_aktuatori.txt AlgoritamAbecedno 5 20 izlaz.txt
        String sintaksa = "^([0-9]{3,}) ([\\a-zA-Z0-9_/.]*.txt) ([\\a-zA-Z0-9_/.]*.txt) ([\\a-zA-Z0-9_/.]*.txt) ([a-zA-Z0-9_]*) ([0-9]{0,}) ([0-9]{0,}) ([\\a-zA-Z0-9_/.]*.txt)$";

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }

        String parts = stringBuilder.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher matcher = pattern.matcher(parts);
        boolean status = matcher.matches();

        String text = null;
        if (status) {
            sjeme = Integer.parseInt(matcher.group(1));
            datotekaMjesta = matcher.group(2);
            datotekaSenzori = matcher.group(3);
            datotekaAktuatori = matcher.group(4);
            algoritam = matcher.group(5);
            trajanjeCiklusa = Integer.parseInt(matcher.group(6));
            brojCiklusa = Integer.parseInt(matcher.group(7));
            izlaznaDatoteka = matcher.group(8);

            System.out.println("Sjeme: " + sjeme
                    + "\nDatoteka mjesta: " + datotekaMjesta
                    + "\nDatoteka senzora: " + datotekaSenzori
                    + "\nDatoteka aktuatora:" + datotekaAktuatori
                    + "\nAloritam: " + algoritam
                    + "\nTrajanje ciklusa: " + trajanjeCiklusa
                    + "\nBroj ciklusa: " + brojCiklusa
                    + "\nIzlazna datoteka: " + izlaznaDatoteka);

            Aplikacija aplikacija = Aplikacija.instanciraj();
            aplikacija.konfiguriraj(sjeme, datotekaMjesta, datotekaSenzori, datotekaAktuatori, algoritam, trajanjeCiklusa, brojCiklusa, izlaznaDatoteka);
            aplikacija.pokreniAplikaciju();
        } else {
            System.out.println("\nPogrešan unos! Pokušaj ponovno!");
            return;
        }
    }
}
