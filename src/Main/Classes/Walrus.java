package Main.Classes;

import java.util.ArrayList;

public class Walrus extends Animal{

    private String dentalHealth; //this should really be an enum (good, average, poor) but string was listed in the assignment specs

    public Walrus(String sex, int weight, ArrayList<GPS> coordinates, String dentalHealth) {
        super(sex, weight, coordinates);
        this.dentalHealth = dentalHealth;
    }

    @Override
    public String toString() {
        return "Species: Walrus" + super.toString() + "\nDental Health: " + this.dentalHealth + "\n";
    }
}
