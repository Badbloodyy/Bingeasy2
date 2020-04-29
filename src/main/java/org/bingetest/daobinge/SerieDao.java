package org.bingetest.daobinge;

import org.bingetest.modele.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieDao extends JpaRepository<Serie,Integer> {

}
