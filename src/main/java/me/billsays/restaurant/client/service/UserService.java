package me.billsays.restaurant.client.service;

import me.billsays.restaurant.client.model.ConfirmationToken;
import me.billsays.restaurant.client.model.Owner;
import me.billsays.restaurant.client.model.Role;
import me.billsays.restaurant.client.model.User;
import me.billsays.restaurant.client.model.role.RoleEnum;
import me.billsays.restaurant.client.repository.ConfirmationTokenRepository;
import me.billsays.restaurant.client.repository.OwnerRepository;
import me.billsays.restaurant.client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

/**
 * UserService.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationRepository;

    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return user;
        } else {
            return Optional.ofNullable(ownerRepository.findUserByEmail(email)
                    .map(owner -> (User) owner).orElse(null));
        }
    }

    public User confirmUserByToken(String token) {
        return confirmationRepository.findConfirmationTokenByToken(token)
                .map(confTok -> {
                    userRepository.setConfirmedByIdUser(confTok.getUser().getIdUser());
                    return confTok.getUser();
                })
                .orElse(null);
    }

    public User saveNewUser(User user) {
        user.setDateregistration(new Date(Calendar.getInstance().getTime().getTime()));
        user.setConfirmed(Boolean.FALSE);
        user.getRoles().add(Role.builder().roleName(RoleEnum.USER.toString()).user(user).build());
        user.setToken(ConfirmationToken.builder().token(UUID.randomUUID().toString()).user(user).build());
        return userRepository.save(user);
    }

    public Optional<Owner> findOwnerByEmail(String email) {
        return ownerRepository.findUserByEmail(email);
    }

    public Owner saveNewOwner(Owner owner) {
        owner.setDateregistration(new Date(Calendar.getInstance().getTime().getTime()));
        owner.setConfirmed(Boolean.FALSE);
        owner.getRoles().add(Role.builder().roleName(RoleEnum.OWNER.toString()).user(owner).build());
        owner.getRoles().add(Role.builder().roleName(RoleEnum.USER.toString()).user(owner).build());
        owner.setToken(ConfirmationToken.builder().token(UUID.randomUUID().toString()).user(owner).build());
        return ownerRepository.save(owner);
    }
    public Owner findOwnerById(Integer idOwner) {
        return ownerRepository.findOne(idOwner);
    }
    public User findUserById(Integer idUser) {
        return userRepository.findOne(idUser);
    }
}
