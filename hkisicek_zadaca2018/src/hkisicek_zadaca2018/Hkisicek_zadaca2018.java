/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkisicek_zadaca2018;

import Utils.Provjera;

/**
 *
 * @author Helena
 */
public class Hkisicek_zadaca2018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        int brojParametara = args.length;
        
        if (brojParametara < 1 || brojParametara > 1) {
            System.out.println("Neispravni argumenti!");
            System.exit(0);
        } else {
            if(Provjera.provjeriUnos(args[0])){
                System.out.println("Pokrecem aplikaciju...");
                
                Aplikacija.instanciraj(args[0]);
                
            }
        }
    }
}
