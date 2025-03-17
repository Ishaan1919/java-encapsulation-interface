// Interface for GPS functionality
interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// Abstract class representing a Vehicle
abstract class Vehicles implements GPS {
    private String vehicleId;
    private String driverName;
    protected double ratePerKm;
    private String currentLocation;

    Vehicles(String vehicleId, String driverName, double ratePerKm, String location) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = location;
    }

    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Rate per KM: $" + ratePerKm);
        System.out.println("Current Location: " + currentLocation);
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }

    abstract double calculateFare(double distance);
}

// Subclass for Cars
class Cars extends Vehicles {
    Cars(String vehicleId, String driverName, double ratePerKm, String location) {
        super(vehicleId, driverName, ratePerKm, location);
    }

    @Override
    double calculateFare(double distance) {
        return distance * ratePerKm + 5; // Fixed base charge of $5
    }
}

// Subclass for Bikes
class Bikes extends Vehicles {
    Bikes(String vehicleId, String driverName, double ratePerKm, String location) {
        super(vehicleId, driverName, ratePerKm, location);
    }

    @Override
    double calculateFare(double distance) {
        return distance * ratePerKm; // No base charge for bikes
    }
}

// Subclass for Auto Rickshaws
class Auto extends Vehicles {
    Auto(String vehicleId, String driverName, double ratePerKm, String location) {
        super(vehicleId, driverName, ratePerKm, location);
    }

    @Override
    double calculateFare(double distance) {
        return (distance * ratePerKm) + 2; // Base charge of $2
    }
}

// Main class to simulate ride-hailing
public class RideHailingApplication {
    public static void main(String[] args) {
        Vehicles car = new Cars("C001", "John Doe", 2.5, "Downtown");
        Vehicles bike = new Bikes("B002", "Mike Ross", 1.0, "Uptown");
        Vehicles auto = new Auto("A003", "Sarah Lee", 1.5, "City Center");

        Vehicles[] rides = {car, bike, auto};

        double distance = 10; // Example ride distance (in km)

        for (Vehicles ride : rides) {
            ride.getVehicleDetails();
            System.out.println("Ride Fare for " + distance + " km: $" + ride.calculateFare(distance));
            System.out.println("---------------------------");
        }

//        Vehicle ID: C001
//        Driver Name: John Doe
//        Rate per KM: $2.5
//        Current Location: Downtown
//        Ride Fare for 10.0 km: $30.0
//                ---------------------------
//                Vehicle ID: B002
//        Driver Name: Mike Ross
//        Rate per KM: $1.0
//        Current Location: Uptown
//        Ride Fare for 10.0 km: $10.0
//                ---------------------------
//                Vehicle ID: A003
//        Driver Name: Sarah Lee
//        Rate per KM: $1.5
//        Current Location: City Center
//        Ride Fare for 10.0 km: $17.0
//                ---------------------------
    }
}
