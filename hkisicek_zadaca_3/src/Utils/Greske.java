/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Helena
 */
public final class Greske {

    public static final String MJESTA_GRESKA_00 = "Neispravan zapis mjesta.";
    public static final String MJESTA_GRESKA_01 = "Nedostaje argument u zapisu.";
    public static final String MJESTA_GRESKA_03 = "Argument je u krivom formatu.";
    public static final String MJESTA_GRESKA_04 = "Pogrešan tip mjesta.";
    public static final String MJESTA_GRESKA_05 = "Mjesto pod navedenim id-em već postoji.";
    public static final String MJESTA_GRESKA_06 = "Mjesto i senzor ne odgovaraju tipu.";
    public static final String MJESTA_GRESKA_07 = "Mjesto i aktuator ne odgovaraju tipu.";

    public static final String SENZOR_GRESKA_00 = "Neispravan zapis senzora.";
    public static final String SENZOR_GRESKA_01 = "Nedostaje argument u zapisu.";
    public static final String SENZOR_GRESKA_03 = "Argument je u krivom formatu.";
    public static final String SENZOR_GRESKA_04 = "Pogrešan tip senzora.";
    public static final String SENZOR_GRESKA_05 = "Pogrešna vrsta senzora.";
    public static final String SENZOR_GRESKA_06 = "Senzor pod navedenim id-em već postoji.";

    public static final String AKTUATOR_GRESKA_00 = "Neispravan zapis aktuatora.";
    public static final String AKTUATOR_GRESKA_01 = "Nedostaje argument u zapisu.";
    public static final String AKTUATOR_GRESKA_03 = "Argument je u krivom formatu.";
    public static final String AKTUATOR_GRESKA_04 = "Pogrešan tip aktuatora.";
    public static final String AKTUATOR_GRESKA_05 = "Pogrešna vrsta aktuatora.";
    public static final String AKTUATOR_GRESKA_06 = "Aktuator pod navedenim id-em već postoji.";

    public static final String RASPORED_GRESKA_00 = "Neispravan zapis rasporeda mjesta.";
    public static final String RASPORED_GRESKA_01 = "Nedostaje argument u zapisu.";
    public static final String RASPORED_GRESKA_03 = "Argument je u krivom formatu.";
    public static final String RASPORED_GRESKA_04 = "Pogrešna vrsta uređaja.";
    public static final String RASPORED_GRESKA_05 = "Uređaj pod navedenim id-em ne postoji.";
    public static final String RASPORED_GRESKA_06 = "Uređaj pod navedenim id-em već postoji.";

    public static String vratiGreskuMjesta(int id_greske) {
        if (id_greske == 1) {
            return MJESTA_GRESKA_00;
        } else if (id_greske == 2) {
            return MJESTA_GRESKA_00 + " " + MJESTA_GRESKA_01;
        } else if (id_greske == 3) {
            return MJESTA_GRESKA_00 + " " + MJESTA_GRESKA_03;
        } else if (id_greske == 4) {
            return MJESTA_GRESKA_00 + " " + MJESTA_GRESKA_04;
        } else if (id_greske == 5) {
            return MJESTA_GRESKA_00 + " " + MJESTA_GRESKA_05;
        } else if (id_greske == 6) {
            return MJESTA_GRESKA_06;
        } else if (id_greske == 7) {
            return MJESTA_GRESKA_07;
        } else {
            return "";
        }
    }

    public static String vratiGreskuSenzor(int id_greske) {
        if (id_greske == 1) {
            return SENZOR_GRESKA_00;
        } else if (id_greske == 2) {
            return SENZOR_GRESKA_00 + " " + SENZOR_GRESKA_01;
        } else if (id_greske == 3) {
            return SENZOR_GRESKA_00 + " " + SENZOR_GRESKA_03;
        } else if (id_greske == 4) {
            return SENZOR_GRESKA_00 + " " + SENZOR_GRESKA_04;
        } else if (id_greske == 5) {
            return SENZOR_GRESKA_00 + " " + SENZOR_GRESKA_05;
        } else if (id_greske == 6) {
            return SENZOR_GRESKA_00 + " " + SENZOR_GRESKA_06;
        } else {
            return "";
        }
    }

    public static String vratiGreskuAktuator(int id_greske) {
        if (id_greske == 1) {
            return AKTUATOR_GRESKA_00;
        } else if (id_greske == 2) {
            return AKTUATOR_GRESKA_00 + " " + AKTUATOR_GRESKA_01;
        } else if (id_greske == 3) {
            return AKTUATOR_GRESKA_00 + " " + AKTUATOR_GRESKA_03;
        } else if (id_greske == 4) {
            return AKTUATOR_GRESKA_00 + " " + AKTUATOR_GRESKA_04;
        } else if (id_greske == 5) {
            return AKTUATOR_GRESKA_00 + " " + AKTUATOR_GRESKA_05;
        } else if (id_greske == 6) {
            return AKTUATOR_GRESKA_00 + " " + AKTUATOR_GRESKA_06;
        } else {
            return "";
        }
    }

    public static String vratiGreskuRaspored(int id_greske) {
        if (id_greske == 1) {
            return RASPORED_GRESKA_00;
        } else if (id_greske == 2) {
            return RASPORED_GRESKA_00 + " " + RASPORED_GRESKA_01;
        } else if (id_greske == 3) {
            return RASPORED_GRESKA_00 + " " + RASPORED_GRESKA_03;
        } else if (id_greske == 4) {
            return RASPORED_GRESKA_00 + " " + RASPORED_GRESKA_04;
        } else if (id_greske == 5) {
            return RASPORED_GRESKA_00 + " " + RASPORED_GRESKA_05;
        } else {
            return "";
        }
    }

}
