package com.example.outsourcing.service.search;

import com.example.outsourcing.dto.popSearch.response.PopSearchResponse;
import com.example.outsourcing.dto.search.response.SearchResponse;
import com.example.outsourcing.entity.PopSearch;
import com.example.outsourcing.service.menu.MenuConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final MenuConnectorInterface menuConnectorInterface;
    private final RedisTemplate<String, String> redisTemplate;
    private final PopSearchConnectorInterface popSearchConnectorInterface;

    public Page<SearchResponse> searchStoreAndMenu(String keyword, int pageNumber) {

        Pageable page = PageRequest.of(
                pageNumber - 1,
                10,
                Sort.by(Sort.Order.asc("name")));


        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.incrementScore("searchRanking", keyword, 1);

        return menuConnectorInterface.findStoreAndMenu(keyword, page);

    }

    public List<PopSearchResponse> popSearchResponseList() {
        ZSetOperations<String, String> zSetOperation = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> searchRangking = zSetOperation.reverseRangeWithScores("searchRanking", 0, 9);
        return PopSearchResponse.from(searchRangking);

    }

    /*1시간 마다 in memory db에 있는 데이터를 mysql db로 저장 후 in memory db를 비웁니다.*/
    @Scheduled(fixedRate = 3600000)
    public void saveAllRedisTodb() {
        List<PopSearchResponse> popSearchResponse = popSearchResponseList();
        popSearchConnectorInterface.saveAll(PopSearch.from(popSearchResponse));
        redisTemplate.delete("searchRanking");
    }

    public List<PopSearchResponse> findPopSearchByDay(){
        LocalDate nowDate = LocalDate.now();
        LocalDateTime nowDateTime = nowDate.atTime(LocalTime.MAX);
        LocalDateTime prevDateTime = nowDate.atStartOfDay();
        return PopSearchResponse.from(popSearchConnectorInterface
                .findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(prevDateTime,nowDateTime));

    }

    public List<PopSearchResponse> findPopSearchByWeek() {
        LocalDate nowDate = LocalDate.now();
        LocalDateTime nowDateTime = nowDate.atTime(LocalTime.MAX);
        LocalDateTime prevDateTime = nowDate.minusWeeks(1).atStartOfDay();
        return PopSearchResponse.from(popSearchConnectorInterface
                .findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(prevDateTime, nowDateTime));
    }

    public List<PopSearchResponse> findPopSearchByMonth(){
        LocalDate nowDate = LocalDate.now();
        LocalDateTime nowDateTime = nowDate.atTime(LocalTime.MAX);
        LocalDateTime prevDateTime = nowDate.minusMonths(1).atStartOfDay();
        return PopSearchResponse.from(popSearchConnectorInterface
                .findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(prevDateTime,nowDateTime));

    }

        public List<PopSearchResponse> findPopSearchByYear(){
            LocalDate nowDate = LocalDate.now();
            LocalDateTime nowDateTime = nowDate.atTime(LocalTime.MAX);
            LocalDateTime prevDateTime = nowDate.minusYears(1).atStartOfDay();
            return PopSearchResponse.from(popSearchConnectorInterface
                    .findTop10BySearchDateTimeBetweenOrderByRankingCountDesc(prevDateTime,nowDateTime));

        }
}
