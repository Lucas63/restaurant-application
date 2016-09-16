package me.billsays.restaurant.client.repository;

import me.billsays.restaurant.client.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 9/16/16, 2016
 *
 * @author mkvitko
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);
}
