package org.bingetest.daobinge;

import java.util.Optional;

import org.bingetest.modele.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeDao extends JpaRepository<Episode,Integer> {

	Optional<Episode> findById(int id); // Si on cherche godzilla dans la base de données et qu'il n'existe pas, l'optional pourra le gérer. Ca rend
	// un optional, les optional sont utilisé notamment par les streams et par les hashmap.
}
