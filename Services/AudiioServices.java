/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author HACKER
 */
public class AudiioServices {

    public static void main(String[] args) {
        new AudiioServices();
    }

    public AudiioServices() {

        AudioClip audioClip
                = Applet.newAudioClip(getClass().getResource("/AudioRes/sirenOne.mp3"));
        audioClip.play();

    }
}
