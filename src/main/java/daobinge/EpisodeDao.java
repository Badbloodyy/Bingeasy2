package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.Episode;

@Repository
public interface EpisodeDao extends JpaRepository<Episode,Integer> {

}
