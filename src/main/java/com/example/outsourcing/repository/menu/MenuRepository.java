package com.example.outsourcing.repository.menu;

import com.example.outsourcing.dto.search.response.SearchResponse;
import com.example.outsourcing.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {


    @Query(

            "SELECT m FROM Menu m JOIN FETCH m.store WHERE m.isDelete = false AND m.store.id = :storeId"

    )
    List<Menu> findByStoreId(Long storeId);

    @Query(
      "SELECT DISTINCT new com.example.outsourcing.dto.search.response.SearchResponse(s.id,s.name,s.address) " +
              "FROM Store s INNER JOIN Menu m ON m.store.id = s.id " +
              "WHERE m.name LIKE %:KEYWORD% AND m.isDelete = false " +
              "OR s.name LIKE %:KEYWORD% AND s.isDeleted = false"
    )
     Page<SearchResponse> findStoreAndMenu(@Param("KEYWORD") String Keyword, Pageable pageable);



}
