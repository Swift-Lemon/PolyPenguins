package Main.Classes;

import java.io.BufferedReader;

public interface ILoggable {

    //animals will implement this to write to the file,
    //gps will use this to write to the console

    void writeCoordinates();

}

//    BufferedReader reader;
//
//        try {
//                reader = new BufferedReader(new FileReader("/Main/Resources/GPS_Log.txt.txt"));
//                String line = reader.readLine();
//                while (line != null) {
//                System.out.println(line);
//                //move to next line
//                line = reader.readLine();
//                }
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
