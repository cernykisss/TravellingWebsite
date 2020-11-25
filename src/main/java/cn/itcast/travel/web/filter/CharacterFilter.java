package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        String method = httpServletRequest.getMethod();
        if (method.equalsIgnoreCase("post")) {
            httpServletRequest.setCharacterEncoding("utf-8");
        }
        httpServletResponse.setContentType("text/html;charset=utf-8");
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void destroy() {
    }


    public void init(FilterConfig config) throws ServletException {
    }

}
