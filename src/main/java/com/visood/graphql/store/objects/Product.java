package com.visood.graphql.store.objects;

public class Product {
	private String id;
	private String name;
	private Float mrp;
	private Float sp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getMrp() {
		return mrp;
	}
	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}
	public Float getSp() {
		return sp;
	}
	public void setSp(Float sp) {
		this.sp = sp;
	}

}
