package com.example.outsourcing.repository.purchases;

import com.example.outsourcing.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
}
