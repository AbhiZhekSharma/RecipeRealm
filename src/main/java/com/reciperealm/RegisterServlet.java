package com.reciperealm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.isBlank() || name.strip().length() > 100
                || email == null || email.strip().length() > 100
                || !email.strip().matches("[^@\\s]+@[^@\\s]+")
                || password == null || password.length() < 8 || password.length() > 128) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=invalid");
            return;
        }

        String sql = "INSERT INTO users (name, email, password, password_hash) VALUES (?, ?, '', ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name.strip());
            statement.setString(2, email.strip().toLowerCase(Locale.ROOT));
            statement.setString(3, Passwords.hash(password));
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=duplicate");
            return;
        } catch (SQLException e) {
            throw new ServletException("Registration failed", e);
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp?registered=1");
    }
}
