package org.foi.uzdiz.tof.SystemHandling;

public class DeviceFactory {

    public Sensor createSensor(
            String naziv,
            int tip,
            int vrsta,
            double min_vrijednost,
            double max_vrijednost,
            String komentar
    ) {
        return new Sensor(naziv,tip,vrsta,min_vrijednost,max_vrijednost,komentar);
    }
    public Actuator createActuator(
            String naziv,
            int tip,
            int vrsta,
            double min_vrijednost,
            double max_vrijednost,
            String komentar
    ) {
        return new Actuator(naziv,tip,vrsta,min_vrijednost,max_vrijednost,komentar);
    }
}
