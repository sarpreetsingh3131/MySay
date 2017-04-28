package com.sarpreetsingh.server.http.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import com.sarpreetsingh.server.http.ServerThread;

public abstract class Response {

	private String response;
	protected String CONTENT;
	protected ServerThread client;
	protected String EXTENSION = "html";

	public Response(ServerThread client, String header, String content) {
		this.response = "HTTP/1.1 " + header + "\r\n";
		this.CONTENT = "<html><body><h1>" + header + "</h1><h2>" + content + "</h2></body></html>";
		this.client = client;
	}

	public void write() throws IOException {
		writeHeader(CONTENT.getBytes().length, EXTENSION);
		writeContent();
	}

	private void writeHeader(long length, String extension) throws IOException {
		response += "Date: " + new Date().toString() + "\r\n";
		response += "Content-Length: " + length + "\r\n";
		response += "Access-Control-Allow-Origin: *\r\n";
		response += "Content-Type: " + extension + "\r\n\r\n";

		PrintWriter printer = new PrintWriter(client.getSocket().getOutputStream(), true);
		printer.write(response);
		printer.flush();
	}

	private void writeContent() throws IOException {
		client.getSocket().getOutputStream().write(CONTENT.getBytes());
	}
}