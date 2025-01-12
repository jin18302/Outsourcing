package com.example.outsourcing.dto.popSearch.response;

import com.example.outsourcing.entity.PopSearch;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class PopSearchResponse {
    private final String keyword;
    private final int count;
    private final int ranking;

    private PopSearchResponse(String keyword,double count,List<PopSearchResponse> prevList){
        this.keyword=keyword;
        this.count=(int)count;
        if(prevList.isEmpty()){
        this.ranking = 1;
        }else {
            this.ranking = prevList.get(prevList.size()-1).getRanking()+1;
        }
    }

    public static List<PopSearchResponse> from(Set<ZSetOperations.TypedTuple<String>> searchRanking){
        List<PopSearchResponse> popSearchResponseList = new ArrayList<>();
        searchRanking.stream().forEach(
                tuple -> {
                    popSearchResponseList.add(new PopSearchResponse(tuple.getValue(),tuple.getScore(),popSearchResponseList));

                }
        );
                return popSearchResponseList;
    }

    public static List<PopSearchResponse> from(List<PopSearch> popSearchList){
        List<PopSearchResponse> popSearchResponseList = new ArrayList<>();
        popSearchList.stream().forEach(
                tuple -> {
                    popSearchResponseList.add(new PopSearchResponse(tuple.getKeyword(),tuple.getRankingCount(),popSearchResponseList));
                });
        return popSearchResponseList;

    }
}
