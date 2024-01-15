package com.example.dining.Review;

import com.example.dining.Exception.BadRequestException;
import com.example.dining.enums.StatusType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    private ReviewController(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @GetMapping
    public Iterable<Review> getAllReview() {
        return this.reviewRepository.findAll();
    }
    @GetMapping("/pending")
    public Iterable<Review> getAllPendingReview() {
        List<Review> reviewList=  reviewRepository.findByStatus(StatusType.PENDING);

        if (reviewList.isEmpty()) {
            throw new NoSuchElementException("No pending Review found.");
        }

        return reviewList;
    }
//    @GetMapping("/{restaurantId}/approved")
//    public Iterable<Review> getByRestaurantIdAndApproved(@PathVariable Long restaurantId) {
//        List<Review> reviewList=  reviewRepository.findByRestaurantIdAndStatus(restaurantId,StatusType.APPROVED);
//        if (reviewList.isEmpty()) {
//            throw new NoSuchElementException("No approved Review found in this restaurant.");
//        }
//
//        return reviewList;
//    }
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        if (review.getId() !=null) {
            throw new BadRequestException("Input ID should not be provided.");
        }
        return this.reviewRepository.save(review);
    }
    @PutMapping("/{id}/approve")
    public Review acceptReview(@PathVariable Long id) {
        Optional<Review> reviewToUpdateOptional = reviewRepository.findById(id);
        if (reviewToUpdateOptional.isEmpty()) {
            throw new NoSuchElementException("No review with the following ID.");
        }
        Review reviewToUpdate = reviewToUpdateOptional.get();
        reviewToUpdate.setStatus(StatusType.APPROVED);
        this.reviewRepository.save(reviewToUpdate);
        return reviewToUpdate;
    }
    @PutMapping("/{id}/reject")
    public Review rejectReview(@PathVariable Long id) {
        Optional<Review> reviewToUpdateOptional = reviewRepository.findById(id);
        if (reviewToUpdateOptional.isEmpty()) {
            throw new NoSuchElementException("No review with the following ID.");
        }
        Review reviewToUpdate = reviewToUpdateOptional.get();
        reviewToUpdate.setStatus(StatusType.REJECTED);
        this.reviewRepository.save(reviewToUpdate);
        return reviewToUpdate;
    }
    @DeleteMapping("/{id}")
    public String deleteReviewById(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new NoSuchElementException("No Review exist with ID {id}.");
        }
        this.reviewRepository.deleteById(id);
        return "Review with ID ${id} is deleted.";
    }

}
