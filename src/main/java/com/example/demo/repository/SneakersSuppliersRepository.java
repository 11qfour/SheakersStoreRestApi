package com.example.demo.repository;

import com.example.demo.model.Sneakers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SneakersSuppliersRepository extends JpaRepository<Sneakers, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sneakers_suppliers (sneakers_id, supplier_id) VALUES (:sneakersId, :supplierId)", nativeQuery = true)
    void addSneakerSupplierRelation(@Param("sneakersId") Long sneakersId, @Param("supplierId") Long supplierId);
}
