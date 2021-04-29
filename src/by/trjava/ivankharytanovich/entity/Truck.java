package by.trjava.ivankharytanovich.entity;

import by.trjava.ivankharytanovich.exception.ResourceAccessException;
import by.trjava.ivankharytanovich.util.generator.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Truck implements Runnable {
    private final static Logger logger = LogManager.getLogger();
    private long id;
    private TruckType truckType;
    private CargoType cargoType;
    private int cargoInside;

    public Truck() {
    }

    public Truck(TruckType truckType, CargoType cargoType,int cargoInside) {
        this.id = IdGenerator.getLongId();
        this.truckType = truckType;
        this.cargoType = cargoType;
        this.cargoInside = cargoInside;
    }

    @Override
    public void run() {
        LogisticBase logisticBase = LogisticBase.getInstance();
        logisticBase.moveTruckToTerr(this);

        try {
            Terminal terminal;
            terminal = logisticBase.getTerminal(this);
            terminal.workWithCargo(this);
            logisticBase.clearTerminal(this, terminal);
        } catch (ResourceAccessException e) {
            logger.error("UNKNOWN ERROR");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public int getCargoInside() {
        return cargoInside;
    }

    public void setCargoInside(int cargoInside) {
        this.cargoInside = cargoInside;
    }

    public void load(int cargo) {
        cargoInside += cargo;
    }

    public void unload(int cargo) {
        cargoInside -= cargo;
    }

    public boolean isLoaded() {
        return (cargoInside == truckType.getCapacitySize());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (id != truck.id) return false;
        if (cargoInside != truck.cargoInside) return false;
        if (truckType != truck.truckType) return false;
        return cargoType == truck.cargoType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (truckType != null ? truckType.hashCode() : 0);
        result = 31 * result + (cargoType != null ? cargoType.hashCode() : 0);
        result = 31 * result + cargoInside;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Truck {");
        stringBuilder.append("id = ").append(id);
        stringBuilder.append(", truckType = ").append(truckType);
        stringBuilder.append(", cargoType = ").append(cargoType);
        stringBuilder.append(", cargoInside = ").append(cargoInside);
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}
