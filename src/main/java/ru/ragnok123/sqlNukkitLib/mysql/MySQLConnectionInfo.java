package ru.ragnok123.sqlNukkitLib.mysql;

import ru.ragnok123.sqlNukkitLib.SQLConnectionInfo;

public class MySQLConnectionInfo implements SQLConnectionInfo{

	private String ip;
	private String user;
	private String password;
	private String database;
	private String port;
	
	public MySQLConnectionInfo(String ip, String user, String password, String database) {
		this(ip,user,password,database,"3306");
	}
	
	public MySQLConnectionInfo(String ip, String user, String password, String database, String port) {
		this.ip = ip;
		this.port = port;
		this.password = password;
		this.database = database;
		this.port = port;
	}
	
	public String getConnectionString() {
		return "jdbc:mysql://"+this.ip+":"+port+"/" + database + "?" + "user=" + user + "&" + "password=" + password;
	}
	
}
