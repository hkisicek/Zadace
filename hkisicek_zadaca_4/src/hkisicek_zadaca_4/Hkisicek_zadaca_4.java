/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_4;

import Datoteke.AktuatorDatoteka;
import Datoteke.Datoteka;
import Datoteke.DatotekaFactory;
import Datoteke.MjestaDatoteka;
import Datoteke.RasporedDatoteka;
import Datoteke.SenzorDatoteka;
import Dretve.GlavnaDretva;
import Generator.Generator;
import MVC.Controller.GlavniController;
import MVC.Model.Model;
import MVC.View.GlavniView;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author helena
 */
public class Hkisicek_zadaca_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //v 
        int n = args.length;
        if (n < 1) {
            System.out.println("Neispravni argumenti!");
            System.exit(0);
        }

        int broj_redaka = -1;
        boolean brojRedaka = false;

        int broj_stupaca = -1;
        boolean brojStupaca = false;

        int broj_komandi = -1;
        boolean brojKomandi = false;

        int ispravnost_uredjaja = -1;
        boolean ispravnostUredjaja = false;

        int sjeme = (int) System.currentTimeMillis();
        boolean generator = false;

        int trajanje_ciklusa = -1;
        boolean trajanjeCiklusa = false;

        int kmax = 100;
        boolean k_max = false;

        int kmin = 5;
        boolean k_min = false;

        int kpov = 5;
        boolean k_pov = false;

        Format df = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date datum = Calendar.getInstance().getTime();
        String datotekaMjesta = "";
        String datotekaSenzora = "";
        String datotekaAktuatora = "";
        String datotekaRasporeda = "";

        if ("--help".equals(args[0])) {
            System.out.println("Help aplikacije...\n -br broj redaka na ekranu (24-40). Ako nije upisana opcija, uzima se 24"
                    + "\n -bs broj stupaca na ekranu (80-160). Ako nije upisana opcija, uzima se 80. "
                    + "\n -brk broj redaka na ekranu za unos komandi (2-5). Ako nije upisana opcija, uzima se 2."
                    + "\n -pi prosječni % ispravnosti uređaja (0-100). Ako nije upisana opcija, uzima se 50."
                    + "\n -g sjeme za generator slučajnog broja (u intervalu 100 - 65535). Ako nije upisana opcija, uzima se broj milisekundi u trenutnom vremenu na bazi njegovog broja sekundi i broja milisekundi."
                    + "\n -m naziv datoteke mjesta ."
                    + "\n -s naziv datoteke senzora"
                    + "\n -a naziv datoteke aktuatora"
                    + "\n -r naziv datoteke rasporeda"
                    + "\n -tcd trajanje ciklusa dretve u sek. Ako nije upisana opcija, uzima se slučajni broj u intervalu 1 - 17"
                    + "\n -kmax maksimalni broj uređaja za pojedini model uređaja. Ako nije upisana opcija, uzima se 100."
                    + "\n -kmin minimalni broj uređaja za pojedini model uređaja (kmin <= kmax, k0 = kmin). Ako nije upisana opcija, uzima se 5."
                    + "\n -kpov broj uređaja koji se dodaje važećem broju za pojedini model uređaja kada nema raspoloživih uređaja pojedinog modela uređaja (kmin <= kmax, ). Ako nije upisana opcija, uzima se 5.."
                    + "\n --help za pomoć u programu \n");
        } else {
            for (int i = 0; i < n; i++) {
                switch (args[i]) {
                    case "-br":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s >= 24 && s <= 40) {
                                    brojRedaka = true;
                                    broj_redaka = s;
                                } else {
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-bs":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s >= 80 && s <= 160) {
                                    brojStupaca = true;
                                    broj_stupaca = s;
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-brk":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s >= 2 && s <= 5) {
                                    brojKomandi = true;
                                    broj_komandi = s;
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-pi":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s >= 0 && s <= 100) {
                                    ispravnostUredjaja = true;
                                    ispravnost_uredjaja = s;
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-g":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s > 99 && s <= 65535) {
                                    sjeme = s;
                                    generator = true;
                                } else {
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
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
                    case "-r":
                        i++;
                        if (i == n) {
                        } else {
                            datotekaRasporeda = args[i];
                        }
                        break;
                    case "-tcd":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                trajanje_ciklusa = Integer.parseInt(args[i]);
                                trajanjeCiklusa = true;
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-kmax":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s > 0 || s <= 100) {
                                    kmax = Integer.parseInt(args[i]);
                                    k_max = true;
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-kmin":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                int s = Integer.parseInt(args[i]);
                                if (s <= kmax && s >= 0) {
                                    kmin = Integer.parseInt(args[i]);
                                    k_min = true;
                                }
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    case "-kpov":
                        i++;
                        if (i == n) {
                        } else {
                            try {
                                kpov = Integer.parseInt(args[i]);
                                k_pov = true;
                            } catch (Exception e) {
                                System.out.println("Pogrešni ulazni argumenti.");
                                return;
                            }
                        }
                        break;
                    default:
                        System.out.println("Ulazni argumenti su neispravni.");
                        return;
                }
            }
            Generator.getInstance().setSjeme(sjeme);
            if (brojRedaka == false) {
                broj_redaka = 24;
            }

            if (brojStupaca == false) {
                broj_stupaca = 80;
            }

            if (ispravnostUredjaja == false) {
                ispravnost_uredjaja = 50;
            }
            if (trajanjeCiklusa == false) {
                trajanje_ciklusa = Generator.getInstance().dajSlucajniBroj(1, 17);
            }

            Aplikacija aplikacija = Aplikacija.instanciraj();
            aplikacija.konfiguriraj(broj_redaka, broj_stupaca, broj_komandi, ispravnost_uredjaja, sjeme, datotekaMjesta, datotekaSenzora, datotekaAktuatora, datotekaRasporeda, trajanje_ciklusa, kmax, kmin, kpov);
            aplikacija.pokreniZadatak();
        }
    }
}
