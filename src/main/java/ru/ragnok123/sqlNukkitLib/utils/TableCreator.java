package ru.ragnok123.sqlNukkitLib.utils;

public class TableCreator {
	
	public TableCreator(String tableName, Column[] columns) {
		this.tableName = tableName;
		this.columns = columns;
	}
	
	public String tableName;
	public Column[] columns;
	
}