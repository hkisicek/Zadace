package org.foi.uzdiz.tof.ThreadHandling;

import org.foi.uzdiz.tof.ThreadHandling.Dretva;
import org.foi.uzdiz.tof.FileHandling.FilesReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DretvaBuilder {

    public Dretva buildDretva(String[] args) {
        Dretva dretva = Dretva.inicijalizirajDretvu();

        dretva.setSjeme(Integer.valueOf(args[0]));
        dretva.setMjesta(args[1]);
        dretva.setSenzori(args[2]);
        dretva.setAktuatori(args[3]);
        dretva.setAlgoritam(args[4]);
        dretva.setTrajanjeCiklusa(Integer.valueOf(args[5]));
        dretva.setBrojCiklusa(Integer.valueOf(args[6]));
        dretva.setExportFile(args[7]);

        return dretva;
    }
}
