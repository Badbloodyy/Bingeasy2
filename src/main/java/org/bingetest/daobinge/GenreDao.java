package org.bingetest.daobinge;

import org.bingetest.modele.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDao extends JpaRepository<Genre,Integer> {

}
