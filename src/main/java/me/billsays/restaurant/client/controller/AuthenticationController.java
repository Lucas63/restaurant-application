package me.billsays.restaurant.client.controller;

import me.billsays.restaurant.client.controller.dto.OwnerDTO;
import me.billsays.restaurant.client.controller.dto.UserDTO;
import me.billsays.restaurant.client.converter.OwnerDTOConverter;
import me.billsays.restaurant.client.converter.UserDTOConverter;
import me.billsays.restaurant.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * AutenticationController.java 8/29/16, 2016
 *
 * @author mkvitko
 */
@Controller
@Scope("session")
@SessionAttributes(types = {UserDTO.class})
public class AuthenticationController {
    @RequestMapping("/registerUser")
    @ResponseBody
    public String verify(@RequestBody UserDTO newUser) {
        if (newUser != null && (newUser.getPassword().equals(newUser.getPasswordConfirmation()))
                && !userService.findUserByEmail(newUser.getEmail()).isPresent()) {
            boolean result = userService.saveNewUser(userDTOConverter.convertFrom(newUser));
            if (result) {
                return "SUCCESS: User was successfully saved to DB!";
            } else {
                return "ERROR: something went wrong!";
            }
        } else {
            return "ERROR: Such email already exists!";
        }
    }

    @RequestMapping("/registerOwner")
    @ResponseBody
    public String verify(@RequestBody OwnerDTO newUser) {
        if (newUser != null && (newUser.getPassword().equals(newUser.getPasswordConfirmation()))
                && !userService.findUserByEmail(newUser.getEmail()).isPresent()) {
            boolean result = userService.saveNewOwner(ownerDTOConverter.convertFrom(newUser));
            if (result) {
                return "SUCCESS: Owner was successfully saved to DB!";
            } else {
                return "ERROR: something went wrong!";
            }
        } else {
            return "ERROR: Such email already exists!";
        }
    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOConverter userDTOConverter;
    @Autowired
    private OwnerDTOConverter ownerDTOConverter;

}
