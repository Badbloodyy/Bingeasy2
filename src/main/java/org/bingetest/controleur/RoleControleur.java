package org.bingetest.controleur;

import java.util.List;
import org.bingetest.daobinge.RoleDao;
import org.bingetest.modele.Role;
import org.bingetest.securite.JwtUtil;
import org.bingetest.securite.MonUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class RoleControleur {

	RoleDao roledao;

	

	@Autowired
	public RoleControleur(RoleDao roledao, AuthenticationManager authenticationManager,
		MonUserDetailService userDetailsService, JwtUtil jwtUtil) {
	this.roledao = roledao;
	}

	
	@GetMapping("/listerole")
	public List<Role> listerole()
	{
		return roledao.findAll();
	}
	
	
	}
