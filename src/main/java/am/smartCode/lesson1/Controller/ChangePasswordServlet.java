package am.smartCode.lesson1.Controller;

import am.smartCode.lesson1.Repository.impl.UserRepositoryImpl;
import am.smartCode.lesson1.Service.UserService;
import am.smartCode.lesson1.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserService userService = new UserService(new UserRepositoryImpl(DatabaseConnection.getInstance().getConnection()));

            String newPassword = req.getParameter("newPassword");
            String repeatPassword = req.getParameter("repeatPassword");
            String username = req.getSession().getAttribute("username").toString();

            if (username == null){
                req.setAttribute("message", "Please login");
                req.getRequestDispatcher("i ndex.jsp").forward(req, resp);
            }
            if (!newPassword.equals(repeatPassword)) {
                req.setAttribute("message", "Passwords are not equal");
                req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);
            }
            userService.changePassword(username, newPassword);
            req.setAttribute("message", "Password has been successfully changed");
            req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException ex) {
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);
        }


    }
}