package com.example.outsourcing.repository.popSearch;

import com.example.outsourcing.entity.PopSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PopSearchRepository extends JpaRepository<PopSearch,Long> {

    List<PopSearch> findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(LocalDateTime startDateTime,LocalDateTime endDateTime);
}
