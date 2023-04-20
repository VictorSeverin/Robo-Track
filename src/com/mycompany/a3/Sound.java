package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
    private Media m;

    public Sound(String fileName) {
        if (Display.getInstance().getCurrent() == null) {
            System.out.println("create sound");
        }
        while (m == null) {

            try {
                InputStream is = Display.getInstance().getResourceAsStream(
                        getClass(), "/" + fileName);
                m = MediaManager.createMedia(is, "audio/wav");
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    public void play() {
        System.out.println("Play called");
        m.setTime(0);
        m.play();
    }
}
