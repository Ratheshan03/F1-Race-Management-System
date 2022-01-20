package com.ratheshan;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Gui extends JFrame implements ActionListener {
    // INSTANCE VARIABLES
    Formula1ChampionshipManager M1 = new Formula1ChampionshipManager();
    JTable Dtable, Rtable, Stable;
    JTextField driverName;
    JButton searchBtn, generateRaceBtn, generateRandomPosBtn, sortDateBtn, dBtn, rBtn, sBtn;
    JLabel DLabel, RLabel, SLabel, Qns, Error, imgLabel1, imgLabel2, imgLabel3, imgLabel4;
    DefaultTableModel DModel, RModel, SModel;
    JScrollPane Dscroll, Rscroll, Sscroll;
    JFrame dMenu, rMenu, sMenu;
    ImageIcon icon1, icon2, icon3, icon4;

    /**
     * Default Constructor
     * Initially sets the java gui components
     * adds the main window components and calls the methods of main 3 tables.
     */
    public Gui() {
        // TABLE INITIALIZATION
        dMenu = new JFrame("FORMULA 1 - DRIVERS TABLE");
        dMenu.setLayout(new FlowLayout());
        dMenu.setName("FORMULA 1 - DRIVERS TABLE");
        dMenu.setSize(900,700);
        dMenu.setBackground(Color.lightGray);

        rMenu = new JFrame("FORMULA 1 - RACES TABLE");
        rMenu .setLayout(new FlowLayout());
        rMenu .setName("FORMULA 1 - RACES TABLE");
        rMenu .setSize(900,700);
        rMenu .setBackground(Color.lightGray);

        sMenu  = new JFrame("FORMULA 1 - DRIVER SEARCH TABLE");
        sMenu.setLayout(new FlowLayout());
        sMenu.setName("FORMULA 1 - DRIVER SEARCH TABLE");
        sMenu.setSize(900,700);
        sMenu.setBackground(Color.lightGray);

        // IMAGES ICONS
        icon1 = new ImageIcon("RaceF1.jpg");
        icon2 = new ImageIcon("RaceM1.jpg");
        icon3 = new ImageIcon("RaceR1.jpg");
        icon4 = new ImageIcon("F1.png");

        // GIVING LABEL VALUES
        imgLabel1 = new JLabel(icon1);
        imgLabel2 = new JLabel(icon2);
        imgLabel3 = new JLabel(icon3);
        imgLabel4 = new JLabel(icon4);
        DLabel = new JLabel("--------------------------------------------------------------------------------DRIVER TABLE--------------------------------------------------------------------------------");
        RLabel = new JLabel("---------------------------------------------------------------------------------RACE TABLE---------------------------------------------------------------------------------");
        SLabel = new JLabel("-----------------------------------------------------------------------------DRIVER SEARCH TABLE-----------------------------------------------------------------------------");
        Qns = new JLabel("Enter the Name of the Driver to Search: ");
        Error = new JLabel("");
        driverName = new JTextField(20);

        // CREATING MAIN OPTION BUTTON
        dBtn = new JButton("DRIVER TABLE");
        dBtn.setBackground(Color.orange);
        rBtn = new JButton("RACE TABLE");
        rBtn.setBackground(Color.orange);
        sBtn = new JButton("SEARCH TABLE");
        sBtn.setBackground(Color.orange);

        // CREATING BUTTONS
        generateRandomPosBtn = new JButton("GENERATE RANDOM POSITION");
        generateRaceBtn = new JButton("GENERATE RACE");
        sortDateBtn = new JButton("SORT BY DATE");
        searchBtn = new JButton("SEARCH");

        // ASSIGNING ACTION LISTENERS
        dBtn.addActionListener(this);
        rBtn.addActionListener(this);
        sBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        generateRaceBtn.addActionListener(this);
        generateRandomPosBtn.addActionListener(this);
        sortDateBtn.addActionListener(this);

        // ADDING COMPONENTS TO THE GUI
        add(imgLabel4);
        add(dBtn);
        add(rBtn);
        add(sBtn);

        // CALLING THE TABLE FUNCTIONS
        createF1DriverTable();
        createF1RacesTable();
        createF1SearchTable();
    }

    /**
     * @param e : Action listeners
     * Returns Calls all methods respective to the functionality
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dBtn){
            dTable();
        }else if (e.getSource() == rBtn) {
            rTable();
        }else if (e.getSource() == sBtn) {
            sTable();
        }else if (e.getSource() == searchBtn) {
            viewDriverRaces(driverName.getText());
        }else if (e.getSource() == generateRaceBtn) {
            M1.generateF1Race();
            resetTable();
        }else if (e.getSource() == generateRandomPosBtn) {
            M1.getStartPosition();
            resetTable();
        }else if (e.getSource() == sortDateBtn) {
            M1.sortRDate();
            resetTable();
        }
    }

    // DRIVER TABLE GUI
    public void dTable(){
        //CALLING FUNCTION
        dMenu.add(imgLabel1);
        dMenu.add(DLabel);
        dMenu.add(Dscroll);
        dMenu.setVisible(true);
        rMenu.setVisible(false);
        sMenu.setVisible(false);
    }

    // RACES TABLE GUI
    public void rTable(){
        //CALLING FUNCTION
        rMenu.add(imgLabel2);
        rMenu.add(RLabel);
        rMenu.add(Rscroll);
        rMenu.add(generateRaceBtn);
        rMenu.add(generateRandomPosBtn);
        rMenu.add(sortDateBtn);
        rMenu.setVisible(true);
        dMenu.setVisible(false);
        sMenu.setVisible(false);
    }

    // SEARCH TABLE GUI
    public void sTable(){
        //CALLING FUNCTION
        sMenu.add(imgLabel3);
        sMenu.add(SLabel);
        sMenu.add(Qns);
        sMenu.add(driverName);
        sMenu.add(searchBtn);
        sMenu.add(Error);
        sMenu.add(Sscroll);
        sMenu.setVisible(true);
        dMenu.setVisible(false);
        rMenu.setVisible(false);
    }

    /**
     * Returns none
     * Sets all 3 tables for formula 1 with table sorting
     */
    public void resetTable(){
        DModel.setRowCount(0);
        RModel.setRowCount(0);
        for (F1Teams f1Teams : M1.getF1Teams()) {
            Object[] dRow = {f1Teams.getDriver().getDriverName(), f1Teams.getDriver().getDriverLocation(), f1Teams.getDriver().getDriverTeam(), f1Teams.getDriver().getFirstPlace(), f1Teams.getDriver().getSecondPlace(), f1Teams.getDriver().getThirdPlace(),
                    f1Teams.getDriver().getNoOfRaces(), f1Teams.getDriver().getPoints()};
            DModel.addRow(dRow);
        }
        for (F1Races race : M1.getF1Races()) {
            Object[] sRow = {race.getDate(), race.getDrivers(), race.getPositions()};
            RModel.addRow(sRow);
        }
        try {
            M1.storeToFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Returns none
     * Sets the JTable for formula 1 drivers with table sorting
     */
    public void createF1DriverTable(){
        String[] columnNames ={"NAME","TEAM","LOCATION","1ST PLACE", "2ND PLACE", "3RD PLACE", "RACES", "POINTS"};
        DModel = new DefaultTableModel(columnNames, 0);
        Dtable = new JTable(DModel);
        Dtable.setPreferredScrollableViewportSize(new Dimension(750, 200));
        Dtable.setFillsViewportHeight(true);
        Dtable.setEnabled(false);
        Dtable.getTableHeader().setOpaque(false);
        Dtable.getTableHeader().setBackground(Color.ORANGE);
        Dscroll = new JScrollPane(Dtable);

        for (F1Teams f1Teams : M1.getF1Teams()) {
            Object[] driverRow = {f1Teams.getDriver().getDriverName(), f1Teams.getDriver().getDriverLocation(), f1Teams.getDriver().getDriverTeam(),
                    f1Teams.getDriver().getFirstPlace(), f1Teams.getDriver().getSecondPlace(), f1Teams.getDriver().getThirdPlace(), f1Teams.getDriver().getNoOfRaces(), f1Teams.getDriver().getPoints()};
            DModel.addRow(driverRow);
        }

        // TableRowSorter - Helps to integrate sorting ability within the JTable Headers
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(Dtable.getModel());
        Dtable.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(30);
        sortKeys.add(new RowSorter.SortKey(7, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }


    /**
     * Returns none
     * Sets the JTable for formula 1 Race List
     */
    public void createF1RacesTable(){
        String [] RColumns = {"Race Date", "Driver List", "Driver Positions"};
        RModel = new DefaultTableModel(RColumns,0);
        Rtable = new JTable(RModel);
        Rtable.setPreferredScrollableViewportSize(new Dimension(750, 200));
        Rtable.setFillsViewportHeight(true);
        Rtable.setEnabled(false);
        Rtable.getTableHeader().setOpaque(false);
        Rtable.getTableHeader().setBackground(Color.ORANGE);
        Rscroll = new JScrollPane(Rtable);

        for (F1Races race: M1.getF1Races()){
            Object[] raceRow = {race.getDate(), race.getDrivers(), race.getPositions()};
            RModel.addRow(raceRow);
        }
    }

    /**
     * Returns none
     * Sets the JTable for Driver Search with Date sorting
     */
    public void createF1SearchTable(){
        String[] sColumns = {"Name", "Date", "Position"};
        SModel = new DefaultTableModel(sColumns, 0);
        Stable = new JTable(SModel);
        Stable.setPreferredScrollableViewportSize(new Dimension(750, 200));
        Stable.setFillsViewportHeight(true);
        Stable.setEnabled(false);
        Stable.getTableHeader().setOpaque(false);
        Stable.getTableHeader().setBackground(Color.ORANGE);
        Sscroll = new JScrollPane(Stable);
        // TableRowSorter - Helps to integrate sorting ability within the JTable Headers.
        TableRowSorter<TableModel> sSorter = new TableRowSorter<>(Stable.getModel());
        Stable.setRowSorter(sSorter);

        ArrayList<RowSorter.SortKey> sSortKeys = new ArrayList<>(25);
        sSortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sSortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sSorter.setSortKeys(sSortKeys);
    }


    /**
     * @param name : Name of the driver
     * Returns the identified driver's Race stats
     */
    public void viewDriverRaces(String name){
        boolean found = false;
        SModel.setRowCount(0);
        for (F1Races race : M1.getF1Races()) {
            ArrayList list = race.getDrivers();
            if (list.contains(name)) {
                for (int i = 0; i < race.getDrivers().size(); i++) {
                    if (race.getDrivers().get(i).equals(name)) {
                        Object[] row = {name, race.getDate(), race.getPositions().get(i)};
                        SModel.addRow(row);
                    }
                }
                found = true;
                Error.setText("");
            }
        }
        if (!found) {
            Error.setText("No Driver Found!");
        }
    }
}

/*
 *  // REFERENCES
 *
 *  Text Book referred : CHAPTER 20 Graphical User Interface, Big Java, Cay Horstmann, fourth edition, Wiley, 2010
 *  Text Book Link : https://learning.oreilly.com/library/view/big-java-4th/9780470509487/pr01.html
 *
 *  YouTube Tutorial :  JAVA - How to Create a Table with JTable in Eclipse
 *  YouTube Tutorial link : https://www.youtube.com/watch?v=pybU3E-eKfw
 *
 **/
