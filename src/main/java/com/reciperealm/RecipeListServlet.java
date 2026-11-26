package com.reciperealm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recipes")
public class RecipeListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sql = "SELECT title, ingredients, steps FROM recipes ORDER BY id DESC LIMIT 50";
        List<Map<String, String>> recipes = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rows = statement.executeQuery()) {
            while (rows.next()) {
                Map<String, String> recipe = new HashMap<>();
                recipe.put("title", rows.getString("title"));
                recipe.put("ingredients", rows.getString("ingredients"));
                recipe.put("steps", rows.getString("steps"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new ServletException("Could not load recipes", e);
        }
        request.setAttribute("recipes", recipes);
        request.getRequestDispatcher("/WEB-INF/views/recipes.jsp").forward(request, response);
    }
}
