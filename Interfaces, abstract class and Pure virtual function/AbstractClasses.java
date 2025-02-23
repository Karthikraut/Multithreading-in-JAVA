abstract class Animal {
    String name;  // Corrected the type to 'String' (uppercase)
    abstract void activity();  
}

class Lion extends Animal{
    @Override
    void activity() {
        System.out.println("Lion Runs with 4 legs.");
    }
}

class Pigeon extends Animal{
    @Override
    void activity(){
        System.out.println("Pigeon Flyes with wings");
    }
}

public class AbstractClasses {
    public static void main(String[] args) {
        // You can create objects here and interact with them
        Pigeon p = new Pigeon();
        p.activity();
    }
}
