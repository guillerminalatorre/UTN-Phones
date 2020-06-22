package com.utn.utnphones.repositories;

import com.utn.utnphones.models.LineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineTypeRepository extends JpaRepository<LineType, Integer> {
<<<<<<< HEAD

    @Query(value = "select l.* from line_types l where l.id_line_type = ?1 ", nativeQuery = true)
    LineType getById(Integer idLineType);
=======
    @Query(value = "select l.digits_qty from line_types l where l.id_line_type = ?1", nativeQuery = true)
    Integer findDigitsQtyById(Integer idLineType);
>>>>>>> parent of 5e7514a... Merge branch 'UTN-Phones-B1'
}
