package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
	
	private static Connection connection = null;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/kata";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "2155";
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				connection.setAutoCommit(false);
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Не удалось создать подключение к БД. " + e);
			}
		}
		return connection;
	}
	public static User createUser (ResultSet rs) throws Exception {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setLastName(rs.getString("last_name"));
		return user;
	}
}
