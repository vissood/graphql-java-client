package com.visood.graphql.client.objects;

public class GraphQLQuery {
	String query;

	
	public GraphQLQuery (String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
