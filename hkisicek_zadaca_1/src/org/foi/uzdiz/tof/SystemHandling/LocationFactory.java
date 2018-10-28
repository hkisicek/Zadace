package org.foi.uzdiz.tof.SystemHandling;

import java.util.List;

public class LocationFactory {
    
    List<Sensor> sensors;
    List<Actuator> actuators;
    
    public Location create(
            String naziv,
            int tip,
            int broj_senzora,
            int broj_aktuatora
    ) {
        return new Location(naziv,tip,broj_senzora,broj_aktuatora);
    }
}
