/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.foi.uzdiz.tof.SystemHandling.DeviceFactory;
import org.foi.uzdiz.tof.SystemHandling.Sensor;

/**
 *
 * @author helena
 */
public class SensorsAdapter extends FilesReader implements ReadingAdapter{

        Scanner scanner=null;
        int iterator=0;
        private String fileName;
        
        @Override
        public ArrayList<Sensor> parseFile(String filename) {
        ArrayList<Sensor> senzors = new ArrayList<Sensor>();
        this.fileName=filename;
        File file=new File(filename);
        try
        {
            scanner=new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println(filename+" cannot be found!");
        }
        DeviceFactory deviceFactory = new DeviceFactory();
        String line = scanner.nextLine();
        String[] array = line.split(";", -1);
        Sensor senzor;
        while(scanner.hasNext()){
            line = scanner.nextLine();
            array = line.split(";", -1);
            senzor =  deviceFactory.createSensor(
                    array[0],
                    Integer.valueOf(array[1]),
                    Integer.valueOf(array[2]),
                    Double.valueOf(array[3]),
                    Double.valueOf(array[4]),
                    array[5]
            );
            senzors.add(senzor);
        }
        return senzors;
    }
}
