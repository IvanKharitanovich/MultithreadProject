package by.trjava.ivankharytanovich.entity.factory;

import by.trjava.ivankharytanovich.entity.CargoType;
import by.trjava.ivankharytanovich.entity.Truck;
import by.trjava.ivankharytanovich.entity.TruckType;

public class EntityFactory {
    private static class SingletonHolder {
        public final static EntityFactory INSTANCE = new EntityFactory();
    }

    public static EntityFactory getInstance() {
        return EntityFactory.getInstance();
    }

    private EntityFactory() {
    }

    public Truck getTruck(TruckType truckType, CargoType cargoType, int cargoInside) {
        return new Truck(truckType, cargoType, cargoInside);
    }
}
