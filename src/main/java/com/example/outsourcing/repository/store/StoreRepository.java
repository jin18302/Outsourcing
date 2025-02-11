package com.example.outsourcing.repository.store;

import com.example.outsourcing.dto.search.response.SearchResponse;
import com.example.outsourcing.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {


    Optional<Store> findByIdAndIsDeletedFalse(Long storeId);

    Page<Store> findByIsDeletedFalse(Pageable pageable);

    // 내 가게 몇개인지
    int countByUserIdAndIsDeletedFalse(Long userId);

    // 내 가게 찾기
    @Query("SELECT s FROM Store s JOIN FETCH s.user WHERE s.user.id = :userId AND s.isDeleted = false")
    List<Store> findMyStoresByUserId(@Param("userId") Long userId);

    // 검색시 이름에 들어가면 다 검색
    @Query("SELECT s FROM Store s WHERE s.name LIKE CONCAT('%', :name, '%') AND s.isDeleted = false")
    Page<Store> findAllByName(@Param("name") String name, Pageable pageable);

    @Query(
            "SELECT DISTINCT new com.example.outsourcing.dto.search.response.SearchResponse(s.id,s.name,s.address) " +
                    "FROM Store s INNER JOIN Menu m ON m.store.id = s.id " +
                    "WHERE m.name LIKE %:KEYWORD% AND m.isDelete = false " +
                    "OR s.name LIKE %:KEYWORD% AND s.isDeleted = false"
    )
    Page<SearchResponse> findStoreAndMenu(@Param("KEYWORD") String Keyword, Pageable pageable);

}
