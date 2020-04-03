package daobinge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modele.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre,Integer> {

}
