package com.sarpreetsingh.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import com.sarpreetsingh.server.http.exceptions.BadRequestException;

public class RequestParser {

	private MethodType type;
	private String path;
	private boolean connectionClosed = false;
	private long contentLength = 0;
	private StringBuilder body;
	public enum MethodType {GET, POST, PUT, DELETE}

	public RequestParser() {
	}

	public RequestParser parse(BufferedReader reader) throws BadRequestException, IOException {

		parseHeader(readHeader(reader));

		if (contentLength > 0) {
			readBody(reader);
		}

		return new RequestParser(type, path, connectionClosed, body);
	}

	private void parseHeader(String header) throws BadRequestException, ArrayIndexOutOfBoundsException {

		String[] totalLines = header.split("\r\n");

		String[] request = totalLines[0].split(" ");

		if (request.length != 3) {
			throw new BadRequestException();
		}

		for (int i = 1; i < totalLines.length; i++) {
			if (totalLines[i].startsWith("Connection")) {
				this.connectionClosed = totalLines[i].split(": ")[1].equals("close");
				break;
			}
		}

		this.type = getEnumMethodType(request[0]);
		this.path = request[1];
	}

	private String readHeader(BufferedReader reader) throws IOException {

		StringBuilder header = new StringBuilder();

		while (true) {

			String line = reader.readLine();

			if (line == null || line.equals("\r\n") || line.isEmpty() || line.equals("")) {
				break;
			}

			header.append(line + "\r\n");

			if (line.startsWith("Content-Length") || line.startsWith("content-length")) {
				contentLength = Integer.parseInt(line.substring(16));
			}
		}
		return header.toString();
	}

	private void readBody(BufferedReader reader) throws IOException {
		body = new StringBuilder();
		for (int i = 0; i < contentLength; i++) {
			body.append((char) reader.read());
		}
	}

	private MethodType getEnumMethodType(String method) throws BadRequestException {

		for (MethodType m : MethodType.values()) {
			if (method.equals(m.name())) {
				return m;
			}
		}
		throw new BadRequestException();
	}

	private RequestParser(MethodType type, String path, boolean connectionClosed, StringBuilder body) {
		this.type = type;
		this.path = path;
		this.connectionClosed = connectionClosed;
		this.body = body;
	}

	public MethodType getMethodType() {
		return type;
	}

	public String getPath() {
		return path;
	}

	public boolean connectionClosed() {
		return connectionClosed;
	}

	public String getBody() {
		return body.toString();
	}
}