package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
	
	private Connection connection = Util.getConnection();
	private PreparedStatement pst = null;
	
	public UserDaoJDBCImpl() {
	
	}
	
	public void createUsersTable() {
		try {
			connection = getConnection();
			PreparedStatement pst = connection.prepareStatement("CREATE TABLE IF NOT EXISTS kata.users ( " + "id BIGINT NOT NULL AUTO_INCREMENT, " + "name VARCHAR(50) NOT NULL, " + "lastName VARCHAR(50) NOT NULL, " + "age TINYINT NOT NULL, " + "PRIMARY KEY (id)); ");
			pst.executeUpdate();
		} catch(Exception e) {
		
		}
		
	}
	
	public void dropUsersTable() {
		try {
			connection = getConnection();
			pst = connection.prepareStatement("DROP TABLE  kata.users;");
			pst.executeUpdate();
		} catch(Exception e) {
		
		}
	}
	
	public void saveUser(String name, String lastName, byte age) {
		try {
			connection = getConnection();
			pst = connection.prepareStatement("INSERT INTO Users (name, lastName, age) values (?,?,?);");
			pst.setString(1, name);
			pst.setString(2, lastName);
			pst.setInt(3, age);
			pst.setInt(3, age);
			pst.executeUpdate();
		} catch(Exception e) {
		
		}
	}
	
	@Override
	public void removeUserById(long id) {
		try {
			connection = getConnection();
			pst = connection.prepareStatement("DELETE FROM kata.users WHERE id=?");
			pst.setInt(1, (int) id);
			pst.executeUpdate();
		} catch(Exception e) {
		
		}
		
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM kata.users";
			ResultSet resultSet = statement.executeQuery(sql);
			
			connection.commit();
			
			while(resultSet.next()) {
				long id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String lastName = resultSet.getString("lastName");
				byte age = (byte) resultSet.getInt("age");
				User user = new User(name, lastName, age);
				user.setId(id);
				users.add(user);
			}
		} catch(SQLException e) {
			
		}
		return users;
	}
	
	public void cleanUsersTable() {
		try {
	
			connection = getConnection();
			pst = connection.prepareStatement("TRUNCATE TABLE kata.users;");
			pst.executeUpdate();

		} catch(Exception e) {
		
		}
	}
}
