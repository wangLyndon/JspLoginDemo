package Servlet;

import Model.User;
import Util.JDBCTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String userPwd = req.getParameter("password");
        String userAgeStr = req.getParameter("userAge");
        boolean hasError = false;

        if (userName == null || userName.isEmpty()) {
            req.setAttribute("errorUsername", "Username is required.");
            hasError = true;
        }

        if (userPwd == null || userPwd.isEmpty()) {
            req.setAttribute("errorPassword", "Password is required.");
            hasError = true;
        }

        int userAge = 0;
        if (userAgeStr == null || userAgeStr.isEmpty()) {
            req.setAttribute("errorAge", "Age is required.");
            hasError = true;
        } else {
            try {
                userAge = Integer.parseInt(userAgeStr);
            } catch (NumberFormatException e) {
                req.setAttribute("errorAge", "Invalid age format.");
                hasError = true;
            }
        }

        if (hasError) {
            RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
            rd.forward(req, resp);
            return;
        }

        User user = new User(userName, userPwd, userAge);
        if (registerUser(user)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            req.setAttribute("errorUsername", "User already exists");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private boolean registerUser(User user){
        boolean result = false;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCTool.getConnection();
            String sql = "INSERT INTO userinfo (userName, userPwd, userAge) VALUES (?, ?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, user.getUserName());
            st.setString(2, user.getUserPws());
            st.setInt(3, user.getUserAge());

            if (st.executeUpdate() > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCTool.closeAllConnections(conn, st, null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
