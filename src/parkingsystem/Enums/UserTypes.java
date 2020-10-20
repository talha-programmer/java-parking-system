package parkingsystem.Enums;

public enum UserTypes {
    OWNER(0), WORKER(1);

    private final int value;
    private UserTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
