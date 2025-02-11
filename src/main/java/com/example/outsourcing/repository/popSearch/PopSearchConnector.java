package com.example.outsourcing.repository.popSearch;

import com.example.outsourcing.entity.PopSearch;
import com.example.outsourcing.service.search.PopSearchConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopSearchConnector implements PopSearchConnectorInterface {
    private final PopSearchRepository popSearchRepository;
    private final PopSearchBatchInsert popSearchBatchInsert;

    @Override
    public void saveAll(List<PopSearch> popSearchList){
        popSearchBatchInsert.saveAll(popSearchList);
    }

    @Override
    public List<PopSearch> findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(LocalDateTime startDateTime,LocalDateTime endDateTime) {
        return popSearchRepository.findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(startDateTime,endDateTime);
    }
}
