package org.bingetest.securite;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component 
public class JwtRequestFilter extends OncePerRequestFilter {     
	private MonUserDetailService userDetailsService;   
	private JwtUtil jwtUtil;

@Autowired   
public JwtRequestFilter(MonUserDetailService userDetailsService, JwtUtil jwtUtil) {       
	this.userDetailsService = userDetailsService;       
	this.jwtUtil = jwtUtil;   
	}

@Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)       
	throws ServletException, IOException {
	   final String authorizationHeader = request.getHeader("Authorization");
	   String username = null;
	   String jwt = null;
	   if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {  // Si l'autorisation dans header est vide et commence par bearer      
		   jwt = authorizationHeader.substring(7);       // On enleve les 7 premiers caracteres
		   username = jwtUtil.extractionDuCorpDuToken(jwt).getSubject(); // On recupere le nom de l'utilisateur  
		   }

if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, 
        userDetails.getAuthorities());
        usernamePasswordAuthenticationToken                   
        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);       
        }   
    }  
	
chain.doFilter(request, response); }

}
