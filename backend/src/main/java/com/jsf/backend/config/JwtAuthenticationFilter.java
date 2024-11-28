package com.jsf.backend.config;
import com.jsf.backend.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Check if there is an Authorization header
        String authHeader = request.getHeader("Authorization");

        // If the header contains a Bearer token, try to validate it
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // Extract the username from the token
                String username = jwtTokenUtil.extractUsername(token);

                if (username != null) {
                    // Create authentication token and set it in the security context
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                // Log the exception to understand why the token is invalid
                logger.error("Invalid or expired JWT token", e);

                // If token is invalid or expired, clear the security context
                SecurityContextHolder.clearContext();

                // Respond with 403 Forbidden and an error message
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Invalid or expired token");
                return; // Do not continue the filter chain
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
