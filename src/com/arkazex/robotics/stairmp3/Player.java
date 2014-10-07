package com.arkazex.robotics.stairmp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Player{

	public static void playClip(final String file) {
		new Thread() {
			@Override
			public void run() {
				try {
					if(!new File(file).exists()) {
						System.out.println("Could not find file with name \"" + file + "\"");
						return;
					}
					InputStream in = new FileInputStream(file);
					AudioStream as = new AudioStream(in);
					AudioPlayer.player.start(as);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
