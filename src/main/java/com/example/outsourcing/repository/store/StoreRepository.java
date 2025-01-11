package com.example.outsourcing.repository.store;

import com.example.outsourcing.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s FROM Store s WHERE s.id = :storeId AND s.isDeleted = false")
    Optional<Store> findByIdAndIsDeletedFalse(@Param("storeId") Long storeId);


    @Query("SELECT s FROM Store s WHERE s.isDeleted = false")
    Page<Store> findByIsDeletedFalse(Pageable pageable);

    // 검색시 이름에 들어가면 다 검색
    @Query("SELECT s FROM Store s WHERE s.name LIKE CONCAT('%', :name, '%') AND s.isDeleted = false")
    Page<Store> findAllByName(@Param("name") String name, Pageable pageable);

    // 내 가게 몇개인지
    @Query("SELECT Count(s) FROM Store s WHERE s.user.id = :userId and s.isDeleted = false")
    int countByUserId(@Param("userId") Long userId);

    // 내 가게 찾기
    @Query("SELECT s FROM Store s JOIN FETCH s.user WHERE s.user.id = :userId AND s.isDeleted = false")
    List<Store> findMyStoresByUserId(@Param("userId") Long userId);
}
