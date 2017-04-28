package com.sarpreetsingh.server.http.response;

import com.sarpreetsingh.server.http.ServerThread;

public class Response404NotFound extends Response {

	public Response404NotFound(ServerThread client) {
		super(client, "404 Not Found", "The requested resource could not be found.");
	}
}