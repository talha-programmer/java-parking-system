package parkingsystem.Enums;

public enum VehicleTypes {
    BIKE (0),
    RICKSHAW (1),
    CAR (2),
    HEAVY_VEHICLE (3);

    private final int value;
    private VehicleTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
