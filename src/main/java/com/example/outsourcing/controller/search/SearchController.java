package com.example.outsourcing.controller.search;

import com.example.outsourcing.dto.popSearch.response.PopSearchResponse;
import com.example.outsourcing.dto.search.response.SearchResponse;
import com.example.outsourcing.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<Page<SearchResponse>> searchStoreAndMenu(@RequestParam(value="keyword",required=true) String keyword, @RequestParam(value="pageNumber",required=false,defaultValue="1") int pageNumber){
        Page<SearchResponse> result = searchService.searchStoreAndMenu(keyword, pageNumber);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/pop")
    public ResponseEntity<List<PopSearchResponse>> popSearchResponseList(){
        return new ResponseEntity<>(searchService.popSearchResponseList(),HttpStatus.OK);
    }

    @GetMapping("/pop/day")
    public ResponseEntity<List<PopSearchResponse>> findPopSearchByDay(){
            return new ResponseEntity<>(searchService.findPopSearchByDay(),HttpStatus.OK);
    }
    @GetMapping("/pop/week")
    public ResponseEntity<List<PopSearchResponse>> findPopSearchByWeek(){
        return new ResponseEntity<>(searchService.findPopSearchByWeek(),HttpStatus.OK);
    }
    @GetMapping("/pop/month")
    public ResponseEntity<List<PopSearchResponse>> findPopSearchByMonth(){
        return new ResponseEntity<>(searchService.findPopSearchByMonth(),HttpStatus.OK);
    }
    @GetMapping("/pop/year")
    public ResponseEntity<List<PopSearchResponse>> findPopSearchByYear(){
        return new ResponseEntity<>(searchService.findPopSearchByYear(),HttpStatus.OK);
    }
}
