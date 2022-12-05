package Main.GUI;

import Main.Classes.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReportScreen extends JPanel {

    private JTextArea reportText;
    private JButton showNewEntries;
    private JButton showGPSLogs;
    private JButton backToInput;

    public ReportScreen() {

        Color backgroundColor = new Color(193, 206, 210);

        //set panel properties
        setLayout(null);
        setVisible(true);
        setBackground(backgroundColor);

        JLabel reports = new JLabel("Reports: ");
        reports.setBounds(20, 10, 120, 20);

        reportText = new JTextArea("");
        reportText.setLineWrap(true);
        reportText.setEditable(false);

        JScrollPane scroll = new JScrollPane(reportText);
        scroll.setBounds(20, 40, 650, 170);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        showNewEntries = new JButton("Show New Entries");
        showNewEntries.setBounds(100, 220, 170, 30);
        showNewEntries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newEntriesMethod();
            }
        });

        showGPSLogs = new JButton("Show GPS Logs");
        showGPSLogs.setBounds(265, 220, 170, 30);
        showGPSLogs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readLogMethod(reportText);
            }
        });

        backToInput = new JButton("Back");
        backToInput.setBounds(430, 220, 170, 30);

        add(reports);
        add(scroll);
        add(showNewEntries);
        add(showGPSLogs);
        add(backToInput);
    }

    private void newEntriesMethod() {
        //this should display all entries this session
        resetText();
        ArrayList<Animal> animals = MainFrame.getAnimalList();
        for (Animal animal : animals) {
            reportText.append(animal.toString() + "\n-------------------------\n");
        }
    }

    private void readLogMethod(JTextArea area) {

        resetText();
        try {
            BufferedReader br = new BufferedReader(new FileReader( "src/Main/Resources/GPS_Log.txt"));
            String line = br.readLine();

            while (line != null) {
                area.append(line + "\n");
                line = br.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void resetText() {
        reportText.setText("");
    }

    public JButton getBackButton() {
        return this.backToInput;
    }
}
