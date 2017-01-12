/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author HACKER
 */
public class Users extends JPanel {

    private static JTable usersTable;
    private static JPanel topSectionPanel;
    private static JPanel searchPanel;
    private static JScrollPane scrollPane;
    private static JButton backButton;
    private static JButton doneButton;
    private static JButton refereshButton;
    private static JLabel usersLabel;
    private static JLabel searchLabel;
    private static JTextField searchField;
    private static JLabel filterbyLabel;
    private static JComboBox filterComboBox;

    public Users() {

        setSize(800, 600);
        setLocation(0, 0);
        setLayout(null);
        setBackground(Color.WHITE);

        String[] columns = {"User ID", "First Name", "Last Name", "Image URL"};
        String[][] temp = {
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""}};

        String[] filterOptions = {
            "--Select a Filter--",
            "User ID",
            "First Name",
            "Last Name",
            "Image URL"};

        searchField = new JTextField();
        searchLabel = new JLabel("Search");
        filterbyLabel = new JLabel("Filter by:");
        filterComboBox = new JComboBox<>(filterOptions);
        topSectionPanel = new JPanel(null);
        searchPanel = new JPanel(null);
        scrollPane = new JScrollPane();
        usersTable = new JTable(temp, columns);
        backButton = new JButton("BACK");
        doneButton = new JButton("DONE");
        refereshButton = new JButton("REFRESH");
        usersLabel = new JLabel("Users");

        topSectionPanel.setBounds(50, 0, 600, 100);
        usersLabel.setBounds(20, 60, 200, 30);
        searchPanel.setBounds(50, 130, 600, 65);

        scrollPane.setBounds(50, 205, 600, 330);
        backButton.setBounds(180, 540, 100, 25);
        doneButton.setBounds(300, 540, 100, 25);
        refereshButton.setBounds(420, 540, 100, 25);

        searchLabel.setBounds(5, 5, 100, 25);
        searchField.setBounds(60, 5, 150, 30);
        filterbyLabel.setBounds(220, 5, 100, 25);
        filterComboBox.setBounds(290, 5, 200, 30);

        scrollPane.getViewport().add(usersTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        filterComboBox.setBackground(Color.WHITE);
        filterComboBox.setSelectedItem("--Select a Filter--");
        searchField.setBorder(new LineBorder(Color.decode("#c0c0c0")));
        searchPanel.setBackground(Color.decode("#1e90ff"));
        usersLabel.setForeground(Color.WHITE);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        filterbyLabel.setForeground(Color.WHITE);
        filterbyLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        usersLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        topSectionPanel.setBackground(Color.decode("#1e90ff"));
        topSectionPanel.setBorder(new LineBorder(Color.decode("#1e90ff")));
        topSectionPanel.add(usersLabel);

        usersTable.setSelectionBackground(Color.decode("#1e90ff"));
        usersTable.setSelectionForeground(Color.WHITE);
        usersTable.setRowHeight(25);

        List<JButton> buttons = Arrays.asList(
                backButton, doneButton, refereshButton
        );

        setButtonProperties(buttons);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(filterbyLabel);
        searchPanel.add(filterComboBox);

        add(topSectionPanel);
        add(searchPanel);
        add(scrollPane);

    }

    public final void setButtonProperties(List<JButton> buttons) {
        buttons.stream()
                .forEach(e -> {
                    e.setBackground(Color.decode("#1e90ff"));
                    e.setForeground(Color.WHITE);
                    e.setBorder(new LineBorder(Color.decode("#1e90ff")));
                    add(e);
                });
    }
}
