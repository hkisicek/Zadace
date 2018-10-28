/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datoteke;

import hkisicek_zadaca_4.Mjesto;
import java.util.List;

/**
 *
 * @author helena
 */
public interface Datoteka {
    
    void otvoriDatoteku();
    void provjeriDatoteku();
    void zatvoriDatoteku();
    List<String> dohvatiZapise();
    List<Object> vratiProcitano();   
}
