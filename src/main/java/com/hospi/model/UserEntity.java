package com.hospi.model;

public class UserEntity {

	
	private String name;
	private String other;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public UserEntity(String name, String other) {
		super();
		this.name = name;
		this.other = other;
	}
	
	
}
