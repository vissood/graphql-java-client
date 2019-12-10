package com.visood.graphql.client.objects;

public class GraphQLMutation {
	String mutation;

	
	public GraphQLMutation (String mutation) {
		this.mutation = mutation;
	}
	
	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}
	
}
