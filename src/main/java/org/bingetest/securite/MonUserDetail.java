package org.bingetest.securite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bingetest.modele.Role;
import org.bingetest.modele.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MonUserDetail implements UserDetails {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1452226625403518864L;
	private String userName;   
	   private String password;   
	   private boolean active;   
	   private List<GrantedAuthority> authorities;
	   
	   public MonUserDetail(Utilisateur utilisateur) {       
		   this.userName = utilisateur.getLogin();
		   this.password = utilisateur.getMotdepasse();       
		   this.active = utilisateur.isActif();       
		  /* this.authorities = utilisateur.getListeRole().stream()               
				   .map(Role::getNom)               
				   .map(SimpleGrantedAuthority::new)               
				   .collect(Collectors.toList());   */ // C'est un stream donc vu qu'on a pas vu, on le met pas
		   this.authorities = new ArrayList<>();
		   
		   for (Role role : utilisateur.getListerole())
		   {
			   this.authorities.add(new SimpleGrantedAuthority(role.getNom()));
		   }
		   }
	   
		// ...  surcharges de tous les getters & setters

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() { // Si le compte n'est plus "bon"
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isAccountNonLocked() { // Si le compte n'a pas été locké aprés des connexions successive ou s'il a été moementanément désactivé
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() { // Par rapport a des droits, savoir s'ils ont été enlevé, genre quelqu'un qui quitte une entreprise
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isEnabled() { // Est ce que le compte est encore actif ou non.
		// TODO Auto-generated method stub
		return active;
	}
	   }
