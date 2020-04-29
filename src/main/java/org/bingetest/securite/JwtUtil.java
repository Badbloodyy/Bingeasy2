package org.bingetest.securite;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	
	@Value("${jwt.secret}")   
	private String secret;
	
	//Retourne le corps du token   
	public Claims extractionDuCorpDuToken(String token) {  // Claims = Format que l'on extrait de notre token. Format spécial que l'on s'impose 
		return Jwts.parser()               //  et corps du token du coup, le payload comme on l'a vu auparavant
			.setSigningKey(secret)               
			.parseClaimsJws(token)               
			.getBody();   
		}
	
	//Retourne un token partir d'un userdetails
	public String generateToken(UserDetails userDetails) {
	   Map<String, Object> tokenData = new HashMap<>(); 
	   //ici vous pouvez rajouter tout ce que vous voulez tant que c'est pas le mot de passe, ici on met les roles par exemple.  
	   tokenData.put("roles", userDetails.getAuthorities().stream()       
		.map(GrantedAuthority::getAuthority)       
		.collect(Collectors.joining(",")));
	   
	   // 'role' : 'ROLE_USER, ROLE_ADMIN'
	   
	   
	   return Jwts.builder()    // Le construit a priori    
		.setClaims(tokenData)       
		.setSubject(userDetails.getUsername())       
		.setIssuedAt(new Date(System.currentTimeMillis()))       
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))       
		.signWith(SignatureAlgorithm.HS256, secret).compact(); 
	   }
	
	//Retourne vrai si le token n'a pas dépassé la date d'expiration 
	private Boolean tokenNonDepasseDateExpiration(String token) {
		return extractionDuCorpDuToken(token)            
				.getExpiration()            
				.before(new Date()); 
		}
	
	//Retourne vrai si le nom de l'utilisateur tentant de se connecter correspond 
	//au subject du corp du token et si la date d'expiration n'est pas passée. 
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractionDuCorpDuToken(token).getSubject();   
		return (username.equals(userDetails.getUsername()) && !tokenNonDepasseDateExpiration(token)); 
	}


	 
}
