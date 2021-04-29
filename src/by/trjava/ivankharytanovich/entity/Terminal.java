package by.trjava.ivankharytanovich.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Terminal {
    private final static Logger logger = LogManager.getLogger();
    private final static int MAX_CARGO_MOVEMENT = 10;
    private boolean inProgressState;

    public Terminal() {
    }

    public boolean progressState() {
        return inProgressState;
    }

    public void setInProgressState(Boolean inProgressState) {
        this.inProgressState = inProgressState;
    }

    public void workWithCargo(Truck truck) {
        if (truck.isLoaded()) {
            while (truck.getCargoInside() > 0) {
                truck.unload(MAX_CARGO_MOVEMENT);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error("UNKNOWN ERROR");
                }
            }
            logger.info("TRUCK " + truck.getId() + " UNLOAD CARGO " + truck.getCargoType());
        } else {
            while (truck.getCargoInside() < truck.getTruckType().getCapacitySize()) {
                truck.load(MAX_CARGO_MOVEMENT);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error("UNKNOWN ERROR");
                }
            }
            logger.info("TRUCK " + truck.getId() + " LOADED CARGO " + truck.getCargoType());
        }
    }
}
