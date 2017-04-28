package com.sarpreetsingh.server.http;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.parser.ParseException;

import com.sarpreetsingh.server.http.exceptions.BadRequestException;
import com.sarpreetsingh.server.http.response.ResponseFactory;

public class ServerThread extends Thread {

	private final Socket socket;
	private ResponseFactory responseFactory;
	private RequestParser requestParser;

	public ServerThread(Socket socket) {
		this.socket = socket;
		requestParser = new RequestParser();
		responseFactory = new ResponseFactory(this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				requestParser = requestParser.parse(new BufferedReader(new InputStreamReader(socket.getInputStream())));
				responseFactory.getResponse(requestParser).write();
				
			} catch (BadRequestException e) {
				responseFactory.writeResponse400BadRequest();
				break;

			} catch (FileNotFoundException e) {
				responseFactory.writeResponse404NotFound();
				break;
			}

			catch (IOException | NullPointerException | ParseException e) {
				responseFactory.writeResponse500InternalServerError();
				break;
			}

			if (requestParser.connectionClosed()) {
				break;
			}
		}

		try {
			socket.close();
		} catch (IOException e) {
			responseFactory.writeResponse500InternalServerError();
		}
	}

	public Socket getSocket() {
		return socket;
	}
}