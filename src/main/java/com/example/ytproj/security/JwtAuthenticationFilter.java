package com.example.ytproj.security;

import java.io.IOException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenHelper jt;

    @Autowired
    UserDetailsService uds;
    static String pubtoken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String reqtoken = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (reqtoken != null && reqtoken.startsWith("Bearer")) {

            token = reqtoken.substring(7);
            pubtoken = token;
            try {
                username = jt.getUsernameFromToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails ud = uds.loadUserByUsername(username);
            if (jt.validateToken(token, ud)) {
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(uds, null,
                        ud.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            } else {
                System.out.println("Jwt not valid");
            }
        } else {
//            System.out.println("Username is null");
        }
        filterChain.doFilter(request, response);
    }

    public boolean check() {
        try {
            DecodedJWT jwt = JWT.decode(pubtoken);
            if (jwt == null || jwt.getExpiresAt().before(new Date())) {
                return true;
            }
        } catch (com.auth0.jwt.exceptions.JWTDecodeException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
