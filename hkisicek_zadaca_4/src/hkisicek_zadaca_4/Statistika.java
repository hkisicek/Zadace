/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca_4;

import Uređaji.Aktuator;
import Uređaji.Senzor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Statistika {

    private List<Mjesto> mjesta;
    private List<Senzor> senzori;
    private List<Aktuator> aktuatori;

    public Statistika(List<Mjesto> mjesta, List<Senzor> senzori, List<Aktuator> aktuatori) {
        this.mjesta = mjesta;
        this.senzori = senzori;
        this.aktuatori = aktuatori;
    }
    
    public List<String> dohvatiStatistiku() {
        List<String> statistika = new ArrayList<>();
        statistika.add("Ukupan broj mjesta: " + "|" + mjesta.size());
        statistika.add("-------------------------------------------------");
        for (Mjesto mjesto : mjesta) {
            statistika.add("Mjesto: " + mjesto.getNaziv());
            statistika.add("Broj aktuatora: " + mjesto.getBrojAktuatora());
            statistika.add("Broj senzora: " + mjesto.getBrojSenzora());
            statistika.add("Broj provjera: " + mjesto.getBrojProvjera());
            for (Senzor senzor : mjesto.getSenzoriMjesta()) {
                statistika.add("Senzor naziv: " + senzor.getNaziv());
            }
            for (Aktuator aktuator : mjesto.getAktuatoriMjesta()) {
                statistika.add("Aktuator naziv: " + aktuator.getNaziv());
            }
        }
        return statistika;
    }

    public List<String> dohvatiInfoMj(int id) {
        List<String> infoMjesto = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            if (mjesto.getId() == id) {
                infoMjesto.add("Id mjesta: " + mjesto.getId());
                infoMjesto.add("Naziv mjesta: " + mjesto.getNaziv());
                infoMjesto.add("Tip mjesta: " + mjesto.getTip());
                infoMjesto.add("Broj aktuatora: " + mjesto.getBrojAktuatora());
                infoMjesto.add("Broj senzora: " + mjesto.getBrojSenzora());
            }
        }
        return infoMjesto;
    }

    public List<String> dohvatiInfoSe(int id) {
        List<String> infoSenzor = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            for (Senzor senzor : mjesto.getSenzoriMjesta()) {
                if (senzor.getSlucajniBroj() == id) {
                    infoSenzor.add("Senzor ID: " + senzor.getId());
                    infoSenzor.add("Senzor naziv: " + senzor.getNaziv());
                    infoSenzor.add("Senzor tip: " + senzor.getTip());
                    infoSenzor.add("Senzor vrsta: " + senzor.getVrsta());
                    infoSenzor.add("Senzor max: " + senzor.getMaxVrijednost());
                    infoSenzor.add("Senzor min: " + senzor.getMinVrijednost());
                    infoSenzor.add("Ocitana vrijednost: " + senzor.getOcitanaVrijednost());
                }
            }
        }
        return infoSenzor;
    }

    public List<String> dohvatiInfoAkt(int id) {
        List<String> infoAktuator = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            for (Aktuator aktuator : mjesto.getAktuatoriMjesta()) {
                if (aktuator.getSlucajniBroj() == id) {
                    infoAktuator.add("Aktuator ID: " + aktuator.getId());
                    infoAktuator.add("Aktuator naziv: " + aktuator.getNaziv());
                    infoAktuator.add("Aktuator tip: " + aktuator.getTip());
                    infoAktuator.add("Aktuator vrsta: " + aktuator.getVrsta());
                    infoAktuator.add("Aktuator max: " + aktuator.getMaxVrijednost());
                    infoAktuator.add("Aktuator min: " + aktuator.getMinVrijednost());
                    infoAktuator.add("Aktuator vrijednost: " + aktuator.getOcitanaVrijednost());
                }
            }
        }
        return infoAktuator;
    }
    
    public List<String> ispisStruktureMjesta(int id){
        List<String> infoStruktura = new ArrayList<>();
        for (Mjesto mjesto : mjesta) {
            if (mjesto.getId() == id) {
                List<Mjesto> unutarnjaMjesta = mjesto.getListaMjesta();
                infoStruktura.add("Mjesto naziv: " + mjesto.getNaziv());
                infoStruktura.add("Mjesto id: " + mjesto.getId());
                for (Mjesto mjestoo : unutarnjaMjesta) {
                    infoStruktura.add("Podmjesto mjesta: " + mjestoo.getNaziv());
                    infoStruktura.add("Podmjesto mjesta: " + mjestoo.getId());
                }
            }
        }
        return infoStruktura;
    }
    
    public List<String> dohvatiInfoKolekcijaSenz(int id){
        List<String> infoKolekcijaS = new ArrayList<>();
        for (Senzor senzor : senzori) {
            if (senzor.getId() == id) {
                infoKolekcijaS.add("Senzor model: " + senzor.getId());
                infoKolekcijaS.add("Trenutni broj uređaja: " + senzor.getBrojUredjaja());
                infoKolekcijaS.add("Minimalni broj uređaja: " + senzor.getMinVrijednost());
                infoKolekcijaS.add("Maksnimalni broj uređaja: " + senzor.getMaksimalanBroj());
                infoKolekcijaS.add("Broj nabava: " + senzor.getBrojNabave());
                infoKolekcijaS.add("Broj zamjena: " + senzor.getBrojZamjena());
            }
        }
        return infoKolekcijaS;
    }
    
    public List<String> dohvatiInfoKolekcijaAkt(int id) {
        List<String> infoKolekcijaA = new ArrayList<>();
        for (Aktuator aktuator : aktuatori) {
            if (aktuator.getId() == id) {
                infoKolekcijaA.add("Aktuator model: " + aktuator.getId());
                infoKolekcijaA.add("Trenutni broj uređaja: " + aktuator.getBrojUredjaja());
                infoKolekcijaA.add("Minimalni broj uređaja: " + aktuator.getMinVrijednost());
                infoKolekcijaA.add("Maksnimalni broj uređaja: " + aktuator.getMaksimalanBroj());
                infoKolekcijaA.add("Broj nabava: " + aktuator.getBrojNabave());
                infoKolekcijaA.add("Broj zamjena: " + aktuator.getBrojZamjena());
            }
        }
        return infoKolekcijaA;
    }
}
