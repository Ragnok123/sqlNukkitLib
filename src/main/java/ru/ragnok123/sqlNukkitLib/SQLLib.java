package ru.ragnok123.sqlNukkitLib;

import ru.ragnok123.sqlNukkitLib.mysql.MySQLDatabase;
import ru.ragnok123.sqlNukkitLib.sqlite3.SQLite3Database;

public class SQLLib {
	
	public static Database init(SQLType type, SQLConnectionInfo info) {
		Database db = null;
		
		switch(type) {
		case MySQL:
			db = new MySQLDatabase(info);
			break;
		case SQLite3:
			db = new SQLite3Database(info);
			break;
		}
		
		return db;
	}
	
}