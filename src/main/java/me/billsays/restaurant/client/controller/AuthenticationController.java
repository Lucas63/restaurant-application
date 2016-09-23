package me.billsays.restaurant.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.billsays.restaurant.client.controller.dto.OwnerDTO;
import me.billsays.restaurant.client.controller.dto.UserDTO;
import me.billsays.restaurant.client.converter.OwnerDTOConverter;
import me.billsays.restaurant.client.converter.UserDTOConverter;
import me.billsays.restaurant.client.model.Owner;
import me.billsays.restaurant.client.model.User;
import me.billsays.restaurant.client.service.EmailService;
import me.billsays.restaurant.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;

/**
 * AuthenticationController.java 8/29/16, 2016
 *
 * @author mkvitko
 */
@Controller
@Scope("session")
@SessionAttributes(types = {UserDTO.class, OwnerDTO.class})
public class AuthenticationController {
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody UserDTO newUser) {
        if (newUser != null && (newUser.getPassword().equals(newUser.getPasswordConfirmation()))
                && !userService.findUserByEmail(newUser.getEmail()).isPresent()) {
            User result = userService.saveNewUser(userDTOConverter.convertFrom(newUser));
            if (result != null) {
                try {
                    emailService.sendConfirmationMail(result);
                } catch (MessagingException e) {
                    return "ERROR: Failed to send confirmation email!";
                }
                return "SUCCESS: User was successfully saved to DB!";
            } else {
                return "ERROR: something went wrong!";
            }
        } else {
            return "ERROR: Such email already exists!";
        }
    }

    @RequestMapping(value = "/registerOwner", method = RequestMethod.POST)
    @ResponseBody
    public String addOwner(@RequestBody OwnerDTO newUser) {
        if (newUser != null && (newUser.getPassword().equals(newUser.getPasswordConfirmation()))
                && !userService.findUserByEmail(newUser.getEmail()).isPresent()) {
            Owner result = userService.saveNewOwner(ownerDTOConverter.convertFrom(newUser));
            if (result != null) {
                try {
                    emailService.sendConfirmationMail(result);
                } catch (MessagingException e) {
                    return "ERROR: Failed to send confirmation email!";
                }
                return "SUCCESS: Owner was successfully saved to DB!";
            } else {
                return "ERROR: something went wrong!";
            }
        } else {
            return "ERROR: Such email already exists!";
        }
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String verify(@RequestParam("token") String token) {
        if (!StringUtils.isEmpty(token)) {
            User user = userService.confirmUserByToken(token);
            if (user != null) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    return mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(userDTOConverter.convertTo(user));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "ERROR: Problems during JSON file creation!";
                }
            } else {
                return "ERROR: No user found for such token: " + token;
            }
        } else {
            return "ERROR: received empty token!";
        }
    }
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getUser(@PathVariable("id") Integer idUser) {
        if (!StringUtils.isEmpty(idUser)) {
            User user = userService.findUserById(idUser);
            if (user != null) {
                try {
                    return new ObjectMapper().writerWithDefaultPrettyPrinter()
                            .writeValueAsString(userDTOConverter.convertTo(user));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                return "ERROR: There is no such user in DB!";
            }
        }
        return "ERROR: received empty id!";
    }
    @RequestMapping(value = "/getOwner/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getOwner(@PathVariable("id") Integer idOwner) {
        if (!StringUtils.isEmpty(idOwner)) {
            Owner user = userService.findOwnerById(idOwner);
            if (user != null) {
                try {
                    return new ObjectMapper().writerWithDefaultPrettyPrinter()
                            .writeValueAsString(ownerDTOConverter.convertTo(user));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                return "ERROR: There is no such owner in DB!";
            }
        }
        return "ERROR: received empty id!";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOConverter userDTOConverter;
    @Autowired
    private OwnerDTOConverter ownerDTOConverter;
    @Autowired
    private EmailService emailService;

}
