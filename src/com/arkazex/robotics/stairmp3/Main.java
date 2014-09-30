package com.arkazex.robotics.stairmp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.Clip;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Main {
	
	private static List<Clip> clips = new ArrayList<Clip>();
	
	public static void main(String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < 100; i++) {
			System.out.print("> ");
			String[] results = in.nextLine().split(" ");
			playSound(results[0]);
			
			Clip[] array = clips.toArray(new Clip[clips.size()]);
			for(int a = 0; a < array.length; a++) {
				array[a].stop();
				clips.remove(a);
			}
		}
		in.close();
	}
	public static void playSound(final String file) {
		Thread t2 = new Thread() {
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
		};
		t2.start();
	}
}
