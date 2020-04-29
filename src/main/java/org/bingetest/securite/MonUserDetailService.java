package org.bingetest.securite;

import org.bingetest.daobinge.UtilisateurDao;
import org.bingetest.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class MonUserDetailService implements UserDetailsService {
	   @Autowired   
	   UtilisateurDao utilisateurDao;
	   
	   
	   @Override   
	   public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		   Utilisateur utilisateur = utilisateurDao.findByLogin(userName)               
			.orElseThrow(() ->                       
			new UsernameNotFoundException("Inconnu : " + userName));
	       	return new MonUserDetail(utilisateur);   } }
