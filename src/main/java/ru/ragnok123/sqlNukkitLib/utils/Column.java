package ru.ragnok123.sqlNukkitLib.utils;

public class Column {
	
	public enum ColumnType{
		INT, CHAR, VARCHAR, BINARY, VARBINARY, TINYBLOB, TINYTEXT, TEXT, BLOB, MEDIUMTEXT, MEDIUMBLOB, LONGTEXT, LONGBLOB;
	}

	private String columnName;
	private ColumnType columnType;
	private int columnSize;
	private boolean notNull;
	private String defaultValue;
	
	public Column(String columnName) {
		this(columnName, ColumnType.CHAR);
	}
	
	public Column(String columnName, ColumnType type) {
		this(columnName, type, 1);
	}
	
	public Column(String columnName, ColumnType type, int size) {
		this(columnName, type, size, true);
	}
	
	public Column(String columnName, ColumnType type, int size, boolean notNull) {
		this(columnName, type, size, notNull, "");
	}
	
	public Column(String columnName, ColumnType type, int size, boolean notNull, String defaultValue) {
		this.columnName = columnName;
		this.columnType = type;
		this.columnSize = size;
		this.notNull = notNull;
		this.defaultValue = defaultValue;
	}
	
	public String creationColumnString() {
		String id = "`"+this.columnName+"`";
		String type = this.columnType.name() + "(" + String.valueOf(this.columnSize) + ")";
		String nil = this.notNull ? "NOT NULL" : "";
		String def = "default '"+this.defaultValue+"'";
		return id + " " + type + " " + nil + " " + def;
	}
	
}