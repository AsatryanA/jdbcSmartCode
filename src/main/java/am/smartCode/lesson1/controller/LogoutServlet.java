package am.smartCode.lesson1.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession() != null){
            req.getSession().invalidate();
            req.setAttribute("message", "You have been successfully logged out");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
