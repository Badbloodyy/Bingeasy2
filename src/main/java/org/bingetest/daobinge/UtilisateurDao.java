package org.bingetest.daobinge;

import java.util.Optional;

import org.bingetest.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer>{

	// Utilisateur findByLogin(String login);
	Optional<Utilisateur> findByLogin(String login);  // Si on veut chercher l'id via optional.
	@Query("Select u from Utilisateur u where u.id = 2")
	Utilisateur requeteSpecialUtilisateur2();
}
