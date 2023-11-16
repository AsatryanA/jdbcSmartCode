package am.smartCode.lesson1.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest)servletRequest);
        HttpServletResponse response = ((HttpServletResponse)servletResponse);

        if (request.getSession().getAttribute("username") == null){
            request.setAttribute("message", "Please login first");
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }else  filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(
                "Filter destroy"
        );
    }
}
