package com.arkazex.robotics.stairmp3;

import java.util.Scanner;

import jssc.SerialPort;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println("Connecting to serial device...");
			SerialPort serialPort = new SerialPort("COM3");
			System.out.println("Configuring device...");
			serialPort.openPort();
			serialPort.setParams(9600, 8, 1, 0);
			serialPort.writeBytes("Hello!".getBytes());
			while(true) {
				StringBuilder sb = new StringBuilder();
				byte in = 0;
				while(in != 0x0A) {
					in = serialPort.readBytes(1)[0];
					sb.append(new String(new byte[]{in}));
				}
				System.out.print(sb.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
