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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author HACKER
 */
public class MenuPanel extends JPanel {

    private static JLabel productImageLabel;
    private static JButton registerUserButton;
    private static JButton SessionLogs;
    private static JButton viewUsersButton;
    private static JButton helpButton;
    private static JButton settingsButton;
    private static JButton homeButton;
    

    public MenuPanel() {
        setBounds(0, 0, 180, 580);
        setLayout(null);
        setBackground(Color.decode("#f5f5f5"));
        
        homeButton = new JButton("Home");
        registerUserButton = new JButton("Register User");
        SessionLogs = new JButton("");
        viewUsersButton = new JButton("");
        helpButton = new JButton("");
        settingsButton = new JButton("");
        productImageLabel = new JLabel(new ImageIcon("C:\\Users\\HACKER\\Documents"
                + "\\NetBeansProjects\\FaceDetectionBiometricsSoft\\src\\appImages\\AFDRSAppIcon.png"));
        productImageLabel.setBounds(2, 2, 175, 120);
        
        homeButton.setBounds(10,130,50,30);
        registerUserButton.setBounds(10,170,90,30);
        SessionLogs.setBounds(10,210,90,30);
        viewUsersButton.setBounds(10,250,80,30);
        settingsButton.setBounds(10,290,70,30);
        helpButton.setBounds(10,330,50,30);
        
        List<JButton> buttonsList = Arrays.asList(
                registerUserButton,SessionLogs,viewUsersButton,helpButton,settingsButton,homeButton
        );
        
        viewUsersButton.addActionListener(e -> FaceDetectionUISkeleton.replaceRightComponent( new Users()));
        SessionLogs.addActionListener(e -> FaceDetectionUISkeleton.replaceRightComponent( new SessionLogs()));
        homeButton.addActionListener(e -> FaceDetectionUISkeleton.replaceRightComponent( new HomeScreen()));
        registerUserButton.addActionListener(e -> FaceDetectionUISkeleton.replaceRightComponent( new FaceDetectionUI()));
        
        setButtonProperties(buttonsList);

        add(productImageLabel);
    }
    
    public final void setButtonProperties(List<JButton> buttons){
        buttons.stream()
                .forEach(e -> {
                    e.setBackground(Color.decode("#f5f5f5"));
                    e.setForeground(Color.decode("#1e90ff"));
                    e.setBorder( new LineBorder(Color.decode("#f5f5f5")));
                    e.setFont( new Font("Calibri",Font.BOLD,14));
                    add(e);
                });
    }

}
