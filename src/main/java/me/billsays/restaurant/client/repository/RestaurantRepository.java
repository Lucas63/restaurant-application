package me.billsays.restaurant.client.repository;

import me.billsays.restaurant.client.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * RestaurantRepository.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Location, Integer> {
    Optional<List<Location>> findByOwnersIdUser(Integer idUser);
    Location findByNameAndAddressAllIgnoreCase(String name, String address);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO location_user(id_location, id_user) values(?1,?2);", nativeQuery = true)
    int addRestaurantToOwner(Integer idLocation, Integer idOwner);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM location_user WHERE id_location = :idLocation AND id_user = :idUser;", nativeQuery = true)
    int removeRestaurantFromOwner(Integer idLocation, Integer idUser);
}
