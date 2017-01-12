/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Home Screen UIs
 */
public class HomeScreen extends JPanel {

    private static JLabel backgroundImageLabel;
    private static JLabel productNameLabel;
    private static JLabel productNameAbbreviationLabel;
    private static JLabel softwareNameLabel;
    private static JButton registerUserButton;

    public HomeScreen() {
        setSize(800, 600);
        setLocation(0, 0);
        setLayout(null);
        setBackground(Color.BLACK);

        registerUserButton = new JButton("Register User");
        backgroundImageLabel = new JLabel(new ImageIcon("C:\\Users\\HACKER\\"
                + "Documents\\NetBeansProjects\\FaceDetectionBiometricsSoft\\src"
                + "\\appImages\\bgImage.jpg"));
        productNameLabel = new JLabel("Aggregate Face Detection and Recognition ");
        productNameAbbreviationLabel = new JLabel("A.F.D.R.S");
        softwareNameLabel = new JLabel("Software");
        
        backgroundImageLabel.setBounds(0, 0, 800, 600);
        productNameAbbreviationLabel.setBounds(250, 100, 600, 75);
        productNameLabel.setBounds(120, 165, 600, 40);
        softwareNameLabel.setBounds(330,210,600,40);
        registerUserButton.setBounds(310,300,150,30);
        
        productNameAbbreviationLabel.setForeground(Color.WHITE);
        productNameAbbreviationLabel.setFont( new Font("Calibri",Font.BOLD,70));
        productNameLabel.setForeground(Color.WHITE);
        productNameLabel.setFont( new Font("Calibri",Font.BOLD,30));
        softwareNameLabel.setForeground(Color.WHITE);
        softwareNameLabel.setFont( new Font("Calibri",Font.BOLD,30));
        registerUserButton.setBackground(Color.decode("#1e90ff"));
        registerUserButton.setForeground(Color.WHITE);
        registerUserButton.setBorder( new LineBorder(Color.decode("#1e90ff")));
        
        registerUserButton.addActionListener(e -> FaceDetectionUISkeleton.replaceRightComponent( 
                new FaceDetectionUI()));
        /**
         * Add all components to the screen
         */
        
        
        add(productNameAbbreviationLabel);
        add(productNameLabel);
        add(softwareNameLabel);
        add(registerUserButton);
        add(backgroundImageLabel);

    }

}
