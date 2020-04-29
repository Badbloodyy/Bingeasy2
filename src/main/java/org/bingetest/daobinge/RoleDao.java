package org.bingetest.daobinge;

import java.util.Optional;

import org.bingetest.modele.Role;
import org.bingetest.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


	@Repository
	public interface RoleDao extends JpaRepository<Role,Integer>{

		// Utilisateur findByLogin(String login);
		// Optional<Role> findByNom(String nom);  // Si on veut chercher l'id via optional.
	}
	
