package org.bingetest.controleur;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.bingetest.daobinge.UtilisateurDao;
import org.bingetest.modele.Role;
import org.bingetest.modele.Utilisateur;
import org.bingetest.securite.JwtUtil;
import org.bingetest.securite.MonUserDetailService;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class UtilisateurControleur {

	UtilisateurDao utilisateurdao;
	private AuthenticationManager authenticationManager;   
	private MonUserDetailService userDetailsService;   
	private JwtUtil jwtUtil;
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	public UtilisateurControleur(UtilisateurDao utilisateurdao, AuthenticationManager authenticationManager,
		MonUserDetailService userDetailsService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
	this.utilisateurdao = utilisateurdao;
	this.authenticationManager = authenticationManager;
	this.userDetailsService = userDetailsService;
	this.passwordEncoder = passwordEncoder;
	this.jwtUtil = jwtUtil;
}

	// Fin partie jwt
	
	@GetMapping("/listeutilisateur")
	@JsonView(MyJsonView.Utilisateur.class)
	public List<Utilisateur> listeutilisateur()
	{
		return utilisateurdao.findAll();
	}
	
	@GetMapping("utilisateur/listeutilisateur") // mis pour android studio, fait apparaitre les plges horaires aussi, attention
	@JsonView(MyJsonView.UtilisateurComplet.class)
	public List<Utilisateur> listeutilisateurcomplet()
	{
		return utilisateurdao.findAll();
	}
	
	
	@GetMapping("/utilisateur/{id}")
	@JsonView(MyJsonView.Utilisateur.class)
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
			// Plus besoin de communiquer le parametre "motdepasse" dans le Json si l'utilisateur existe déjà.
		}
		utilisateurdao.save(utilisateur);
		return true;
	}
	
	@PutMapping("/user/utilisateur") // Est la pour le test
	public int testuser(@RequestBody Utilisateur utilisateur)
	{
		utilisateurdao.saveAndFlush(utilisateur);
		return utilisateur.getId();
	}
	
	@PutMapping("/inscription") // Est la pour le test
	public int inscription(@RequestBody Utilisateur utilisateur)
	{
		utilisateur.setActif(true); // On le met en actif de base
		Role roleUser = new Role(); 
		roleUser.setId(1);
		utilisateur.setListerole(new HashSet<>());
		utilisateur.getListerole().add(roleUser); // On peut attribuer le role
		utilisateur.setMotdepasse(passwordEncoder.encode(utilisateur.getMotdepasse()));
		
		/// 3 lignes au desssus equivalent à role : [{id:1}]
		
		utilisateurdao.save(utilisateur);
		System.out.println(utilisateur.getMotdepasse());
		return utilisateur.getId();
		// return 0;
	}
	
	@GetMapping(value = "/utilisateur/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE) // Attribut produce permet de changer le type de retour de notre getmapping
	// La par exemple, on rend en image jpg.
    public ResponseEntity<byte[]> getImage(@PathVariable int id) throws IOException { // Le code 201 dit que l'object a bien été créer.
    	// Response entity aura en body un tableau d'octet et en contenu une image jpg

        Utilisateur utilisateur = utilisateurdao.findById(id).orElse(null);

        ClassPathResource imgFile = new ClassPathResource("private/images/avatars/" + utilisateur.getNomImageAvatar()); // on dit le lien de l'image dans la bdd
        // classpathresource permet de dire où se situe l'image application
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream()); // on transforme l'image en tableau de bytes

        return ResponseEntity
                .ok() // on dit ici ce qu'on veut comme contenu comme dit au dessus
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
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