package com.arkazex.robotics.stairmp3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
	
	private static List<Clip> clips = new ArrayList<Clip>();
	
	public static void main(String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < 100; i++) {
			System.out.print("> ");
			String[] results = in.nextLine().split(" ");
			
			if(results[0].equalsIgnoreCase("play")) {
				playSound(results[1]);
				
				Clip[] array = clips.toArray(new Clip[clips.size()]);
				for(int a = 0; a < array.length; a++) {
					array[a].stop();
					clips.remove(a);
				}
			}
			if(results[0].equalsIgnoreCase("stop")) {
				Clip[] array = clips.toArray(new Clip[clips.size()]);
				for(int a = 0; a < array.length; a++) {
					array[a].stop();
					clips.remove(a);
				}
			}
		}
		in.close();
	}
	
	public static void playSound(final String file) {
		Thread t2 = new Thread() {
			try {
				if(!new File(file).exists()) {
					System.out.println("Could not find file with name \"" + file + "\"");
					return;
				}
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
				clip.open();
				clip.start();
				Thread.sleep(3000);
				clip.stop();
				clip.close();
				inputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		};
		t2.start();
	}
}
