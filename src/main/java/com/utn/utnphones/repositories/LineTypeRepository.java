package com.utn.utnphones.repositories;

import com.utn.utnphones.models.LineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface LineTypeRepository extends JpaRepository<LineType, Integer> {

    @Query(value = "select l.* from line_types l where l.id_line_type = ?1 ", nativeQuery = true)
    LineType getById(Integer idLineType);
}
