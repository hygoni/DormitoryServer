package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WasherRepository extends JpaRepository<Washer, Integer> {
    List<Washer> findAll();
    void deleteAll();
    Optional<Washer> findByBuildingNumberAndSubId(int buildingNumber, int subId);
    Washer save(Washer washer);
}
