package ru.ragnok123.sqlNukkitLib.sqlite3;

import java.sql.DriverManager;
import java.sql.SQLException;

import ru.ragnok123.sqlNukkitLib.Database;
import ru.ragnok123.sqlNukkitLib.SQLConnectionInfo;

public class SQLite3Database extends Database {

	public SQLite3Database(SQLConnectionInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection(this.connectInfo.getConnectionString());
			this.connection.createStatement();
			System.out.printf("[sqlNukkitLib] Connected to SQLite3 DATABASE");
		} catch(SQLException sqle) {
		
		} catch (ClassNotFoundException ex) {
			
		}
	}

}
