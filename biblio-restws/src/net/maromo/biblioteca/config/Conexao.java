package net.maromo.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/biblioteca?useTimezone=true&serverTimezone="
				+ "UTC&useSSL=false&profileSQL=true","root","root");
	}
}
