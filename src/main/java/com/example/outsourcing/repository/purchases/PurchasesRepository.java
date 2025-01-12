package com.example.outsourcing.repository.purchases;

import com.example.outsourcing.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {

    @Query("SELECT p from Purchases p JOIN FETCH p.store WHERE p.store.id = :storeId")
    List<Purchases> findAllByStoreId(@Param("storeId") Long storeId);

    @Query("SELECT p from Purchases p JOIN FETCH p.user WHERE p.user.id = :userId")
    List<Purchases> findAllByUserId(@Param("userId") Long userId);


}
