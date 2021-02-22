package ru.ragnok123.sqlNukkitLib.utils;

public enum OrderType {
	
	ASCENDING("ASC"),
	DESCENDING("DESC");
	
	OrderType(String type){
		this.type = type;
	}
	
	private String type;
	
	public String getType() {
		return this.type;
	}
	
}