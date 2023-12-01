package am.smartCode.lesson1.controller;

import am.smartCode.lesson1.repository.jdbcImpl.UserRepositoryJdbcImpl;
import am.smartCode.lesson1.service.UserService;
import am.smartCode.lesson1.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserService(new UserRepositoryJdbcImpl(DatabaseConnection.getInstance().getConnection()));

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String rememberMe = req.getParameter("remember");

            userService.login(username, password);




            if (rememberMe != null && rememberMe.equals("on")) {
                Cookie cookie = new Cookie("rememberMe", username + ":" + password);
                cookie.setMaxAge(60*60);
                resp.addCookie(cookie);
                req.getSession().setAttribute("username", username);
            }

            req.setAttribute("username", username);
            req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
