package com.example.dining.Review;

import com.example.dining.enums.StatusType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "REVIEWS",uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME", "RESTAURANT_ID" }) })
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float peanutScore;
    private Float eggScore;
    private Float dairyScore;
    private String comment;
    @Enumerated(EnumType.STRING)
    private StatusType status=StatusType.PENDING;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Restaurant restaurant;

}
