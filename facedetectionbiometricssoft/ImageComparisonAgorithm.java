/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class ImageComparisonAgorithm {

    /**
     *Image size variable
     */
    private static int sizeA;
    private static int sizeB;

    /**
     * @param fileA
     * @param fileB
     * step 1. check size
     * step2. 2-dimension array-pixel point 
     * return true if images are same size and same pixel density
     * return false if not same size
     * or if pixel density do not match.
     * @return  a true/ false value
     */

    public static boolean compare(File fileA, File fileB) {
        try {
            FileInputStream fileInputStreamA = new FileInputStream(fileA);
            FileInputStream fileInputStreamB = new FileInputStream(fileB);
            BufferedImage bufferdImageA = ImageIO.read(fileInputStreamA); //buffferdImage store data about the image file
            System.err.println(""+bufferdImageA);
            DataBuffer dba = bufferdImageA.getData().getDataBuffer();
            sizeA = dba.getSize();
            BufferedImage bufferdImageB = ImageIO.read(fileInputStreamB);
            DataBuffer dbB = bufferdImageB.getData().getDataBuffer();
            sizeB = dbB.getSize();

            if (sizeA == sizeB) { // compare sizes
                System.err.println("Image size is same");
                for (int i = 0; i < sizeB; i++) { // match pixels
                    if (dba.getElem(i) != dbB.getElem(i)) {
                        System.err.println("Pixel size failed");
                        return false;
                    }
                }
                System.err.println("Pixel density test passed");
                JOptionPane.showMessageDialog(null, "Sign in successfull!!","",JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                 JOptionPane.showMessageDialog(null, "Sign in Failed, Image sizes are different!!","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sign in Failed Image Size is Different!!"+e.getMessage(),"",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

