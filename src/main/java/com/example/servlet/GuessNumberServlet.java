package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.entity.Player;

@WebServlet(name = "/GuessNumberServlet", urlPatterns = { "/game" })
public class GuessNumberServlet extends HttpServlet {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/GuessNumber";
	private static final String username = "root";
	private static final String password = "";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		// Tạo một số ngẫu nhiên nếu chưa có
		if (session.getAttribute("number") == null) {
			session.setAttribute("number", new Random().nextInt(1000) + 1);
			session.setAttribute("guesses", 0);
		}

		// Lấy số người dùng đoán và so sánh số ngẫu nhiên
		int guess = Integer.parseInt(request.getParameter("guess"));
		int number = (Integer) session.getAttribute("number");
		int guesses = (Integer) session.getAttribute("guesses") + 1;
//		Player player = new Player();
		String message;
		if (guess < number) {
			message = "Số vừa đoán nhỏ hơn đáp án";
			String username = request.getParameter("username");
			if (username != null && !username.isEmpty()) {
				Player player = new Player();
				player.setName(username);
				player.setGuesses(guesses);
				savePlayer(player);
			}

		} else if (guess > number) {
			message = "Số vừa đoán lớn hơn đáp án";
			String username = request.getParameter("username");
			if (username != null && !username.isEmpty()) {
				Player player = new Player();
				player.setName(username);
				player.setGuesses(guesses);
				savePlayer(player);
			}
		} else {
			message = "Chúc mừng! Bạn đã đoán đúng số sau " + guesses + " lần đoán";
			session.removeAttribute("number");
			session.removeAttribute("guesses");

			String username = request.getParameter("username");
			if (username != null && !username.isEmpty()) {
				session.setAttribute("username", username);
				Player player;
				try {
					player = getPlayerByUsername(username);
					if (player == null) { // Nếu người chơi chưa tồn tại trong DB
						player = new Player();
						player.setName(username);
						player.setGuesses(1); // Đây là lần đầu tiên người chơi này đoán
					} else { // Nếu người chơi đã tồn tại trong DB
						player.setGuesses(player.getGuesses() + 1); // Tăng số lần đoán lên
					}
					player.setGuesses(guesses);
					savePlayer(player); // Cập nhật thông tin người chơi trong DB
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			session.removeAttribute("username");
		}
		session.setAttribute("message", message);
		session.setAttribute("guesses", guesses);
		try {
			List<Player> players = getPlayers();
			session.setAttribute("players", players);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		// Chuyển hướng trang
		response.sendRedirect("game.jsp");
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Khong tim thay Driver!");
			e.printStackTrace();
		}
		return connection;
	}

	private Player getPlayerByUsername(String username) throws ClassNotFoundException, SQLException {
		Player player = null;
		try {
			Connection connection = getConnection();
			String selectPlayer = "SELECT * FROM players WHERE name = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				player = new Player();
				player.setName(rs.getString("name"));
				player.setGuesses(rs.getInt("guesses"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}

	private void savePlayer(Player player) {
		String updatePlayer = "UPDATE players SET guesses = ? WHERE name = ?";
		String insertPlayer = "INSERT INTO players (name, guesses) VALUES (?, ?)";
		Connection connection = getConnection();
		try {
			// Truy van trong database thong PrepareStatement
			PreparedStatement preparedStatement = connection.prepareStatement(updatePlayer);
			preparedStatement.setInt(1, player.getGuesses());
			preparedStatement.setString(2, player.getName());
			int updatedRow = preparedStatement.executeUpdate();

			if (updatedRow == 0) {
				preparedStatement = connection.prepareStatement(insertPlayer);
				preparedStatement.setString(1, player.getName());
				preparedStatement.setInt(2, player.getGuesses());
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Player> getPlayers() throws ClassNotFoundException, SQLException {
		List<Player> players = new ArrayList<>();

		try {
			Connection connection = getConnection();
			String selectPlayer = "SELECT * FROM players ORDER BY guesses";

			PreparedStatement preparedStatement = connection.prepareStatement(selectPlayer);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Player player = new Player();
				player.setName(rs.getString("name"));
				player.setGuesses(rs.getInt("guesses"));
				players.add(player);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return players;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Player> players = null;
		try {
			players = getPlayers();
			request.setAttribute("players", players);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("game.jsp").forward(request, response);
	}
}
