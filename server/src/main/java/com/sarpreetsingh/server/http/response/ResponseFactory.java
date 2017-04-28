package com.sarpreetsingh.server.http.response;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.sarpreetsingh.server.http.RequestHandler;
import com.sarpreetsingh.server.http.RequestParser;
import com.sarpreetsingh.server.http.ServerThread;

public class ResponseFactory {

	private ServerThread client;
	private RequestHandler requestHandler;

	public ResponseFactory(ServerThread client) {
		this.client = client;
		this.requestHandler = new RequestHandler();
	}

	public Response getResponse(RequestParser requestParser) throws FileNotFoundException, ParseException, IOException {

		switch (requestParser.getMethodType()) {

		case GET:
			return new Response200OK(client, requestHandler.GET(requestParser.getPath()));

		case POST:
			requestHandler.POST(requestParser.getBody());
			return new Response201Created(client);

		default:
			return new Response501NotImplemented(client);
		}
	}

	public void writeResponse400BadRequest() {
		write(new Response400BadRequest(client));
	}

	public void writeResponse500InternalServerError() {
		write(new Response500InternalServerError(client));
	}

	public void writeResponse404NotFound() {
		write(new Response404NotFound(client));
	}

	private void write(Response response) {
		try {
			response.write();
		} catch (IOException e) {
		}
	}
}