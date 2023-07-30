//package com.example.servlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.example.entity.Player;
//
//import java.util.*;
//
//@WebServlet(name = "ScoreboardServlet", urlPatterns = { "/scoreboard" })
//public class ScoreboardServlet extends HttpServlet {
//	private static final String url = "jdbc:mysql://127.0.0.1:3306/GuessNumber?useUnicode=true&characterEncoding=utf8";
//	private static final String username = "root";
//	private static final String password = "1032001";
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<Player> players = null;
//		try {
//			players = getPlayers();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		request.setAttribute("players", players);
//		request.getRequestDispatcher("scoreboard.jsp").forward(request, response);
////		request.getRequestDispatcher("game.jsp").forward(request, response);
//	}
//
//	public Connection getConnection() {
//		Connection connection = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			connection = DriverManager.getConnection(url, username, password);
//		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println("Khong tim thay Driver!");
//			e.printStackTrace();
//		}
//		return connection;
//	}
//
//	private List<Player> getPlayers() throws ClassNotFoundException, SQLException {
//		List<Player> players = new ArrayList<>();
//
//		try {
//			Connection connection = getConnection();
//			String selectPlayer = "SELECT * FROM players ORDER BY guesses";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer);
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//				Player player = new Player();
//				player.setName(rs.getString("name"));
//				player.setGuesses(rs.getInt("guesses"));
//				players.add(player);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return players;
//	}
//}
