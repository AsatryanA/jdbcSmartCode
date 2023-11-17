package am.smartCode.lesson1.Controller;

import am.smartCode.lesson1.Repository.jdbcImpl.UserRepositoryJdbcImpl;
import am.smartCode.lesson1.Service.UserService;
import am.smartCode.lesson1.util.CookieUtil;
import am.smartCode.lesson1.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserService(new UserRepositoryJdbcImpl(DatabaseConnection.getInstance().getConnection()));
            final String REMEMBER_ME = "rememberMe";
            String value = CookieUtil.getValue(req.getCookies(), REMEMBER_ME);
            if (value == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                String[] split = value.split(":");
                String username = split[0];
                String password = split[1];
                userService.login(username, password);
                Cookie newCookie = new Cookie("rememberMe", username + ":" + password);
                newCookie.setMaxAge(60 * 60);
                resp.addCookie(newCookie);
                req.getSession().setAttribute("username", username);
                req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);


            }
        } catch (RuntimeException ex) {
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


