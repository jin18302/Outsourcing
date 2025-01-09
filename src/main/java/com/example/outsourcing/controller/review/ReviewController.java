package com.example.outsourcing.controller.review;

import com.example.outsourcing.dto.review.request.CreateReviewRequest;
import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.example.outsourcing.dto.review.response.ReviewResponse;
import com.example.outsourcing.entity.Review;
import com.example.outsourcing.service.review.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> saveReview(@RequestBody CreateReviewRequest createReviewRequest, HttpServletRequest request){


       return new ResponseEntity<>(reviewService.saveReview((Long)request.getAttribute("userId"),createReviewRequest), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody UpdateReviewRequest updateReviewRequest, HttpServletRequest request){

        return new ResponseEntity<>(reviewService.updateReview((Long)request.getAttribute("userId"),updateReviewRequest),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Review>> findReviewByUserId(@RequestParam String Rating, @RequestParam int pageNumber, HttpServletRequest request){

        return new ResponseEntity (reviewService.findReviewByUserId((Long)request.getAttribute("userId"),Rating,pageNumber),HttpStatus.OK);

    }

    @GetMapping("{storeId}")
    public ResponseEntity<Page<Review>> findReviewByStoreId(@PathVariable Long storeId,@RequestParam String Rating, @RequestParam int pageNumber){

        return new ResponseEntity (reviewService.findReviewByStoreId(storeId,Rating,pageNumber),HttpStatus.OK);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<Void> removeReview(@PathVariable Long reviewId,HttpServletRequest request){
        reviewService.removeReview((Long)request.getAttribute("userId"),reviewId);
        return new ResponseEntity (HttpStatus.OK);
    }

}
