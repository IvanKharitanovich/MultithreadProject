package by.trjava.ivankharytanovich.util.parser.impl;

import by.trjava.ivankharytanovich.entity.CargoType;
import by.trjava.ivankharytanovich.entity.Truck;
import by.trjava.ivankharytanovich.entity.TruckType;
import by.trjava.ivankharytanovich.exception.DataException;
import by.trjava.ivankharytanovich.util.parser.Parser;

public class TruckParser implements Parser<Truck> {
    private final static int TRUCK_TYPE_HOLDS = 0;
    private final static int CARGO_TYPE_HOLDS = 1;
    private final static int CARGO_INSIDE_HOLDS = 2;

    @Override
    public Truck parse(String data) throws DataException {
        String[] splittedData = data.split(":");
        TruckType truckType = TruckType.valueOf(splittedData[TRUCK_TYPE_HOLDS]);
        CargoType cargoType = CargoType.valueOf(splittedData[CARGO_TYPE_HOLDS]);
        int cargoInside = Integer.parseInt(splittedData[CARGO_INSIDE_HOLDS]);
        return new Truck(truckType, cargoType, cargoInside);
    }
}
