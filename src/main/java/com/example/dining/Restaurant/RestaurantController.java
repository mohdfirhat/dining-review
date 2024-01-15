package com.example.dining.Restaurant;

import com.example.dining.Exception.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    private RestaurantController(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurant() {
        return this.restaurantRepository.findAll();
    }
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new NoSuchElementException("No restaurant found with that ID.");
        }
        return restaurantOptional.get();
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurant.getId() !=null) {
            throw new BadRequestException("Input ID should not be provided.");
        }
        return this.restaurantRepository.save(restaurant);
    }
    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id,@RequestBody Restaurant restaurant) {
        if (restaurant.getId() !=null) {
            throw new BadRequestException("Input ID should not be provided.");
        }

        Optional<Restaurant> restaurantToUpdateOptional = restaurantRepository.findById(id);

        if (restaurantToUpdateOptional.isEmpty()) {
            throw new NoSuchElementException("No restaurant with the following ID.");
        }
        //Don't allow to update score(egg,peanut,dairy)
        Restaurant restaurantToUpdate = restaurantToUpdateOptional.get();
        restaurantToUpdate.setName(restaurant.getName());
        restaurantToUpdate.setCity(restaurant.getCity());
        restaurantToUpdate.setState(restaurant.getState());
        restaurantToUpdate.setZipcode(restaurant.getZipcode());
        return this.restaurantRepository.save(restaurantToUpdate);
    }
    @DeleteMapping("/{id}")
    public String deleteRestaurantById(@PathVariable Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new NoSuchElementException("No Restaurant exist with ID {id}.");
        }
        this.restaurantRepository.deleteById(id);
        return "Restaurant with ID ${id} is deleted.";
    }


}
