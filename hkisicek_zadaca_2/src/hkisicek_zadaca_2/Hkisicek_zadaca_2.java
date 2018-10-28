/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_2;

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
public class Hkisicek_zadaca_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        /*        String datotekaMjesta = "", datotekaSenzori = "", datotekaAktuatori = "", algoritam = "", izlaznaDatoteka = "";
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
            aplikacija.procitajDatoteke();
            aplikacija.postaviSustav();
            aplikacija.inicijalizirajSustav();
            aplikacija.provjeriMjesta();

            
            
        } else {
            System.out.println("\nPogrešan unos! Pokušaj ponovno!");
            return;
        }*/
        int n = args.length;
        if (n < 1) {
            System.out.println("Neispravni argumenti!");
            System.exit(0);
        }

        Random rg = new Random();
        boolean gOk = false;
        boolean tcdOk = false;
        boolean bcdOk = false;
        boolean iOk = false;
        boolean brlOk = false;

        int sjeme = (int) System.currentTimeMillis();
        Format df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date datum = Calendar.getInstance().getTime();
        String datotekaMjesta = "";
        String datotekaSenzora = "";
        String datotekaAktuatora = "";
        String algoritam = "";
        String datotekaIzlaz = "hkisicek2_" + df.format(datum) + ".txt";
        int tcd = -1;
        int bcd = -1;
        int brl = -1;
        if ("--help".equals(args[0])) {
            System.out.println("Help aplikacije...\n -g sjeme za generator slučajnog broja (u intervalu 100 - 65535). Ako nije upisana opcija, uzima se broj milisekundi u trenutnom vremenu na bazi njegovog broja sekundi i broja milisekundi "
                    + "\n -m naziv datoteke mjesta "
                    + "\n -s naziv datoteke senzora"
                    + "\n -a naziv datoteke aktuatora "
                    + "\n -alg puni naziv klase algoritma provjere koja se dinamički učitava"
                    + "\n -tcd trajanje ciklusa dretve u sek. Ako nije upisana opcija, uzima se slučajni broj u intervalu 1 - 17 "
                    + "\n -bcd broj ciklusa dretve. Ako nije upisana opcija, uzima se slučajni broj u intervalu 1 - 23"
                    + "\n -i naziv datoteke u koju se sprema izlaz programa. Ako nije upisana opcija, uzima se vlastito korisničko ime kojem se dodaje trenutni podaci vremena po formatu _ggggmmdd_hhmmss.txt npr. dkermek_20171105_203128.txt "
                    + "\n -brl broj linija u spremniku za upis u datoteku za izlaz. Ako nije upisana opcija, uzima se slučajni broj u intervalu 100 - 999 \n");
        } else {
            for (int i = 0; i < n; i++) {
                switch (args[i]) {
                    case "-g":
                        i++;
                        if (i == n) {
                        } else {
                            int s = Integer.parseInt(args[i]);
                            if (s > 99 && s <= 65535) {
                                sjeme = s;
                                gOk = true;
                            } else {
                            }
                        }
                        break;
                    case "-m":
                        i++;
                        if (i == n) {
                        } else {
                            datotekaMjesta = args[i];
                        }
                        break;
                    case "-s":
                        i++;
                        if (i == n) {
                        } else {
                            datotekaSenzora = args[i];
                        }
                        break;
                    case "-a":
                        i++;
                        if (i == n) {
                        } else {
                            datotekaAktuatora = args[i];
                        }
                        break;
                    case "-alg":
                        i++;
                        if (i == n) {
                        } else {
                            algoritam = args[i];
                        }
                        break;
                    case "-tcd":
                        i++;
                        if (i == n) {
                        } else {
                            tcd = Integer.parseInt(args[i]);
                            tcdOk = true;
                        }
                        break;
                    case "-bcd":
                        i++;
                        if (i == n) {
                        } else {
                            bcd = Integer.parseInt(args[i]);
                            bcdOk = true;
                        }
                        break;
                    case "-i":
                        i++;
                        if (i == n) {
                        } else {
                            datotekaIzlaz = args[i];
                            iOk = true;
                        }
                        break;
                    case "-brl":
                        i++;
                        if (i == n) {
                        } else {
                            brl = Integer.parseInt(args[i]);
                            brlOk = true;
                        }
                        break;
                    default:
                        return;
                }
            }
            Generator.getInstance().setSjeme(sjeme);
            if (tcd == -1) {
                tcd = Generator.getInstance().dajSlucajniBroj(1, 17);
            }
            if (bcd == -1) {
                bcd = Generator.getInstance().dajSlucajniBroj(1, 23);
            }
            if (brl == -1) {
                brl = Generator.getInstance().dajSlucajniBroj(100, 999);
            }

            System.out.println("Sjeme: " + sjeme
                    + "\nDatoteka mjesta: " + datotekaMjesta
                    + "\nDatoteka senzora: " + datotekaSenzora
                    + "\nDatoteka aktuatora:" + datotekaAktuatora
                    + "\nAloritam: " + algoritam
                    + "\nTrajanje ciklusa: " + tcd
                    + "\nBroj ciklusa: " + bcd
                    + "\nIzlazna datoteka: " + datotekaIzlaz
                    + "\nBroj linija: " +brl);

            Aplikacija aplikacija = Aplikacija.instanciraj();
            aplikacija.konfiguriraj(sjeme, datotekaMjesta, datotekaSenzora, datotekaAktuatora, algoritam, tcd, bcd, datotekaIzlaz, brl);
            aplikacija.procitajDatoteke();
            aplikacija.postaviSustav();
            aplikacija.inicijalizirajSustav();
            aplikacija.provjeriMjesta();
        }
    }
}
