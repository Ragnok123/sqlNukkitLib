#sqlNukkitLib

 Simple SQL library for Nukkit server
 
 Currently, library supports only two types of db:
 
  1) MySQL
 
  2) SQLite3
 
###Initialization:

 To connect to your database, you can use this:
 
```java
Database db = nsl.init(SQLType type, SQLConnectionInfo info);
```
 where ```SQLType`` can be ```SQLType.MySQL`` or ```SQLType.SQLite3``
 
 For mysql, arguments for ```SQLConnectionInfo`` are 
 
```java
info = new MySQLConnectionInfo(String ip, String user, String password, String database, String port);
```
 
 and for sqlite3 are 
 
```java
info = new SQLite3ConnectionInfo(new File(path_to_db));
```
 
###Connection:

You can connect to db simply with ```db.connect();`` and dissconnect with ```db.close();``


##API example:

To insert data, use ```db.insert();`` method. Example:

```java
db.insert("players", new Pair[]{
	new Pair("nickname", "Ragnok"), new Pair("money", 0.0), new Pair("rank","User")
});
```

To select data, use ```db.select();`` method. Example:

```java
db.select("players", "nickname", "Ragnok", map -> {
	if(map.isEmpty()){
		getLogger().info("This player doesn't exists");
	} else {
		double money = (double)map.get("money");
		String rank = (String)map.get("rank");
		
		getLogger().info("Player Ragnok information:");
		getLogger().info(">> Money: " + String.valueOf(money));
		getLogger().info(">> Rank: " + rank);
	}
});
```

To update data, use ```db.update()`` method. Example:

```java
db.update("players", "nickname", "Ragnok", new Pair[]{
	new Pair("money", 100.0), new Pair("rank", "VIP")
});
``