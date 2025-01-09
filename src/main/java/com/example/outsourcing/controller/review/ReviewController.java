package com.example.outsourcing.controller.review;

import com.example.outsourcing.dto.review.request.CreateReviewRequest;
import com.example.outsourcing.dto.review.request.UpdateReviewRequest;
import com.example.outsourcing.dto.review.response.ReviewResponse;
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
    public ResponseEntity<ReviewResponse> saveReview(@RequestAttribute("userId") Long userId,@RequestBody CreateReviewRequest createReviewRequest){
        ReviewResponse result = reviewService.saveReview(userId, createReviewRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(@RequestAttribute("userId") Long userId,@RequestBody UpdateReviewRequest updateReviewRequest){
        ReviewResponse result = reviewService.updateReview(userId, updateReviewRequest);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> findReviewByUserId(@RequestAttribute("userId") Long userId,@RequestParam String Rating, @RequestParam int pageNumber){
        Page<ReviewResponse> result = reviewService.findReviewByUserId(userId, Rating, pageNumber);
        return new ResponseEntity<> (result,HttpStatus.OK);

    }

    @GetMapping("{storeId}")
    public ResponseEntity<Page<ReviewResponse>> findReviewByStoreId(@PathVariable Long storeId,@RequestParam String Rating, @RequestParam int pageNumber){
        Page<ReviewResponse> result = reviewService.findReviewByStoreId(storeId, Rating, pageNumber);

        return new ResponseEntity (result,HttpStatus.OK);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<Void> removeReview(@RequestAttribute("userId") Long userId,@PathVariable Long reviewId){
        reviewService.removeReview(userId,reviewId);
        return new ResponseEntity (HttpStatus.OK);
    }

}
