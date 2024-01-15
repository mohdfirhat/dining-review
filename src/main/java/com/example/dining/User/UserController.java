package com.example.dining.User;

import com.example.dining.Exception.AlreadyExistException;
import com.example.dining.Exception.BadRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    private UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public Collection<UserColumn> getAllUser() {
        return this.userRepository.findAllUserColumnBy();
    }

    @GetMapping("/{name}")
    public UserColumn getUserByName(@PathVariable String name) {
        Optional<UserColumn> userOptional = userRepository.findOneByName(name);
        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("No User with specified name:{name}.");
        }
        return userOptional.get();
    }

    @PostMapping
    public User createUser(@NotNull @RequestBody User user) {
        if (user.getId() != null) {
            throw new BadRequestException("Input ID should not be provided.");
        }
        Optional<UserColumn> userOptional = userRepository.findOneByName(user.getName());
        if (userOptional.isPresent()) {
            throw new AlreadyExistException("That username already exist. Choose a different username.");
        }
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User user) {
        Optional<User> userToUpdateOptional = userRepository.findById(id);
        if (userToUpdateOptional.isEmpty()) {
            throw new NoSuchElementException("No User with ID {id} exist.");
        }
        User userToUpdate = userToUpdateOptional.get();
        if (!user.getName().equals(userToUpdate.getName())) {
            throw new BadRequestException("Unable to change name field.");
        }
        userToUpdate.setCity(user.getCity());
        userToUpdate.setState(user.getState());
        userToUpdate.setZipcode(user.getZipcode());
        userToUpdate.setHasPeanutAllergy(user.getHasPeanutAllergy());
        userToUpdate.setHasEggAllergy(user.getHasEggAllergy());
        userToUpdate.setHasDairyAllergy(user.getHasDairyAllergy());
        return this.userRepository.save(userToUpdate);
    }
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("No User exist with ID {id}.");
        }
            this.userRepository.deleteById(id);
            return "User with ID ${id} is deleted.";
    }
}
