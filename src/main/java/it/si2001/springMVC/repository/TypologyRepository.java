package it.si2001.springMVC.repository;

import it.si2001.springMVC.model.Typology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypologyRepository extends JpaRepository<Typology, Integer> {

    @Query(value = "from Typology where type = :typology")
    Typology findByType(@Param("typology") String typology);

}
