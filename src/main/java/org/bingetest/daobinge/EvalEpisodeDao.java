package org.bingetest.daobinge;

import org.bingetest.modele.EvalEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalEpisodeDao extends JpaRepository<EvalEpisode, Integer> {

	
}
