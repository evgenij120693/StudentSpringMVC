package ru.svetozarov.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class AuthenticationFilter implements Filter{
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
        HttpSession session = req.getSession(false);
        if(session == null ){
            this.context.log("Unauthorized access request");
            res.sendRedirect("/students/login");
        }else{
            if(session.getAttribute("id") == null){
                res.sendRedirect("/students/login");
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
