package com.sarpreetsingh.server.http.response;

import com.sarpreetsingh.server.http.ServerThread;

public class Response500InternalServerError extends Response {

	public Response500InternalServerError(ServerThread client) {
		super(client, "500 Internal Server Error", "Server have some internal problem");
	}
}