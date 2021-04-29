package by.trjava.ivankharytanovich.util.starter.impl;

import by.trjava.ivankharytanovich.entity.Truck;
import by.trjava.ivankharytanovich.util.starter.Starter;

import java.util.List;

public class TruckStarter implements Starter<Truck> {

    @Override
    public void start(List<Truck> trucks) {
        for (Truck truck : trucks) {
            Thread thread = new Thread(truck);
            thread.start();
        }
    }
}
