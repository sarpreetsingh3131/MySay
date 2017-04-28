package com.sarpreetsingh.server.http.response;

import com.sarpreetsingh.server.http.ServerThread;

public class Response201Created extends Response {

	public Response201Created(ServerThread client) {
		super(client, "201 Created", "Created successfully");
	}
}
