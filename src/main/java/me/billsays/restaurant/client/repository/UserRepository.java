package me.billsays.restaurant.client.repository;

import me.billsays.restaurant.client.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * UserRepository.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE user1 SET confirmed = true WHERE id_user = ?1", nativeQuery = true)
    int setConfirmedByIdUser(Integer idUser);
}
