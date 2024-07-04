package com.bootcamp.weekly.service.impl.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bootcamp.weekly.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomJWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
		try {
			String jwtToken = extractJwtFromRequest(request);
			if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
				String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				ArrayList<SimpleGrantedAuthority> roles = jwtTokenUtil.getRolesFromToken(jwtToken);				
				UserDetails userDetails = new User(username, "", roles);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", e);
			log.error("JWT" + e.getLocalizedMessage());;
		} catch(BadCredentialsException e) {
			request.setAttribute("exception", e);
			log.error("JWT" + e.getLocalizedMessage());;
		}
		chain.doFilter(request, response);
	}
	
	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7, bearerToken.length());
		return bearerToken;
	}

}
