package com.example.outsourcing.service.search;

import com.example.outsourcing.entity.PopSearch;

import java.time.LocalDateTime;
import java.util.List;

public interface PopSearchConnectorInterface {


    void saveAll(List<PopSearch> popSearchList);

    List<PopSearch> findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);


}
