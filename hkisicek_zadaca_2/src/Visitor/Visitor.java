/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import Datoteke.IzlazDatoteka;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author helena
 */
public class Visitor implements VisitorInterface {

    @Override
    public void visit(IzlazDatoteka datoteka) {
        
        List<String> podaci=datoteka.podaci;
        int duljina=podaci.size();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        String root=FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        
        podaci.add("\nZapisujem u spremnik."
                + "\n Broj zapisanih redova:"+duljina
                + "\n Trenutno vrijeme i datum:"+now
                +"\n Apsolutna putanja projekta:"+root);        
    }
}
