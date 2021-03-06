package ru.ragnok123.sqlNukkitLib.mysql;

import ru.ragnok123.sqlNukkitLib.Database;
import ru.ragnok123.sqlNukkitLib.SQLConnectionInfo;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase extends Database {

	public MySQLDatabase(SQLConnectionInfo info) {
		super(info);
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(this.connectInfo.getConnectionString());
			this.connection.createStatement();
			System.out.printf("[sqlNukkitLib] Connected to MySQL database");
		} catch(SQLException sqle) {
			System.out.printf("[sqlNukkitLib] Database not found: " + sqle.getErrorCode());
		} catch (ClassNotFoundException ex) {
			System.out.printf("[sqlNukkitLib] Class not found");
		}
	}
	
	

}
