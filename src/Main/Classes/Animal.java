package Main.Classes;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class Animal implements ILoggable {

    String sex;
    int weight;
    ArrayList<GPS> coordinates;

    public Animal(String sex, int weight, ArrayList<GPS> coordinates) {
        this.sex = sex;
        this.weight = weight;
        this.coordinates = coordinates;
    }

    public ArrayList<GPS> getCoordinates() {return this.coordinates;}

    public String toString() {


        return "\nSex: " + this.sex + "\nWeight: " + this.weight;
    }

    @Override
    public void writeCoordinates() {

        StringBuilder text = new StringBuilder();
        for (GPS coordinate : this.coordinates) {
            text.append(coordinate.latitude + ", " + coordinate.longitude + "\n");
        }

        String targetFilePath = "src/Main/Resources/GPS_Log.txt";

        try {
            writeToFile(text.toString(), targetFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String text, String targetFilePath) throws IOException {
        Path targetPath = Paths.get(targetFilePath);
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        Files.write(targetPath, bytes, StandardOpenOption.APPEND);
        //could get replaced with (but I think we're supposed to use bytes):
        //Files.writeString(targetPath, text, StandardOpenOption.CREATE);
    }
}
