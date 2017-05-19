package model.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mateu on 19.05.2017.
 */
public class CustomEncodingFilter extends OncePerRequestFilter {

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);

    }

}