package com.reciperealm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddRecipeServlet")
public class AddRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (userId(request) == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/addRecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = userId(request);
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String ingredients = request.getParameter("ingredients");
        String steps = request.getParameter("steps");

        if (title == null || title.isBlank() || title.strip().length() > 100
                || invalidRecipeText(ingredients) || invalidRecipeText(steps)) {
            response.sendRedirect(request.getContextPath() + "/AddRecipeServlet?error=1");
            return;
        }

        String sql = "INSERT INTO recipes (title, ingredients, steps, user_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title.strip());
            statement.setString(2, ingredients.strip());
            statement.setString(3, steps.strip());
            statement.setInt(4, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Could not save recipe", e);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp?saved=1");
    }

    private static Integer userId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : (Integer) session.getAttribute("userId");
    }

    private static boolean invalidRecipeText(String value) {
        return value == null || value.isBlank() || value.getBytes(StandardCharsets.UTF_8).length > 65_535;
    }
}
