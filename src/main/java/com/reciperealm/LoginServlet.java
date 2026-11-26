package com.reciperealm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || password == null || password.isEmpty() || password.length() > 128) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=1");
            return;
        }

        String sql = "SELECT id, password, password_hash, role FROM users WHERE email = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email.strip().toLowerCase(Locale.ROOT));
            try (ResultSet user = statement.executeQuery()) {
                if (user.next()) {
                    int id = user.getInt("id");
                    String hash = user.getString("password_hash");
                    if (hash != null ? Passwords.matches(password, hash)
                            : password.equals(user.getString("password"))) {
                        if (hash == null) {
                            upgradePassword(connection, id, password);
                        }
                        HttpSession session = request.getSession();
                        request.changeSessionId();
                        session.setAttribute("userId", id);
                        session.setAttribute("role", user.getString("role"));
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Login failed", e);
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp?error=1");
    }

    private void upgradePassword(Connection connection, int userId, String password) throws SQLException {
        String sql = "UPDATE users SET password_hash = ?, password = '' WHERE id = ? AND password_hash IS NULL";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Passwords.hash(password));
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }
}
