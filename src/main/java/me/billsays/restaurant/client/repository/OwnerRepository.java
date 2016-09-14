package me.billsays.restaurant.client.repository;

import me.billsays.restaurant.client.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * OwnerRepository.java 9/2/16, 2016
 *
 * @author mkvitko
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findUserByEmail(String email);
}
