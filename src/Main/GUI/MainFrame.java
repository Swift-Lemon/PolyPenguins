package Main.GUI;

import Main.Classes.Animal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//best practise is not to do it this way, but to add JFrame as a class attribute
public class MainFrame extends JFrame {

    private DataEntryScreen dataEntryScreen;
    private ReportScreen reportScreen;

    private static ArrayList<Animal> animalList = new ArrayList<Animal>();

    public MainFrame() {

        //frame properties
        setTitle("Antarctic Animal Tracking");
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100,100,700,300);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new CardLayout(0,0));
        this.setContentPane(contentPane);

        dataEntryScreen = new DataEntryScreen();
        reportScreen = new ReportScreen();

        contentPane.add(dataEntryScreen);
        contentPane.add(reportScreen);


        //buttons and actionlisteners here to change panels
        JButton toDataEntry = reportScreen.getBackButton();
        toDataEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportScreen.setVisible(false);
                dataEntryScreen.setVisible(false);
            }
        });

        JButton toReportScreen = dataEntryScreen.getViewReportsButton();
        toReportScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataEntryScreen.setVisible(false);
                reportScreen.setVisible(true);
            }
        });
    }

    public static ArrayList<Animal> getAnimalList() {return animalList;}
}
