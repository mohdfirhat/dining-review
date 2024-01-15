package com.example.dining.Restaurant;

import com.example.dining.Review.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "RESTAURANTS",uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME", "ZIPCODE" }) })
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String state;
    private Integer zipcode;
    private Float peanutScore;
    private Float eggScore;
    private Float dairyScore;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID",referencedColumnName = "ID")
    private List<Review> reviews;
}
