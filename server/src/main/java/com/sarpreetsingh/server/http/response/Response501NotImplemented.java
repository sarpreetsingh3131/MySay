package com.sarpreetsingh.server.http.response;

import com.sarpreetsingh.server.http.ServerThread;

public class Response501NotImplemented extends Response {

	public Response501NotImplemented(ServerThread client) {
		super(client, "501 Not Implemented",
				"The server lacks the ability to fulfill the request. The sever only support GET method");
	}
}