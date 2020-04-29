package org.bingetest.daobinge;

import org.bingetest.modele.PlateForme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateFormeDao extends JpaRepository<PlateForme,Integer> {

}
