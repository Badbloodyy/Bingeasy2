package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.Saison;

@Repository
public interface SaisonDao extends JpaRepository<Saison,Integer>{

}
