package com._ds.system.repository;

import com._ds.system.model.CardRangeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRangeRepository extends JpaRepository<CardRangeData, Long> {

}
