package Main.GUI;

import Main.Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DataEntryScreen extends JPanel {

    private JComboBox<String> animalSpecies;
    private JComboBox<String> animalSex;
    private JTextField animalWeight;
    private JButton addEntry;

    private JLabel special;
    private JTextField animalSpecialText;
    private JComboBox animalSpecialCombo;

    private JTextField inputGPS;
    private JButton addGPSButton;
    private JTextArea outputGPS;

    private JButton viewReports;

    //arrays for combobox options:
    private final String[] speciesOptions = new String[] {"-----", "Penguin", "Sea Lion", "Walrus"};
    private final String[] sexOptions = new String[] {"-----", "Male", "Female"};
    private final String[] dentalOptions = new String[] {"-----", "Poor", "Average", "Good"};

    public DataEntryScreen() {

        Color backgroundColor = new Color(193, 206, 210);

        //set panel properties
        setLayout(null); //this is null to use absolute positioning on the elements within the pane
        setVisible(true);
        setBackground(backgroundColor);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Animal information (left side):
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        //create gui elements
        JLabel animalObservedTitle = new JLabel("Animal Observed:");
        animalObservedTitle.setBounds(20, 30, 200, 20);

        JLabel animal = new JLabel("Animal");
        animal.setBounds(20, 60, 120, 20);
        animalSpecies = new JComboBox<>(speciesOptions);
        animalSpecies.setBounds(130, 60, 120, 20);

        JLabel sex = new JLabel("Sex");
        sex.setBounds(20, 90, 120, 20);
        animalSex = new JComboBox<>(sexOptions);
        animalSex.setBounds(130, 90, 120, 20);

        JLabel weight = new JLabel("Weight in Kg");
        weight.setBounds(20, 120, 120, 20);
        animalWeight = new JTextField("");
        animalWeight.setBounds(130, 120, 120, 20);

        addEntry = new JButton("Add Entry");
        addEntry.setBounds(50, 180, 120, 20);
        //this button should only be enabled when a species has been selected
        addEntry.setEnabled(false);

        //below code handles unique input fields depending on the species
        special = new JLabel();
        special.setBounds(20, 150, 120, 20);
        special.setVisible(false);

        animalSpecialText = new JTextField("");
        animalSpecialText.setBounds(130, 150, 120, 20);
        animalSpecialText.setVisible(false);

        animalSpecialCombo = new JComboBox<>();
        animalSpecialCombo.setBounds(130, 150, 120, 20);
        animalSpecialCombo.setVisible(false);

        animalSpecies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speciesSelectMethod();
            }
        });

        add(animalObservedTitle);
        add(animal);
        add(animalSpecies);
        add(sex);
        add(animalSex);
        add(weight);
        add(animalWeight);
        add(special);
        add(animalSpecialText);
        add(animalSpecialCombo);
        add(addEntry);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //GPS information (left side) (and the reports page button)
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        JLabel GPSName = new JLabel("GPS Coordinates: (-)##.####### (-)(## or ###).#######");
        GPSName.setBounds(280, 30, 400, 20);

        inputGPS = new JTextField("");
        inputGPS.setBounds(280, 60, 300, 20);

        addGPSButton = new JButton("Add GPS");
        addGPSButton.setBounds(590, 60, 90, 20);

        outputGPS = new JTextArea("");
        outputGPS.setEnabled(false);
        outputGPS.setLineWrap(true);
        outputGPS.setBounds(280, 90, 400, 110);

        JScrollPane scroll = new JScrollPane(outputGPS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        viewReports = new JButton("View Reports");
        viewReports.setBounds(540, 225, 140, 20);

        addGPSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add validation to check if the text in inputGPS matches regex
                if (validateGPSInput(inputGPS)) {
                    outputGPS.setText(inputGPS.getText() + "\n" + outputGPS.getText());
                    inputGPS.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            """
                                    Invalid GPS Format:
                                    Latitude values range from -90 to 90.
                                    Longitude values range from -180 to 180.
                                    Both values must have 7 digits after the decimal.
                                    Separate latitude and longitude values with a space.
                                    (-)##.####### (-)(## or ###).#######
                                    """,
                            "Warning",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validateEntry()) {

                    createNewAnimal();
                    resetOptions();
                    resetSpecial();
                    animalSpecies.setSelectedIndex(0);
                }
            }
        });

        add(GPSName);
        add(inputGPS);
        add(addGPSButton);
        add(outputGPS);
        add(scroll);
        add(viewReports);
    }

    private void speciesSelectMethod() {
        Object selected = animalSpecies.getSelectedItem();

        if (selected == "Penguin") {
            //only for penguins:
            resetSpecial();
            special.setText("Blood Pressure");
            special.setVisible(true);
            animalSpecialText.setVisible(true);
            addEntry.setEnabled(true);

        } else if (selected == "Sea Lion") {
            //only for Sea Lion:
            resetSpecial();
            special.setText("# of Spots");
            special.setVisible(true);
            animalSpecialText.setVisible(true);
            addEntry.setEnabled(true);

        } else if (selected == "Walrus") {
            //only for walrus:
            resetSpecial();

            DefaultComboBoxModel<String> walrusModel = new DefaultComboBoxModel<>(dentalOptions);

            special.setText("Dental Health");
            special.setVisible(true);
            animalSpecialCombo.setModel(walrusModel);
            animalSpecialCombo.setVisible(true);
            addEntry.setEnabled(true);

        } else {
            //this should only happen in blank option is picked
            resetOptions();
        }
    }

    private boolean validateEntry() {

        boolean validated = false;

        if (animalSex.getSelectedItem() == sexOptions[0]){
            //check if they've selected the blank option.
            //send a pop-up notifying how to fix their entry
            comboboxValidationError("Sex");

        } else if (!validateInt(animalWeight)) {
            //send a pop-up notifying how to fix their entry
            intValidationError( "Weight");

        } else if (outputGPS.getText().matches("^$")) {
            JOptionPane.showMessageDialog(null,
                    """
                            At least one GPS location must be entered.
                            """,
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);

        } else {
            //so long as all common input it acceptable, check the
            //values unique to each species:
            if (animalSpecies.getSelectedItem() == "Penguin") {
                if (!validateDouble(animalSpecialText)) {
                    //send a pop-up notifying how to fix their entry
                    doubleValidationError("Blood Pressure");
                } else {
                    //passed all validation:
                    validated = true;
                }
            } else if (animalSpecies.getSelectedItem() == "Sea Lion") {
                if (!validateInt(animalSpecialText)) {
                    //send a pop-up notifying how to fix their entry
                    intValidationError("Spots");
                } else {
                    //passed all validation:
                    validated = true;
                }
            } else if (animalSpecies.getSelectedItem() == "Walrus") {
                if (animalSpecialCombo.getSelectedItem() == dentalOptions[0]) {
                    //send a pop-up notifying how to fix their entry
                    comboboxValidationError("Dental Health");
                } else {
                    //passed all validation:
                    validated = true;
                }
            }
        }

        return validated;
    }

    private void createNewAnimal() {

        ArrayList<Animal> animals = MainFrame.getAnimalList();

        String sex = String.valueOf(animalSex.getSelectedItem());
        int weight = Integer.parseInt(animalWeight.getText());
        ArrayList<GPS> animalCoordinates = new ArrayList<>();

        for (String line : outputGPS.getText().split("\\n")) {
            String[] splitCoordinates = line.split(" ");
            animalCoordinates.add(new GPS(splitCoordinates));
        }

        if (animalSpecies.getSelectedItem() == "Penguin") {

            double bloodPressure = Double.parseDouble(animalSpecialText.getText());
            Animal animal = new Penguin(sex, weight, animalCoordinates, bloodPressure);

            for (GPS coordinates : animal.getCoordinates()) {
                coordinates.writeCoordinates();
            }

            animals.add(animal);
            confirmationMessage("Penguin");
            //coordinates of the animal get added to the GPS_Log.txt file
            animal.writeCoordinates();

        } else if (animalSpecies.getSelectedItem() == "Sea Lion") {

            int spots = Integer.parseInt(animalSpecialText.getText());
            Animal animal = new SeaLion(sex, weight, animalCoordinates, spots);
            for (GPS coordinates : animal.getCoordinates()) {
                coordinates.writeCoordinates();
            }

            animals.add(animal);
            confirmationMessage("Sea Lion");
            //coordinates of the animal get added to the GPS_Log.txt file
            animal.writeCoordinates();

        } else if (animalSpecies.getSelectedItem() == "Walrus") {

            String dentalHealth = String.valueOf(animalSpecialCombo.getSelectedItem());
            Animal animal = new Walrus(sex, weight, animalCoordinates, dentalHealth);
            for (GPS coordinates : animal.getCoordinates()) {
                coordinates.writeCoordinates();
            }

            animals.add(animal);
            confirmationMessage("Walrus");
            //coordinates of the animal get added to the GPS_Log.txt file
            animal.writeCoordinates();
        }
    }

    private boolean validateGPSInput(JTextField input) {
        //since .matches() returns a boolean anyway, I don't need an if else for the return values
        return input.getText().matches("^(-?([0-9]|[1-8][0-9]|90)([.]\\d{7})) \\s*(-?([0-9]{1,2}|1[0-7][0-9]|180)([.]\\d{7}))$");
    }

    private boolean validateInt(JTextField input) {
         if (input.getText().matches("^$")) {
            return false;
        } else return (input.getText().matches("^+\\d*$")); //accepts any positive integer, but doesn't check for null
    }

    private void intValidationError(String field) {
        JOptionPane.showMessageDialog(null, String.format(
                """
                        [%s]:Invalid input:
                        Enter a whole number greater than 0.
                        Please do not include spaces.
                        """, field),
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateDouble(JTextField input) {
        if (input.getText().matches("^$")) {
            return false;
        } else return (input.getText().matches("^+?\\d+\\.\\d+")); //accepts any positive number with decimals
    }

    private void doubleValidationError(String field) {
        JOptionPane.showMessageDialog(null, String.format(
                    """
                            [%s]:Invalid input:
                            Please enter a positive number with a decimal.
                            """, field),
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    private void comboboxValidationError(String field) {
        JOptionPane.showMessageDialog(null, String.format(
                        """
                            [%s]:Invalid input:
                            Please select an option.
                            """, field),
                "Warning",
                JOptionPane.ERROR_MESSAGE);
    }

    private void confirmationMessage(String species) {
        JOptionPane.showMessageDialog(null, String.format(
                        """
                        %s saved as new entry.
                        """, species),
                "Entry Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetOptions() {
        animalSex.setSelectedIndex(0);
        animalWeight.setText("");
        outputGPS.setText("");
        addEntry.setEnabled(false);
    }

    private void resetSpecial() {
        special.setVisible(false);
        animalSpecialText.setVisible(false);
        animalSpecialText.setText("");
        animalSpecialCombo.setVisible(false);
    }

    public JButton getViewReportsButton() {
        return this.viewReports;
    }

}
