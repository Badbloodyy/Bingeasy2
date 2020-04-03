package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.PlageHoraireDispo;

@Repository
public interface PlageHoraireDispoDao extends JpaRepository<PlageHoraireDispo,Integer> {

}
