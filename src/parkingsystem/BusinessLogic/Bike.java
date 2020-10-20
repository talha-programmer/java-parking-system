package parkingsystem.BusinessLogic;

public class Bike extends Vehicle{
    private static float ratePerHour;

    public static float getRatePerHour() {
        return ratePerHour;
    }

    public static void setRatePerHour(float ratePerHour) {
        Bike.ratePerHour = ratePerHour;
    }
}
