package by.trjava.ivankharytanovich.entity;

public enum TruckType {
    SMALL(20),
    STANDARD(40),
    LARGE(60);

    private int capacitySize;

    TruckType(int capacitySize) {
        this.capacitySize = capacitySize;
    }

    public int getCapacitySize() {
        return capacitySize;
    }
}
