package com.sarpreetsingh.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.sarpreetsingh.server.http.ServerThread;

public class App {
	
	public final static int PORT = 9090;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("ERROR: PORT IS IN USE!!");
			System.exit(1);
		}

		while (true) {
			Socket socket = serverSocket.accept();
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
		}
	}
}