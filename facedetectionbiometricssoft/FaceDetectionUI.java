/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * //The User registration interface
 */
public class FaceDetectionUI extends JPanel {

    private static JProgressBar progressBar;
    private static JButton saveImage;
    private static JButton captureImageButton;
    private static JButton doneButton;
    private static JTextField keyField;
    private static int progressCounter = 0;
    private static JLabel urlEntryLabel;
    private static JLabel imageProcessingLabel;
    private static JPanel imageCapturePanel;
    private static JTextArea imageLogssTextArea;
    private static JFileChooser fileChooser;
    private static File selectedFile;
    private static String b_File;
    private static List<String> cipherTextLines;
    private static int enteredKey = 0;
    private static JScrollPane scrollPane;
    private static JTextField firstNameField;
    private static JLabel firstNameLabel;
    private static JTextField lastNameField;
    private static JLabel lastNameLabel;
    private static String imageUrl;
    private static JLabel imageLabel;
    private static JLabel emailAddressLabel;
    private static JTextField emailAddressField;
    
    
    public FaceDetectionUI() {

        setSize(800, 600);
        setLocation(0, 0);
        setLayout(null);
        setBackground(Color.WHITE);

        /**
         * prepare variables variables
         */
        
        scrollPane = new JScrollPane();
        firstNameField = new JTextField();
        firstNameLabel = new JLabel("First Name:");
        lastNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name:");

        emailAddressField = new JTextField();
        emailAddressLabel = new JLabel("Email Address:");
        
        fileChooser = new JFileChooser( new File("D:\\NetBeansProjects\\FaceDetectionBiometricsSoft"));
        imageCapturePanel = new JPanel(null);
        urlEntryLabel = new JLabel(":");
        saveImage = new JButton("Save");
        captureImageButton = new JButton("Capture");
        doneButton = new JButton("DONE");
        progressBar = new JProgressBar();
        keyField = new JTextField();
        imageProcessingLabel = new JLabel("");
        imageLogssTextArea = new JTextArea();
        imageLabel = new JLabel(new ImageIcon(getClass()
                .getResource("/appImages/what-is-material-design.png")));
        imageLabel.setBackground(Color.black);
        imageLabel.setForeground(Color.red);

        /**
         * Set Bounds For user interface components
         */
        
        
        keyField.setBounds(200, 40, 350, 40);
        urlEntryLabel.setBounds(565, 40, 200, 35);
        imageCapturePanel.setBounds(200, 40, 450, 340);
        scrollPane.setBounds(5, 20, 440, 310);
        progressBar.setBounds(200, 400, 450, 25);
        imageProcessingLabel.setBounds(250, 435, 350, 25);
        firstNameLabel.setBounds(200, 430, 100, 25);
        firstNameField.setBounds(310, 430, 200, 30);
        emailAddressField.setBounds(310, 520, 200, 30);
        emailAddressLabel.setBounds(200, 520, 100, 25);
        lastNameLabel.setBounds(200, 470, 100, 25);
        lastNameField.setBounds(310, 470, 200, 30);

        captureImageButton.setBounds(30, 40, 150, 50);
        doneButton.setBounds(530, 520, 150, 35);

        progressBar.setStringPainted(true);
        progressBar.setMaximum(100);
        progressBar.setMinimum(0);

        /**
         * Set components properties e.g button size
         */
        
        scrollPane.getViewport().add(imageLabel);
        urlEntryLabel.setForeground(Color.red);
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.red), "Image Capture");
        titledBorder.setTitleColor(Color.decode("#f5f5f5"));
        imageCapturePanel.setBorder(titledBorder);
        imageCapturePanel.setBackground(Color.black);
        imageProcessingLabel.setForeground(Color.red);
        imageProcessingLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        keyField.setBackground(Color.BLUE);
        keyField.setBorder(new LineBorder(Color.decode("#1e90ff")));
        keyField.setForeground(Color.RED);
        keyField.setFont(new Font("Calibri", Font.BOLD, 18));
        imageCapturePanel.add(scrollPane);

        /**
         * Collect all buttons into a list Buttons 
         */
        
        List<JButton> buttons = Arrays.asList(
                saveImage, captureImageButton, doneButton
        );
        setButtonProperties(buttons); // set button properties and pass a list of buttons

        //Execute save user details commmand by sending the details into the databases
        saveImage.addActionListener(e -> {
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null) {
                selectedFile = fileChooser.getSelectedFile();
                imageUrl = selectedFile.getPath();
            } else if (fileChooser.getSelectedFile() == null) {
                JOptionPane.showMessageDialog(null, "No File has been selected...", "Face Detection Soft",
                        JOptionPane.ERROR_MESSAGE);
                selectedFile = new File("temp.txt");
            }

        });
        
        /**
         * Call done button to send user images to the database
         */
        doneButton.addActionListener(e -> UsersDaos.registerUsers(
                firstNameField.getText(), 
                lastNameField.getText(),
                emailAddressField.getText(),
                imageUrl
                ));

        /**
         * Capture image by calling a file selection dialog
         */
        
        captureImageButton.addActionListener(e -> {
            //check key interity end exiting

            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null) {
                try {
                    selectedFile = fileChooser.getSelectedFile();
                    System.err.println("Image obtained"+selectedFile.getPath());
                    imageLabel.setIcon( new ImageIcon(ImageIO.read(selectedFile) ) );
                    imageUrl = selectedFile.getPath();
                } catch (IOException ex) {
                    Logger.getLogger(FaceDetectionUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ""+ex.getMessage());
                }catch(NullPointerException ec){
                    JOptionPane.showMessageDialog(null, "Unable to capture Image, Restart and Try again");
                }
            } else if (fileChooser.getSelectedFile() == null) {
                JOptionPane.showMessageDialog(null, "No File has been selected...", "Encryptor",
                        JOptionPane.ERROR_MESSAGE);
                selectedFile = new File("temp.txt");
            }
            /**
             * Run the progress Dialog
             */
            if (fileChooser.getSelectedFile() != null) {

                imageProcessingLabel.setText("Image processing in progress...");
                new Thread(
                        () -> {
                            while (progressCounter <= 100) {
                                SwingUtilities.invokeLater(() -> {
                                    progressBar.setValue(progressCounter++);
                                    //control progressbar
                                    if (progressCounter == 30) {
                                        imageProcessingLabel.setText("Carrying out binnary rebuild...");

                                    } else if (progressCounter == 60) {
                                        imageProcessingLabel.setText("Completing verification cycles...");
                                    } else if (progressCounter == 80) {
                                        imageProcessingLabel.setText("Image index build in progress...");
                                    } else if (progressCounter == 80) {
                                        imageProcessingLabel.setText("On completion stage...");
                                    } else if (progressCounter == 90) {
                                        imageProcessingLabel.setText("Extracting binnary bits...");
                                    } else if (progressCounter == 95) {
                                        imageProcessingLabel.setText("Almost done...");
                                    } else if (progressCounter == 97) {
                                        imageProcessingLabel.setText("Completing image-db publishing...");
                                    }
                                    if (progressCounter == 100) {
                                        JOptionPane.showMessageDialog(null,
                                                "Image File storage was succeful completed succefully!!", "Image File Processing",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        imageProcessingLabel.setText("Image storage completed succefully...");
                                        progressBar.setValue(0);
                                    }

                                    //control dialog window
                                    switch (progressCounter) {
                                        case 10:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Carrying out binnary rebuild...\n"));
                                            break;
                                        case 20:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Carrying out binnary rebuild...\n"));
                                            break;
                                        case 30:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Completing verification cycles...\n"));
                                            break;
                                        case 40:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Completing verification cycles...\n"));
                                            break;
                                        case 50:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Image index build in progress...\n"));
                                            break;
                                        case 60:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Image index build in progress...\n"));
                                            break;
                                        case 70:
                                            imageLogssTextArea.append("\n" + getImageBuffer("On completion stage...\n"));
                                            break;
                                        case 80:
                                            imageLogssTextArea.append("\n" + getImageBuffer("On completion stage...\n"));
                                            break;
                                        case 90:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Extracting binnary bits...\n"));
                                            break;
                                        case 100:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Extracting binnary bits...\n"));
                                            break;
                                        case 35:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Almost done...\n"));
                                            break;
                                        case 45:
                                            imageLogssTextArea.append("\n" + getImageBuffer("Completing image-db publishing...\n"));
                                            break;
                                    }

                                });

                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException ex) {
                                }
                            }

                        }).start();
            } else {
                imageProcessingLabel.setText("Waiting for File selection...");
                JOptionPane.showMessageDialog(null, "No File was selected!!", "Face  Recognition Soft", JOptionPane.ERROR_MESSAGE);
            }
            progressBar.setValue(0);
        });

        add(firstNameField);
        add(firstNameLabel);
        add(lastNameField);
        add(lastNameLabel);
        add(urlEntryLabel);
        add(emailAddressField);
        add(emailAddressLabel);
        add(emailAddressField);
        add(progressBar);
        add(imageProcessingLabel);
        add(imageCapturePanel);
    }

    /**
     * Read the image file the user selects for processing
     * @param backUpFile
     * @return 
     */
    
    public static List<String> readTargetFile(File backUpFile) {
        try {
        } catch (NullPointerException ex) {
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(backUpFile))) {
            String line = "";
            List<String> lines = new ArrayList<>();
            while (((line = bufferedReader.readLine()) != null)) {
                lines.add(line);
            }
            return lines;

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Run to obtain the image buffer
     * @param targetFile
     * @param b_File 
     */
    
  
    public static String getImageBuffer(String plainText) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] cipherLavelOne = messageDigest.digest(plainText.getBytes());
            messageDigest.update(plainText.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cipherLavelOne.length; i++) {
                stringBuilder.append(Integer.toString((cipherLavelOne[i] & 0xff) + 0x100, 32)).toString();
            }
            String cipher = stringBuilder.toString();
            return cipher;
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }

    }

    /**
     * Handles the look and feel of the buttons
     * @param buttons 
     */
    
    public final void setButtonProperties(List<JButton> buttons) {
        buttons.stream()
                .forEach(e -> {
                    e.setBackground(Color.decode("#1e90ff"));
                    e.setForeground(Color.WHITE);
                    e.setBorder(new LineBorder(Color.decode("#1e90ff")));
                    add(e);
                    e.addMouseListener(
                            new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent event) {
                            e.setBackground(Color.black);
                            e.setForeground(Color.red);
                        }

                        @Override
                        public void mouseExited(MouseEvent event) {
                            e.setBackground(Color.decode("#1e90ff"));
                            e.setForeground(Color.WHITE);
                        }

                    }
                    );
                });

    }

    /**
     * Write a new list representing the selected image for this category
     * @param imageBufferArray
     * @param categoryFile
     */
    public static void writeImageBackUpFile(List<String> imageBufferArray, File categoryFile) {

        try {
            FileWriter fileWriter = new FileWriter(categoryFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            imageBufferArray.stream()
                    .forEach(e -> {
                        try {
                            bufferedWriter.write(e);
                            bufferedWriter.newLine();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Unable to write back up image file\n"
                                    + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                        }
                    });
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to process Image\n"
                    + e.getMessage(), "Face Detection soft", JOptionPane.ERROR_MESSAGE);

        }
    }

}
