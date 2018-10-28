/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.View;

import Utils.Konstante;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Helena
 */
public class GlavniView {

    private int broj_redaka, broj_stupaca, broj_komandi, pocetak_gornji, pocetak_donji, kraj_gornji, kraj_donji, trenutni_gornji, trenutni_donji, sirina;
    public int donji_uvecani;

    public GlavniView(int broj_redaka, int broj_stupaca, int broj_komandi) {
        this.broj_redaka = broj_redaka;
        this.broj_stupaca = broj_stupaca;
        this.broj_komandi = broj_komandi;
        sirina = broj_stupaca;
    }

    private void postavi() {
        this.pocetak_gornji = 1;
        this.trenutni_gornji = 1;
        this.kraj_gornji = this.broj_redaka - broj_komandi;
        this.pocetak_donji = this.kraj_gornji + 1;
        this.kraj_donji = this.pocetak_donji + broj_komandi - 1;
        this.donji_uvecani = this.pocetak_donji;
    }

    public void ispisiGornjiProzor() {
        postavi();
        System.out.print(Konstante.ERASE_SCREEN);
    }

    public void ispisiInputProzor() {
        postaviKursor(kraj_gornji + 1, 1);
        System.out.print(Konstante.CURSOR_SAVE);
        System.out.print("Odabir komande: ");
    }

    public void ispisiPocetniProzor(ArrayList<String> podaci) {
        for (int counter = 0; counter < podaci.size(); counter++) {
            System.out.print(Konstante.ANSI_ESC + trenutni_gornji + ";" + 1 + "f");
            System.out.print(podaci.get(counter));
            this.provjeriKraj();
            this.setSleep(500);
        }
    }

    public void azurirajGornjiProzor(List<String> podaci) {
        String format = "|%1$-40s%3$-20s|\n";
        for (int counter = 0; counter < podaci.size(); counter++) {
            System.out.print(Konstante.ANSI_ESC + trenutni_gornji + ";" + 1 + "f");
            if (podaci.get(counter).length() > sirina) {
                this.okviriZapis(podaci.get(counter), broj_stupaca, kraj_gornji, pocetak_gornji);
            } else {
                System.out.print(podaci.get(counter));
            }
            int length = podaci.get(counter).length();
            this.provjeriKraj();
            this.setSleep(500);
        }
        this.setSleep(1000);
    }

    public void azurirajGornjiProzorFormat(List<String> podaci) {
        String format = "|%1$-40s%3$-20s|\n";
        for (int counter = 0; counter < podaci.size(); counter++) {
            System.out.println(Konstante.ANSI_ESC + trenutni_gornji + ";" + 1 + "f");
            if (podaci.get(counter).length() > sirina) {
                this.okviriZapis(podaci.get(counter), broj_stupaca, kraj_gornji, pocetak_gornji);
            } else {
                String zapis = podaci.get(counter);
                String[] polje = zapis.split(":");
                System.out.format(format, polje[0], "", polje[1]);
            }
            int length = podaci.get(counter).length();
            this.provjeriKraj();
            this.setSleep(500);
        }
        this.setSleep(1000);
    }

    public void azurirajDonjiProzor() {
        if (donji_uvecani > kraj_donji) {
            obrisiDrugiProzor();
            ispisiInputProzor();
            donji_uvecani = pocetak_donji;
        }
    }

    private void okviriZapis(String red, int pocetni_stupac, int kraj_gornji, int pocetak_gornji) {
        System.out.print(red.substring(0, sirina));
        for (int n = sirina; n < red.length(); n = n + sirina) {
            System.out.print(Konstante.ANSI_ESC + "1B");
            System.out.print(Konstante.ANSI_ESC + (sirina) + "D");
        }
        trenutni_gornji++;
        System.out.print(Konstante.ANSI_ESC + trenutni_gornji + ";" + 1 + "f");
        System.out.print(red.substring(sirina));
    }

    private void provjeriKraj() {
        if (trenutni_gornji == kraj_gornji) {
            System.out.print(Konstante.CURSOR_SAVE);
            System.out.print("\nZa nastavak se treba pritisnuti tipka n/N  ");
            cekajNastavak();
        } else {
            trenutni_gornji++;
        }
    }

    public void obrisiPrviProzor() {
        for (int i = pocetak_gornji; i <= kraj_gornji + 1; i++) {
            this.postaviKursor(i, 1);
            System.out.print(Konstante.ERASE_LINE);
            this.setSleep(20);
        }
        trenutni_gornji = 1;
    }

    public void obrisiDrugiProzor() {
        for (int i = pocetak_donji; i <= kraj_donji + 1; i++) {
            this.postaviKursor(i, 1);
            System.out.print(Konstante.ERASE_LINE);
            this.setSleep(20);
        }
    }

    public void ispisiIzbornik() {
        System.out.print(Konstante.ERASE_SCREEN);
        System.out.println("-------------------------------------------------");
        System.out.println("\t\tGLAVNI IZBORNIK");
        System.out.println("-------------------------------------------------");
        System.out.println("M x - ispis podataka mjesta x");
        System.out.println("S x - ispis podataka senzora x");
        System.out.println("A x - ispis podataka aktuatora x");
        System.out.println("S - ispis statistike");
        System.out.println("SP - spremi podatke (mjesta, uređaja)");
        System.out.println("VP - vrati spremljene podatke (mjesta, uređaja)");
        System.out.println("C n - izvršavanje n ciklusa dretve (1-100)");
        System.out.println("VF - izvršavanje vlastite funkcionalnosti");
        System.out.println("PI n - prosječni % ispravnosti uređaja (0-100)");
        System.out.println("H - pomoć, ispis dopuštenih komandi i njihov opis");
        System.out.println("I - izlaz.");
        System.out.println("-------------------------------------------------");

        ispisiInputProzor();
    }

    private void postaviKursor(int x, int y) {
        System.out.print(Konstante.ANSI_ESC + x + ";" + y + "f");
    }

    private void setSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
        }
    }

    public boolean cekajNastavak() {
        boolean nastavak = false;
        String odabir = "";
        do {
            System.out.print(Konstante.CURSOR_SAVE);
            Scanner in = new Scanner(System.in);
            odabir = in.nextLine();
        } while (!odabir.equalsIgnoreCase("n"));
        this.obrisiPrviProzor();
        trenutni_gornji = pocetak_gornji;
        return nastavak;
    }

    public void formatiraj(List<String> podaci) {
        String format = "|%1$-40s%3$-20s|\n";
        for (String podatak : podaci) {
            String[] polje = podatak.split(":");
            System.out.format(format, polje[0], "", polje[1]);
        }
    }
}
