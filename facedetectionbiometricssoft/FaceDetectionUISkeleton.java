/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *This is the UI layout for the application
 */
public class FaceDetectionUISkeleton extends JFrame {
    
    private static JSplitPane splitPane;
    private static JLabel productIconLabel;
    
    
    
    public FaceDetectionUISkeleton(){
        setSize(950,650);
        setLocation(100,100);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        productIconLabel = new JLabel( new ImageIcon("C:\\Users\\HACKER\\Documents"
                + "\\NetBeansProjects\\FaceDetectionBiometricsSoft\\src\\appImages\\AFDRSAppIcon.png"));
        splitPane = new JSplitPane();
        splitPane.setDividerSize(1);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(180);
        splitPane.setBounds(20,20,890,580);
        productIconLabel.setBounds(5,5,175,120);
        
        splitPane.setRightComponent( new HomeScreen());
        splitPane.setLeftComponent( new MenuPanel());
        
        add(splitPane);
        
        repaint();
        setVisible(true);
    }
    
    /**
     * Replace the right component
     * @param nextPanel JPanel
     */
    public  static  void replaceRightComponent(JPanel nextPanel){
        splitPane.remove(splitPane.getRightComponent());
        splitPane.setRightComponent(nextPanel);
        splitPane.setDividerLocation(180);
        splitPane.setEnabled(false);
    }
    
    public static void main(String[] args) {
        new FaceDetectionUISkeleton();
    }
}
