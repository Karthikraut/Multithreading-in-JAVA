interface Bicycle {
    void applyBrakes();
    void speedUp(int increment);
}
// A concrete class implementing the Bicycle interface
class MountainBike implements Bicycle {
    private int speed;
    private int gear;
    public MountainBike(int startSpeed, int startGear) {
        this.speed = startSpeed;
        this.gear = startGear;
    }
    @Override
    public void applyBrakes() {
        speed -= 5;
        System.out.println("Applying brakes. Speed reduced to " + speed);
    }
    @Override
    public void speedUp(int increment) {
        speed += increment;
        System.out.println("Speeding up. Speed increased to " + speed);
    }
}
// Another concrete class implementing the Bicycle interface
class RoadBike implements Bicycle {
    private int speed;
    public RoadBike(int startSpeed) {
        this.speed = startSpeed;
    }
    @Override
    public void applyBrakes() {
        speed -= 3; // Road bikes might have more efficient brakes
        System.out.println("Road bike brakes applied. Speed reduced to " + speed);
    }
    @Override
    public void speedUp(int increment) {
        speed += increment * 2; // Road bikes are faster!
        System.out.println("Road bike speeding up. Speed increased to " + speed);
    }
}
public class InterfaceExample {
    public static void main(String[] args) {
        MountainBike mountainBike = new MountainBike(10, 1);
        RoadBike roadBike = new RoadBike(15);
        mountainBike.speedUp(5);
        mountainBike.applyBrakes();
        roadBike.speedUp(10);
        roadBike.applyBrakes();
        // Using the interface as a reference type
        Bicycle myBike = new MountainBike(5, 2); // Valid: MountainBike *is a* Bicycle
        myBike.speedUp(3);
        myBike.applyBrakes();
    }
}
