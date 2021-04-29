package by.trjava.ivankharytanovich.entity;

import by.trjava.ivankharytanovich.exception.ResourceAccessException;
import by.trjava.ivankharytanovich.util.comparator.CargoTypeComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private final static int TERMINALS = 2;
    private final static Logger logger = LogManager.getLogger();
    private final static Semaphore semaphore = new Semaphore(TERMINALS, true);
    private List<Terminal> terminalList = new ArrayList<>();
    private Queue<Truck> trucksOutside = new PriorityQueue(new CargoTypeComparator());
    private Lock lock = new ReentrantLock();
    private Lock trucksOutsideLock = new ReentrantLock();

    private static class SingletonHolder {
        public static final LogisticBase INSTANCE = new LogisticBase();
    }

    public static LogisticBase getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LogisticBase() {
        for (int i = 0; i < TERMINALS; i++) {
            terminalList.add(new Terminal());
        }
    }

    public Terminal getTerminal(Truck truck) throws ResourceAccessException {
        Terminal terminal;
        try {
            semaphore.acquire();
            lock.lock();
            Optional<Terminal> optionalTerminal = terminalList.stream().filter(o -> !o.progressState()).findAny();
            terminal = optionalTerminal.get();
            trucksOutside.remove(truck);
            terminal.setInProgressState(true);
            logger.info("TRUCK " + truck + " IN TERMINAL");
            return terminal;
        } catch (InterruptedException e) {
            logger.error("UNKNOWN ERROR");
        } finally {
            lock.unlock();
        }
        throw new ResourceAccessException();
    }

    public void clearTerminal(Truck truck, Terminal terminal) {
        lock.lock();
        terminal.setInProgressState(false);
        logger.info("TRUCK " + truck + " LEFT TERMINAL");
        lock.unlock();
        semaphore.release();
    }

    public void moveTruckToTerr(Truck truck) {
        try {
            trucksOutsideLock.lock();
            trucksOutside.add(truck);
            logger.info("TRUCK " + truck + " IN QUEUE");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            logger.error("UNKNOWN ERROR");
        } finally {
            trucksOutsideLock.unlock();
        }
    }
}
