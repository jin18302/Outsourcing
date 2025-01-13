package com.example.outsourcing.repository.popSearch;

import com.example.outsourcing.entity.PopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PopSearchBatchInsert {
    private final JdbcTemplate jdbcTemplate;

    //
    public void saveAll(List<PopSearch> popSearchList){
        String sql = "INSERT INTO popsearch (rankingcount,search_datetime,keyword) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(
                sql,
                popSearchList,
                popSearchList.size(),
                (ps,argument) -> {
                    ps.setInt(1,argument.getRankingCount());
                    ps.setTimestamp(2, Timestamp.valueOf(argument.getSearchDateTime()));
                    ps.setString(3,argument.getKeyword());
                });
    }
}
