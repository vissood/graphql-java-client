package com.visood.graphql.store.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visood.graphql.client.objects.Item;
import com.visood.graphql.store.StoreClient;
import com.visood.graphql.store.objects.Product;

public class StoreClientTest {

	@Test
	public void testGetProduct() {
		StoreClient sc = new StoreClient();
		Product p = sc.getProduct("product-1");
		System.out.println(p);
	}

/*	@Test
	public void test1() throws JSONException {
		String responseStr = "{\"data\":{\"productById\":{\"name\":\"MAC\",\"id\":\"product-1\",\"mrp\":223.0,\"sp\":210.0}}}";
		JSONObject obj = new JSONObject(responseStr);
		JSONObject obj1 = obj.getJSONObject("data");
		System.out.println(obj1.toString());
		JSONObject obj2 = obj1.getJSONObject("productById");
	}*/
	
	@Test
	public void testPlaceOrder() throws JsonProcessingException {
		StoreClient sc = new StoreClient();
		List<Item> items = new ArrayList<Item>();
		Item e = new Item("product-1",10);
		items.add(e);
		Item e1 = new Item("product-2",10);
		items.add(e1);
		/*ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(items));*/
		
		StringBuffer sb = new StringBuffer();
		sb.append("mutation{placeOrder(items:[");
		boolean first = true;
		for(Item item:items) {
			if(!first)
				sb.append(",");
			sb.append(item.getString());
		}
		sb.append("]");
		System.out.println(sb.toString());
		String orderId = sc.placeOrder(items);
		System.out.println(orderId);
	}

}
