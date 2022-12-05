package Main.Classes;

import java.util.ArrayList;

public class SeaLion extends Animal{

    private int spots;

    public SeaLion(String sex, int weight, ArrayList<GPS> coordinates, int spots) {
        super(sex, weight, coordinates);
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "Species: Sea Lion" + super.toString() + "\n# of Spots: " + this.spots + "\n";
    }
}
