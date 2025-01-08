package com.example.outsourcing.repository.menu;

import com.example.outsourcing.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // public Optional<Menu> findByIdAndStoreId(Long storeId, Long menu);


    @Query(
            "SELECT m FROM Menu m WHERE m.isDelete = false AND m.store.id = :storeId"
    )
    public List<Menu> findByStoreId(Long storeId);


}
