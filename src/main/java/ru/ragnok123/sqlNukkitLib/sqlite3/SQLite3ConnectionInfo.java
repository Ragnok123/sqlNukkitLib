package ru.ragnok123.sqlNukkitLib.sqlite3;

import java.io.File;

import ru.ragnok123.sqlNukkitLib.SQLConnectionInfo;

public class SQLite3ConnectionInfo implements SQLConnectionInfo {

	
	private File file = null;
	
	public SQLite3ConnectionInfo(File path) {
		this.file = path;
	}
	
	
	@Override
	public String getConnectionString() {
		return "jdbc:sqlite:"+this.file.getAbsolutePath();
	}

}
