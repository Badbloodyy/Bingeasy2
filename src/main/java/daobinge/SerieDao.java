package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.Serie;

@Repository
public interface SerieDao extends JpaRepository<Serie,Integer> {

}
