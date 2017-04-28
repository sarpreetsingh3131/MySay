package com.sarpreetsingh.server.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestHandler {

	private final File RESOURCES = new File("src/main/java/com/sarpreetsingh/server/resources/data.json");

	public synchronized String GET(String path) throws FileNotFoundException, ParseException, IOException {

		JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(RESOURCES));

		if (path.equals("/")) {
			return jsonArray.size() <= 100 ? jsonArray.toJSONString() : getData(jsonArray, 0, 100);
		}

		else if (path.startsWith("/?after=")) {
			int index = getIndex(path.split("=")[1].trim(), jsonArray);
			return (Integer.parseInt(path.split("=")[1].trim()) + 100) >= jsonArray.size()
					? getData(jsonArray, index, jsonArray.size()) : getData(jsonArray, index, index + 100);
		}
		
		else if(path.startsWith("/?before=")) {
			int index = getIndex(path.split("=")[1].trim(), jsonArray);
			return getData(jsonArray, 0, index - 1);	
		}

		throw new FileNotFoundException();
	}

	@SuppressWarnings("unchecked")
	public synchronized void POST(String body) throws FileNotFoundException, IOException, ParseException {
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(RESOURCES));
		jsonArray.add(0, (JSONObject) new JSONParser().parse(body));
		FileWriter writer = new FileWriter(RESOURCES);
		writer.write(jsonArray.toJSONString());
		writer.flush();
		writer.close();
	}

	@SuppressWarnings("unchecked")
	private String getData(JSONArray jsonArray, int start, int end) throws IOException {
		JSONArray array = new JSONArray();
		for (int i = start; i < end; i++) {
			array.add(jsonArray.get(i));
		}
		return array.toJSONString();
	}

	private int getIndex(String id, JSONArray array) throws IOException {
		for (Object object : array) {
			Long key = (long) ((JSONObject) object).get("id");
			if (key == Long.parseLong(id)) {
				return array.indexOf(object) + 1;
			}
		}
		throw new IOException();
	}
}