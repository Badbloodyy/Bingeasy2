package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.PlateForme;

@Repository
public interface PlateFormeDao extends JpaRepository<PlateForme,Integer> {

}
