package com.example.outsourcing.repository.store;

import com.example.outsourcing.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 사장님 가게 몇개인지
    @Query("SELECT Count(s) FROM Store s WHERE s.user.id = :userId and s.isDeleted = false")
    int countByUserId(@Param("userId") Long userId);

    Page<Store> findByIsDeletedFalse(Pageable pageable);

    // 검색시 이름에 들어가면 다 검색
    @Query("SELECT s FROM Store s WHERE s.name LIKE CONCAT('%', :name, '%') AND s.isDeleted = false")
    Page<Store> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT s FROM Store s JOIN FETCH s.user WHERE s.user.id = :userId")
    List<Store> findByUserId(Long userId);
}
