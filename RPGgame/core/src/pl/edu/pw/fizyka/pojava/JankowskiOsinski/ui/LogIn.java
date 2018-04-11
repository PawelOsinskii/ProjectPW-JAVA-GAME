package pl.edu.pw.fizyka.pojava.JankowskiOsinski.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LogIn {

	public static boolean isLogin(String login, String password) {
		boolean isLog = false;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com/sql11231478",
					"sql11231478", "hVqTw9bwik");

			// sql injection
			Statement stmt = conn.createStatement();
			// PreparedStatement
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM players WHERE login = '" + login + "' AND password = '" + password + "'");
			isLog = rs.next();

			while (rs.next()) {
				System.out.println(rs.getInt("skill"));
				break;
			}
			rs.close();
			conn.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return isLog;
	}

}
