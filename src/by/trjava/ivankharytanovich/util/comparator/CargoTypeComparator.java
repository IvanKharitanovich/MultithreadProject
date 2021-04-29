package by.trjava.ivankharytanovich.util.comparator;

import by.trjava.ivankharytanovich.entity.CargoType;
import by.trjava.ivankharytanovich.entity.Truck;

import java.util.Comparator;

public class CargoTypeComparator implements Comparator<Truck> {
    @Override
    public int compare(Truck o1, Truck o2) {
        if ((CargoType.PERISHABLE.equals(o1.getCargoType()))
                && (!CargoType.PERISHABLE.equals(o2.getCargoType()))) {
            return -1;
        } else if ((!CargoType.PERISHABLE.equals(o1.getCargoType()))
                && (CargoType.PERISHABLE.equals(o2.getCargoType()))) {
            return 1;
        } else {
            return 0;
        }
    }
}
