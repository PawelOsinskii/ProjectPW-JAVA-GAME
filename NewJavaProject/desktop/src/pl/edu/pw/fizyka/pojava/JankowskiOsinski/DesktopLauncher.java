package pl.edu.pw.fizyka.pojava.JankowskiOsinski;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.edu.pw.fizyka.pojava.JankowskiOsinski.MyGdxGame;

public class DesktopLauncher {
	public static JFrame frame;
	public static JTextField login1;
	public static JTextField haslo1;

	static int magic = 0;
	public static void main(String[] arg) {

		frame = new JFrame("Game");
		frame.setLayout(new GridLayout(1, 3));
		JButton btn = new JButton("Login");
		login1 = new JTextField();
		haslo1 = new JTextField();
		

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String login = login1.getText();
				String password = haslo1.getText();

				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-g enerated catch block
						e1.printStackTrace();
					}
					Connection conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com/sql11231478",
							"sql11231478", "hVqTw9bwik");

					// sql injection
					java.sql.Statement stmt = conn.createStatement();
					// PreparedStatement
					ResultSet rs = stmt.executeQuery(
							"SELECT * FROM players WHERE login = '" + login + "' AND password = '" + password + "'");
					Boolean zalogowano = false;
					while (rs.next()) {
						System.out.println(rs.getInt("skill"));
						magic=rs.getInt("magic");
						/*
						 * int SHIELDING_START = 10; int MAGIC_LEVEL_START = 1; int ATTACK_LEVEL_START =
						 * 1;
						 */
						zalogowano = true;
						break;
					}

					rs.close();

					if (zalogowano) {
						LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
						config.width = 1200;
						config.height = 800;
						new LwjglApplication(new MyGdxGame(), config);
					}

					conn.close();
					frame.setVisible(false);
					frame.dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		frame.setSize(new Dimension(800, 600));

		frame.add(login1);
		frame.add(haslo1);
		frame.add(btn);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
