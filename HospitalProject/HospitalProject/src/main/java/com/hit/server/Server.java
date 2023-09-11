package com.hit.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server  implements Runnable{
	private static boolean serverUp = true;

	public Server(int port) {
		killServer();
		try {
			ServerSocket server = new ServerSocket(port);
			while (serverUp) {
				Socket someClient = server.accept();
				new Thread(new Route(someClient)).start();
			}
			server.close();
		} catch (Exception e) {
			System.out.println("tired of waiting for connection");
		}
	}

	public static void killServer() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Scanner scn = new Scanner(System.in);
				while (serverUp) {
					String s = scn.nextLine();
					if (s.equals("stop"))
						serverUp = false;
				}
				scn.close();
			}
		}).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
