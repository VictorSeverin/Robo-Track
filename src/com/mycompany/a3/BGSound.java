package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable {
	private Media m;
	GameWorld gw;

	public BGSound(String fileName, GameWorld gw) {
		this.gw = gw;
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		while (m == null) { // ADD THIS
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				m = MediaManager.createMediaAsync(is, "audio/wav", this::play).get(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pause() {
		m.pause();
	} // pause playing the sound

	public void play() {
		if (!m.isPlaying() && !gw.isPaused()) {
			m.play();
		} else {
			m.pause();
		}
	}

	public void run() {
		// start playing from time zero (beginning of the sound file)
		m.setTime(0);
		if (!gw.getSoundBool()) {
			m.play();
		}

	}
}
