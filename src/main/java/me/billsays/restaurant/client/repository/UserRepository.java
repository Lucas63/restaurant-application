package me.billsays.restaurant.client.repository;

import me.billsays.restaurant.client.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
