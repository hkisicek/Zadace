/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.FileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import org.foi.uzdiz.tof.SystemHandling.Actuator;
import org.foi.uzdiz.tof.SystemHandling.DeviceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author helena
 */
public class ActuatorAdapter extends FilesReader implements ReadingAdapter{
    
        Scanner scanner=null;
        int iterator=0;
        private String fileName;
    
        @Override
         public ArrayList<Actuator> parseFile(String filename) {
            ArrayList<Actuator> aktuators = new ArrayList<Actuator>();
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
            Actuator aktuator;
            while(scanner.hasNext()){
                line = scanner.nextLine();
                array = line.split(";", -1);
                aktuator =  deviceFactory.createActuator(
                        array[0],
                        Integer.valueOf(array[1]),
                        Integer.valueOf(array[2]),
                        Double.valueOf(array[3]),
                        Double.valueOf(array[4]),
                        array[5]
                );
                aktuators.add(aktuator);
            }
            return aktuators;
         }
    }
    

