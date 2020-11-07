package ru.ragnok123.sqlNukkitLib;

import ru.ragnok123.sqlNukkitLib.mysql.MySQLDatabase;
import ru.ragnok123.sqlNukkitLib.sqlite3.SQLite3Database;

public class SQLLib {
	
	public static Database init(SQLType type, SQLConnectionInfo info) {
		switch(type) {
		case MySQL:
			return new MySQLDatabase(info);
		case SQLite3:
			return new SQLite3Database(info);
		}
		return null;
	}
}