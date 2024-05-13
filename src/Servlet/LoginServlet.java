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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String userPwd = req.getParameter("password");
        boolean hasError = false;

        if (userName == null || userName.isEmpty()){
            req.setAttribute("errorUsername", "Username is required");
            hasError = true;
        }

        if (userPwd == null || userPwd.isEmpty()){
            req.setAttribute("errorPassword", "Password is required");
            hasError = true;
        }

        if (hasError){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        User user = validateLogin(userName, userPwd);

        if (user != null){
            req.setAttribute("username", user.getUserName());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            req.setAttribute("errorUsername", "Wrong username or password");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private User validateLogin(String userName, String userPwd){
        User user = null;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCTool.getConnection();
            String sql = "SELECT * FROM userinfo WHERE userName = ? AND userPwd = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, userPwd);

            rs = st.executeQuery();

            if (rs.next()){
                user = new User(
                        rs.getString("userName"),
                        rs.getString("userPwd"),
                        rs.getInt("userAge")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                JDBCTool.closeAllConnections(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
