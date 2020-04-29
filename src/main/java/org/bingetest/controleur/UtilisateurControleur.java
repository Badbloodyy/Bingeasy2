package org.bingetest.controleur;


import java.util.List;
import java.util.Optional;

import org.bingetest.daobinge.UtilisateurDao;
import org.bingetest.modele.Utilisateur;
import org.bingetest.securite.JwtUtil;
import org.bingetest.securite.MonUserDetailService;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class UtilisateurControleur {

	UtilisateurDao utilisateurdao;
	private AuthenticationManager authenticationManager;   
	private MonUserDetailService userDetailsService;   
	private JwtUtil jwtUtil;
	

	@Autowired
	public UtilisateurControleur(UtilisateurDao utilisateurdao, AuthenticationManager authenticationManager,
		MonUserDetailService userDetailsService, JwtUtil jwtUtil) {
	this.utilisateurdao = utilisateurdao;
	this.authenticationManager = authenticationManager;
	this.userDetailsService = userDetailsService;
	this.jwtUtil = jwtUtil;
}

	// Fin partie jwt
	
	@GetMapping("/listeutilisateur")
	@JsonView(MyJsonView.Utilisateur.class)
	public List<Utilisateur> listeutilisateur()
	{
		return utilisateurdao.findAll();
	}
	
	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable int id)
	{
		return utilisateurdao.findById(id).orElse(null);
	}
	
	@PostMapping("/ajouterinfouser")
	public boolean ajoutinfouser(@RequestBody Utilisateur utilisateur) throws Exception
	{
		if (utilisateur.getId() != null)
		{
			Utilisateur utilbdd = utilisateurdao.findById(utilisateur.getId()).orElse(null);
			utilisateur.setMotdepasse(utilbdd.getMotdepasse());
			
		}
		utilisateurdao.save(utilisateur);
		return true;
	}
	
	/* @PostMapping("/modifierutilisateur")
	public boolean ajoutuser(@RequestBody Utilisateur utilisateur) throws Exception
	{
		Utilisateur pdd = utilisateurdao.findById(utilisateur.getId()).orElse(null);
		pdd.setListeseriea(utilisateur.getListeseriea());
		utilisateurdao.save(utilisateur);
		return true;
	} */
	
	/* @GetMapping("/utilisateur/login/{login}")
	public Optional<Utilisateur> getUtilisateur(@PathVariable String login)
	{
		return utilisateurdao.findByLogin(login);
	} */
	
	/* @GetMapping("/utilisateur/login/{login}")
	public Utilisateur getUtilisateur(@PathVariable String login)
	{
		return utilisateurdao.findByLogin(login);
	} */
	
	@GetMapping("/utilisateur/numero2")
	public Utilisateur getUtilisateur()
	{
		return utilisateurdao.requeteSpecialUtilisateur2();
	}
	
	
	 @PostMapping("/authentification")   
	 public String authentification(@RequestBody Utilisateur utilisateur) throws Exception { /*ResponseEntity*/
	       try {           
	    	    authenticationManager.authenticate(                   
	    		new UsernamePasswordAuthenticationToken(                           
	    		utilisateur.getLogin(), utilisateur.getMotdepasse()));     
	    	    
	    	    final UserDetails userDetails = userDetailsService               
	 	    		   .loadUserByUsername(utilisateur.getLogin());
	 	       
	 	       return jwtUtil.generateToken(userDetails);   // ResponseEntity.ok()
	    	    }       
	       catch (BadCredentialsException e) {           
	    	   throw new Exception("Pseudo ou mot de passe incorrect", e);       
	    	   }
	       
	       
	       }
	
	}