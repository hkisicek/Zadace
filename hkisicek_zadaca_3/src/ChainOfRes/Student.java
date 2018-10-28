/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfRes;

import Uređaji.Senzor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Helena
 */
public class Student implements Izvrsitelj {

    private String ime;
    private String prezime;
    private Izvrsitelj sljedeciIzvrsitelj;
    private boolean popravljeno;
    private List<String> popravci = new ArrayList<String>();

    public Student(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public void postaviSljedeceg(Izvrsitelj izvrsitelj) {
        this.sljedeciIzvrsitelj = izvrsitelj;
    }

    @Override
    public void popraviUređaj(Senzor senzor) {
        if (senzor.getRazina_greske() == 0) {
            popravljeno = true;
            senzor.setPopravljen(true);
            popravci.add("Student " + this.ime + " " + this.prezime + " je uspjesno popravio senzor: " + senzor.getId() + "-" + senzor.getNaziv());
        } else {
            popravci.add("Student " + this.ime + " " + this.prezime + " NIJE popravio senzor: " + senzor.getId() + "-" + senzor.getNaziv());
            this.sljedeciIzvrsitelj.popraviUređaj(senzor);
        }
    }

    @Override
    public List<String> ispisiPoruku() {
        return popravci;
    }

}
