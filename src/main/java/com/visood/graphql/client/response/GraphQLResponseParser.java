package com.visood.graphql.client.response;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visood.graphql.client.objects.DataResponse;

public class GraphQLResponseParser {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T parse(Response response, String query, Class<T> clazz) throws JSONException, JsonParseException, JsonMappingException, IOException {
		String responseStr = response.readEntity(String.class);
		
		JSONObject obj = new JSONObject(responseStr);
		JSONObject obj1 = obj.getJSONObject("data");
		JSONObject obj2 = obj1.getJSONObject(query);
		
	/*	JSONObject data = res.getJSONObject("data");
		JSONObject queryResult = data.getJSONObject(query);
		System.out.println(queryResult.toString());*/
		return objectMapper.readValue(obj2.toString(), clazz);
		
	//	return null;
	}
	
	public static String parseAsString(Response response, String query) throws JSONException, JsonParseException, JsonMappingException, IOException {
		String responseStr = response.readEntity(String.class);
		
		JSONObject obj = new JSONObject(responseStr);
		JSONObject obj1 = obj.getJSONObject("data");
		return obj1.getString(query);
	}

}
