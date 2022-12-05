package Main;

import Main.Classes.GPS;
import Main.GUI.MainFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame characterCreation = new MainFrame();
                    characterCreation.setVisible(true);
                } catch (Exception e) {
                    //output info about the error
                    e.printStackTrace();
                }
            }
        });
    }
}