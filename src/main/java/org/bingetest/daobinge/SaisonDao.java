package org.bingetest.daobinge;

import org.bingetest.modele.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaisonDao extends JpaRepository<Saison,Integer>{

}
