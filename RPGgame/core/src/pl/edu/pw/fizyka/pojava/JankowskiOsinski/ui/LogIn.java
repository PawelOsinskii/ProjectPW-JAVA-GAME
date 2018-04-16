package pl.edu.pw.fizyka.pojava.JankowskiOsinski.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.edu.pw.fizyka.pojava.JankowskiOsinski.map.MapScreen;

public class LogIn {

	public static int gold;
	public static int attack;
	public static int magic;
	
	public static boolean isLogin(String login, String password) {
		boolean isLog = false;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			// 
			Connection conn = DriverManager.getConnection("jdbc:mysql://mn26.webd.pl/marekb93_rpggame",
					"marekb93_rpggame", "xBGG)2Jn&b?E?kC+");
			Statement stmt = conn.createStatement(); 
			// PreparedStatement
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM players WHERE login = '" + login + "' AND password = '" + password + "'");
			isLog = rs.next();
			gold = rs.getInt("gold");
			attack = rs.getInt("skill");
			magic = rs.getInt("magic");
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

	public static void loadStatsFromServer(MapScreen mapScreen) {
		// pobieranie z bazy danych statystyk gracza
		mapScreen.getKnight().setAttackLevel(LogIn.gold);
		mapScreen.getKnight().setGold(LogIn.attack);
		mapScreen.getKnight().setMagicLevel(LogIn.magic);
	}

}
