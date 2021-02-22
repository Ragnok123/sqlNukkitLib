package ru.ragnok123.sqlNukkitLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import ru.ragnok123.sqlNukkitLib.utils.OrderType;
import ru.ragnok123.sqlNukkitLib.utils.Pair;

public abstract class Database {
	
	protected SQLConnectionInfo connectInfo;
	protected Connection connection;
	
	protected final ExecutorService asyncQuery = Executors.newCachedThreadPool();
	
	public Database(SQLConnectionInfo info) {
		this.connectInfo = info;
	}
	
	public abstract void connect();
	
	public void close() {
		this.asyncQuery.execute(() -> {
			try {
				this.connection.close();
			} catch (SQLException e) {
			}
		});
	}
	
	public void query(String query) {
		this.asyncQuery.execute(() -> {
			try {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.executeUpdate();
				statement.close();
			} catch(SQLException e) {}
		});
	}
	
	public void insert(final String table, final Pair[] pairs) {

		this.asyncQuery.execute(() -> {
			try {
				
				String separator = ",";
				String delimator = "";
				String delimator2 = "";
				
				String[] keys = {};
				Object[] values = {};
				
				for(int i = 0; i <= pairs.length; i++) {
					keys[i] = pairs[i].getKey();
					values[i] = pairs[i].getValue();
				}
				
				StringBuilder b1 = new StringBuilder();
				for(String key : keys) {
					b1.append(delimator).append(key);
					delimator = separator;
				}
				
				StringBuilder b2 = new StringBuilder();
				for(Object insertValue : values) {
					String value = "'" + String.valueOf(insertValue) +"'";
					b2.append(delimator2).append(value);
				}
				
				PreparedStatement statement = connection.prepareStatement("INSERT INTO `"+table+"` ("+b1.toString()+") VALUES ("+b2.toString()+");");
				statement.executeUpdate();
				statement.close();
			} catch(SQLException e) {
				
			}
		});
	}
	
	public void select(final String table, final String where, final String selector, final Consumer<HashMap<String,Object>> callback) {
		this.asyncQuery.execute(() -> {
			try{
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `"+table+"` WHERE `"+where+"` = '" + selector + "'");
	            ResultSet result = statement.executeQuery();
	            ResultSetMetaData md = result.getMetaData();
	            int columns = md.getColumnCount();
	            HashMap<String, Object> row = new HashMap<String, Object>();
	            while (result.next())
				{
	                for (int i = 1; i <= columns; ++i)
					{
	                    row.put(md.getColumnName(i), result.getObject(i));
	                }
	            }
	            statement.close();
	            result.close();
	            asyncQuery.execute(() -> {
	            	try {
	            		callback.accept(row);
            		} catch(Exception e) {}
	            });
	        }catch (SQLException ex){
	            ex.printStackTrace();
	        }
		});
	}
	
	public void selectTop(final String table, final String order, final int limit, final OrderType orderType, final Consumer<HashMap<Integer, HashMap<String, Object>>> callback) {
		this.asyncQuery.execute(() -> {
			try{
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM `"+table+"` ORDER BY cast("+order+" as unsigned) "+orderType.getType()+" LIMIT "+String.valueOf(limit));
				ResultSet result = statement.executeQuery();
				ResultSetMetaData md = result.getMetaData();
				HashMap<Integer, HashMap<String, Object>> rowCount= new HashMap<Integer, HashMap<String, Object>>();
				HashMap<String, Object> row = new HashMap<String, Object>();
				int columns = md.getColumnCount();
				int currentRow = 0;
				while(result.next()) {
					for(int i = 1; i  <= columns; i++) {
						row.put(md.getColumnName(i), result.getObject(i));
					}
					rowCount.put(currentRow, row);
					currentRow++;
				}
				statement.close();
	            result.close();
	            asyncQuery.execute(() -> {
	            	try {
	            		callback.accept(rowCount);
            		} catch(Exception e) {}
	            });
			} catch (SQLException ex){
	            ex.printStackTrace();
	        }
		});
	}

	
	public void update(final String table, String where, String selector, Pair[] pairs) {
		this.asyncQuery.execute(() -> {
			for(Pair pair : pairs) {
				try {
					PreparedStatement statement = connection.prepareStatement("UPDATE `"+table+"` SET `"+pair.getKey()+"`='"+pair.getValue()+"' WHERE `"+where+"`='"+selector+"'");
					statement.executeUpdate();
					statement.close();
				} catch(SQLException ex) {
					
				}
			}
		});
	}
	
}