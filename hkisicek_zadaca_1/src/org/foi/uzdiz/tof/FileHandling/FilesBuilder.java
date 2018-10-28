package org.foi.uzdiz.tof.FileHandling;

import org.foi.uzdiz.tof.SystemHandling.Actuator;
import org.foi.uzdiz.tof.SystemHandling.Location;
import org.foi.uzdiz.tof.SystemHandling.Sensor;

import java.util.ArrayList;

public class FilesBuilder {

    public ArrayList<Location> preapareLocations(String mjesta,String senzor,String aktuator) {
        LocationsAdapter locationsAdapter = new LocationsAdapter();
        ArrayList<Location> locations = locationsAdapter.parseFile(mjesta);

        SensorsAdapter sensorsAdapter = new SensorsAdapter();
        ArrayList<Sensor> sensors = sensorsAdapter.parseFile(senzor);

        ActuatorAdapter actuatorAdapter = new ActuatorAdapter();
        ArrayList<Actuator> actuators = actuatorAdapter.parseFile(aktuator);

        locations.forEach(location -> {
            
        });
        return locations;
    }
}
