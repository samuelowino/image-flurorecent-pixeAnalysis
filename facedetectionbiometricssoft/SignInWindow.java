/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Logs users into the specific system using their 
 * sign in credentials
 */
public class SignInWindow extends JFrame {

    private static JLabel signInLabel;
    private static JLabel userNameLabel;
    private static JTextField emailAddressField;
    private static JScrollPane userImagePane;
    private static JLabel userImageLabel;
    private static JButton captureImageButton;
    private static JButton verifyImageButton;
    private static JFileChooser fileChooser;
    private static File selectedFile;
    private static String imageUrl;
    
    public SignInWindow() {
        setLocation(200, 50);
        setSize(800, 600);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#084061"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        selectedFile = new File("");
        fileChooser = new JFileChooser(new File("D:\\NetBeansProjects\\FaceDetectionBiometricsSoft"));
        signInLabel = new JLabel("Sign");
        userNameLabel = new JLabel("User Name");
        emailAddressField = new JTextField();
        userImageLabel = new JLabel(new ImageIcon(getClass().getResource("/appImages/what-is-material-design.png")));
        userImagePane = new JScrollPane();
        captureImageButton = new JButton("Capture");
        verifyImageButton = new JButton("SIGN IN");

        signInLabel.setBounds(20, 20, 100, 30);
        userNameLabel.setBounds(150,20,100,30);
        emailAddressField.setBounds(220,20,200,25);
        userImagePane.setBounds(100,60,500,350);
        captureImageButton.setBounds(200,440,100,25);
        verifyImageButton.setBounds(350,440,100,25);
        
        userImagePane.getViewport().add(userImageLabel);
        
        List<JComponent> compoents = Arrays.asList(
                signInLabel,userImagePane,emailAddressField,userNameLabel,captureImageButton,
                verifyImageButton
        );
        
        List<JButton> buttons = Arrays.asList(
                captureImageButton,verifyImageButton
        );
        
        /**
         * Captures the necessary images
         * For comparison with existing images
         */
        
        captureImageButton.addActionListener( e -> {
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null) {
                selectedFile = fileChooser.getSelectedFile();
                System.err.println("image obtained"+selectedFile.getPath());
                userImageLabel.setIcon( new ImageIcon(getClass().getResource("/signInImages/"+selectedFile.getName())));
                imageUrl = selectedFile.getPath();
            } else if (fileChooser.getSelectedFile() == null) {
                JOptionPane.showMessageDialog(null, "No File has been selected...", "Encryptor",
                        JOptionPane.ERROR_MESSAGE);
                selectedFile = new File("temp.txt");
            }
        });
        
        verifyImageButton.addActionListener(e -> {
            //obtain store image
            String storedImageUrl = UsersDaos.getUserImageUrl(emailAddressField.getText());
            File storedImage = new File(storedImageUrl);
            File capturedImage = selectedFile;
            System.err.println("Store image"+storedImageUrl);
            ImageComparisonAgorithm.compare(storedImage, capturedImage);
        });
        
        
        setButtonProperties(buttons);
        addComponentsToFrame(compoents);
        
        repaint();
        setVisible(true);

    }
    
    public static void setButtonProperties(List<JButton> button){
        button.stream()
                .forEach(e -> {
                    e.setBackground(Color.decode("#1e90ff"));
                    e.setBorder( new LineBorder(Color.decode("#1e90ff")));
                    e.setForeground(Color.WHITE);
                    e.setFont( new Font("Calibri",Font.BOLD,14));
                });
    }
   
    /**
     * Adds UI components to the frame
     * @param components 
     */
    
    public void addComponentsToFrame(List<JComponent> components){
        components.stream()
                .forEach(e -> {
                    add(e);
                });
    }

    public static void main(String[] args) {
        new SignInWindow();
    }
}
