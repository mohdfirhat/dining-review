package com.example.dining.User;

import com.example.dining.Review.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String city;
    private String state;
    private Integer zipcode;
    private Boolean hasPeanutAllergy;
    private Boolean hasEggAllergy;
    private Boolean hasDairyAllergy;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NAME",referencedColumnName = "NAME")
    private List<Review> reviews;

}



