package com.soc.exames.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	private static Connection connection = null;
    
    public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/soc", "root", "root");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
