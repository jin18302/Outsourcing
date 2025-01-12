package com.example.outsourcing.entity;

import com.example.outsourcing.dto.popSearch.response.PopSearchResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "popsearch")
@NoArgsConstructor
public class PopSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="keyword")
    private String keyword;

    @Column(name = "rankingcount")
    private int rankingCount;

    @Column(name="search_datetime")
    private LocalDateTime searchDateTime;

    private PopSearch(String keyword,int rankingCount,LocalDateTime searchDateTime){
        this.keyword = keyword;
        this.rankingCount=rankingCount;
        this.searchDateTime = searchDateTime;
    }

    public static List<PopSearch> from(List<PopSearchResponse> popSearchResponseList){
        LocalDateTime nowDateTime = LocalDateTime.now();
        List<PopSearch> popSearchList = new ArrayList<>();
        for(PopSearchResponse popSearchResponse  : popSearchResponseList){
            popSearchList.add(new PopSearch(
                    popSearchResponse.getKeyword(),
                    popSearchResponse.getCount(),
                    nowDateTime
                    ));
        }
        return popSearchList;

    }
}
