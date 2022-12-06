package Main.Classes;

import java.util.ArrayList;

public class Penguin extends Animal {

    private double bloodPressure;

    public Penguin(String sex, int weight, ArrayList<GPS> coordinates, double bloodPressure) {
        super(sex, weight, coordinates);
        this.bloodPressure = bloodPressure;
    }

    @Override
    public String toString() {

        StringBuilder printCoordinates = new StringBuilder();
        for (GPS coordinate : this.coordinates) {
            printCoordinates.append(coordinate.latitude + ", " + coordinate.longitude + "\n");
        }

        return "Species: Penguin" + super.toString() + "\nBlood Pressure: " + this.bloodPressure + "\n" + printCoordinates;
    }
}
