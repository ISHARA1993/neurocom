package com._ds.system.repository;

import com._ds.system.model.CardRangeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRangeRepository extends JpaRepository<CardRangeData, Long> {

    @Query("SELECT c FROM CardRangeData c WHERE :pan BETWEEN c.startRange AND c.endRange")
    Optional<CardRangeData> findByPanRange(@Param("pan") long pan);

}
