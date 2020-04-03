package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.EvalEpisode;

@Repository
public interface EvalEpisodeDao extends JpaRepository<EvalEpisode, Integer> {

	
}
