package org.bingetest.daobinge;

import org.bingetest.modele.PlageHoraireDispo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlageHoraireDispoDao extends JpaRepository<PlageHoraireDispo,Integer> {

}
