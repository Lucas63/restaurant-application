package me.billsays.restaurant.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.billsays.restaurant.client.controller.dto.LocationDTO;
import me.billsays.restaurant.client.converter.LocationDTOConverter;
import me.billsays.restaurant.client.model.Location;
import me.billsays.restaurant.client.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 9/16/16, 2016
 *
 * @author mkvitko
 */
@Controller
@Scope("session")
public class OwnerController {
    @RequestMapping(value = "/getAllRestaurants", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    //TODO: may be change to list?
    public String getAllRestaurants() {
        List<LocationDTO> locations = restaurantService.getAllRestaurants().stream()
                .map(converter::convertTo).collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(locations);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "ERROR: Problems during JSON file creation!";
        }
    }
    @RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
    @ResponseBody
    public String addRestaurant(@RequestBody LocationDTO newLocation) {
        if (newLocation != null && !restaurantService.exist(newLocation.getName(), newLocation.getAddress())) {
            if (restaurantService.addRestaurant(converter.convertFrom(newLocation)) != null) {
                return "SUCCESS: Restaurant with name: " + newLocation.getName() +
                        " was successfully added to DB!";
            }
        }
        return "ERROR: Something went wrong. Please check your input data!";
    }
    @RequestMapping(value = "/getRestaurant/{idLocation}", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getRestaurantById(@PathVariable("idLocation") Integer idLocation) {
        if (idLocation != null) {
            Location location = restaurantService.getRestaurantById(idLocation);
            if (location != null) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(converter.convertTo(location));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "ERROR: Problems during JSON file creation!";
                }
            } else {
                return "ERROR: There is no such restaurant in DB!";
            }
        } else {
            return "ERROR: Received empty id for restaurant!";
        }
    }
    @RequestMapping(value = "/restaurantToOwner/{idOwner}/{idLocation}", method = RequestMethod.PUT)
    @ResponseBody
    public String addRestaurantToOwner(@PathVariable("idOwner") Integer idOwner,
                                       @PathVariable("idLocation") Integer idLocation) {
        if (idLocation != null && idOwner != null) {
            if (restaurantService.updateRestaurantWithOwner(idLocation, idOwner, Boolean.FALSE)) {
                return "SUCCESS: Restaurant was added to owner!";
            }
        }
        return "ERROR: One of ids is empty OR restaurant already added!";
    }
    @RequestMapping(value = "/restaurantToOwner/{idOwner}/{idLocation}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRestaurantFromOwner(@PathVariable("idOwner") Integer idOwner,
                                            @PathVariable("idLocation") Integer idLocation) {
        if (idLocation != null && idOwner != null) {
            if (restaurantService.updateRestaurantWithOwner(idLocation, idOwner, Boolean.TRUE)) {
                return "SUCCESS: Restaurant was deleted from owner!";
            }
        }
        return "ERROR: One of ids is empty OR restaurant already deleted!";
    }

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private LocationDTOConverter converter;
}
