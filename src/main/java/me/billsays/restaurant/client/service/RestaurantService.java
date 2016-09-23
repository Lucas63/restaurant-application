package me.billsays.restaurant.client.service;

import me.billsays.restaurant.client.model.Location;
import me.billsays.restaurant.client.model.Owner;
import me.billsays.restaurant.client.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * RestaurantService.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserService userService;

    public List<Location> getAllRestaurants() {
        return Optional.ofNullable(restaurantRepository.findAll()).orElse(Collections.emptyList());
    }
    public Location getRestaurantById(Integer locationId) {
        return restaurantRepository.findOne(locationId);
    }
    public List<Location> getRestaurantsByOwner(Integer ownerId) {
        return restaurantRepository.findByOwnersIdUser(ownerId).orElse(Collections.emptyList());
    }
    public boolean exist(String name, String address) {
        return restaurantRepository.findByNameAndAddressAllIgnoreCase(name, address) != null;
    }
    public Location addRestaurant(Location location) {
        location.setDateCreation(new Date(Calendar.getInstance().getTime().getTime()));
        return restaurantRepository.save(location);
    }
    public boolean updateRestaurantWithOwner(Integer idLocation, Integer idOwner, boolean delete) {
        Location location = getRestaurantById(idLocation);
        Owner owner = userService.findOwnerById(idOwner);
        if (owner != null && location != null) {
            if (delete) {
                return restaurantRepository.removeRestaurantFromOwner(idLocation, idOwner) > 0;
            } else{
                return restaurantRepository.addRestaurantToOwner(idLocation, idOwner) > 0;
            }
        }
        return false;
    }
}
