package com.example.dining.Review;

import com.example.dining.enums.StatusType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findByStatus(StatusType status);
//    List<Review> findByRestaurantIdAndStatus(Long id,StatusType status);
}
