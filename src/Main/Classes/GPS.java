package Main.Classes;

import java.io.*;

public class GPS implements ILoggable {

    String latitude;
    String longitude;

    public GPS(String[] coordinates) {
        this.latitude = coordinates[0];
        this.longitude = coordinates[1];
    }

    @Override
    public void writeCoordinates() {
        //print each line of the GPS_Log.txt file to the console for debugging
        System.out.println(this.latitude + ", " + this.longitude);
    }
}


