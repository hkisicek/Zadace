/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import hkisicek_zadaca_2.Mjesto;

/**
 *
 * @author helena
 */
public interface Iterator {
    
    public Mjesto prvi();
    public Mjesto trenutni(int indeks);
    public boolean postojiSljedeci(int indeks);
    public Mjesto sljedeci(int indeks);
    public void dodaj();
}
