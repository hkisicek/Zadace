/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.tof.FileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import org.foi.uzdiz.tof.SystemHandling.Location;
import org.foi.uzdiz.tof.SystemHandling.LocationFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author helena
 */
public class LocationsAdapter extends  FilesReader implements ReadingAdapter{
    
        Scanner scanner=null;
        int iterator=0;
        private String fileName;
        
        @Override
        public ArrayList<Location> parseFile(String filename) {
       ArrayList<Location> locations = new ArrayList<Location>();
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
        LocationFactory locationFactory = new LocationFactory();
        String line = scanner.nextLine();
        String[] array = line.split(";", -1);
        Location location;
        while(scanner.hasNext()){
            line = scanner.nextLine();
            array = line.split(";", -1);
            location = locationFactory.create(
                    array[0],
                    Integer.valueOf(array[1]),
                    Integer.valueOf(array[2]),
                    Integer.valueOf(array[3])
            );
            locations.add(location);
        }
        return locations;
    }
}
