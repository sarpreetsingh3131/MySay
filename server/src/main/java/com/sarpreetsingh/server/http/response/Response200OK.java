package com.sarpreetsingh.server.http.response;

import com.sarpreetsingh.server.http.ServerThread;

public class Response200OK extends Response {

	public Response200OK(ServerThread client, String json) {
		super(client, "200 OK", "");
		super.CONTENT = json;
		super.EXTENSION = "json";	
	}
}