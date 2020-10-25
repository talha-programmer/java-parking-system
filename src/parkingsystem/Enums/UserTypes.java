package parkingsystem.Enums;

public enum UserTypes {
    OWNER("Owner",0), WORKER("Worker", 1);

    private final String name;
    private final int value;
    private UserTypes(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public String getName(){ return name; }
}
