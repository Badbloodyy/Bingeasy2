package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer>{

}
