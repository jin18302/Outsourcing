package com.example.outsourcing.repository.review;

import com.example.outsourcing.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
//TODO UserId와 StoreId로 리뷰를 검색하는 JPQL쿼리를 추가합니다.


    @Query("SELECT R FROM Review R JOIN FETCH R.user WHERE R.user.id = :userId AND R.rating BETWEEN :startRating AND :endRating")
    Page<Review> findReviewByUserId(@Param("userId") Long userId,
                            @Param("startRating") int startRating,
                            @Param("endRating")  int endRating,
                            Pageable pageable);

    @Query("SELECT R FROM Review R JOIN FETCH R.store WHERE R.store.id = :storeId AND R.rating BETWEEN :startRating AND :endRating")
    Page<Review> findReviewByStoreId(@Param("storeId") Long storeId,
                                     @Param("startRating") int startRating,
                                     @Param("endRating")  int endRating,
                                     Pageable pageable);

    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.purchases.id = :purchasesId")
    boolean existsByPurchasesId(@Param("purchasesId") Long purchasesId);

}
