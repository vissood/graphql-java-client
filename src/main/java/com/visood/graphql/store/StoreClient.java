package com.visood.graphql.store;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.visood.graphql.client.objects.GraphQLQuery;
import com.visood.graphql.client.objects.Item;
import com.visood.graphql.client.response.GraphQLResponseParser;
import com.visood.graphql.store.objects.Product;

public class StoreClient {
	
	private final static String path = "http://localhost:8080/graphql";
	
	private WebTarget getClient () {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));
		return target;
		
	}
	
	public Product getProduct(String productId) {
		Product  p = null;
		GraphQLQuery query = new GraphQLQuery("{productById(id:\""+productId + "\") {name, id, mrp, sp}}");
		
		WebTarget target = getClient();
		
		Response response = target.request().accept("application/json").post(Entity.entity(query, "application/json"));
		
		if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
		
		try {
			p = GraphQLResponseParser.parse(response, "productById", Product.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	public String placeOrder(List<Item> items) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("mutation{placeOrder(items:[");
		boolean first = true;
		for(Item item:items) {
			if(!first)
				sb.append(",");
			else
				first=false;
			
			sb.append(item.getString());
		}
		sb.append("])}");
		
		GraphQLQuery query = new GraphQLQuery(sb.toString());
		WebTarget target = getClient();
		Response response = target.request().accept("application/json").post(Entity.entity(query, "application/json"));
		
		if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
		
		String orderId = null;
		
		try {
			orderId = GraphQLResponseParser.parseAsString(response, "placeOrder");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderId;
	}
}
