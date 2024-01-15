package com.example.dining.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;
interface UserColumn {
    Long getId();
    String getName();
    String getCity();
    String getState();
    Integer getZipcode();
    Boolean getHasPeanutAllergy();
    Boolean getHasEggAllergy();
    Boolean getHasDairyAllergy();
}
public interface UserRepository extends CrudRepository<User,Long> {
    Collection<UserColumn> findAllUserColumnBy();
    Optional<UserColumn> findOneByName(String name);

}
