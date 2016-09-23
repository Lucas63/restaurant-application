package me.billsays.restaurant.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Owner.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "user1")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"restaurants", "token", "roles"})
@NoArgsConstructor
@DiscriminatorValue("owner")
public class Owner extends User {
    @Builder(toBuilder = true)
    public Owner(Integer idUser, String name, String email, Date dateregistration, String password,
                 Boolean confirmed, Set<Role> roles, Set<Location> restaurants, ConfirmationToken token) {
        super(idUser, name, email, dateregistration, password, confirmed, token, roles);
        this.restaurants = restaurants;
    }
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="location_user",
            joinColumns={ @JoinColumn(name="id_user")},
            inverseJoinColumns={ @JoinColumn(name="id_location")})
    private Set<Location> restaurants = new HashSet<>();
}
