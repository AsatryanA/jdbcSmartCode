package am.smartCode.lesson1.Controller;

import am.smartCode.lesson1.Model.User;
import am.smartCode.lesson1.Repository.jdbcImpl.UserRepositoryJdbcImpl;
import am.smartCode.lesson1.Repository.jpaImpl.UserRepositoryJpaImpl;
import am.smartCode.lesson1.Service.UserService;
import am.smartCode.lesson1.util.DatabaseConnection;
import am.smartCode.lesson1.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserService(new UserRepositoryJpaImpl(HibernateUtil.getSessionFactory()));

            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("repeatPassword");
            String age = req.getParameter("age");
            if (!password.equals(repeatPassword)) {
                req.setAttribute("message", "Passwords are not equal");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setAge(Integer.parseInt(age));
            userService.register(user);


            req.getRequestDispatcher("index.jsp").forward(req, resp);

        } catch (RuntimeException ex) {
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }

    }
}
