package pl.edu.pw.fizyka.pojava.JankowskiOsinski.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;

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

	public static void loadStatsFromServer(MapScreen mapScreen, ResultSet rs) throws SQLException {
		// pobieranie z bazy danych statystyk gracza
		// do pobrania dane, cos nie dziala
		mapScreen.getKnight().setAttackLevel(rs.getInt("skill"));
		mapScreen.getKnight().setGold(rs.getInt("gold"));
	}

}
