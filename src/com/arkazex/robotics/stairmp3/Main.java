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
		while(true) {
			System.out.print("> ");
			String[] results = in.nextLine().split(" ");
			for(int i = 0; i < results.length; i++) {
				if(results[i] == "x") {
					Thread.sleep(500);
				} else if(results[i] == "xx") {
					Thread.sleep(1000);
				} else {
					playSound("WAVs/Track" + results[i] + ".wav");
				}
			}
		}
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
