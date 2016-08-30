package me.billsays.restaurant.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * User.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "User1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class User {
    @Id
    private Integer idUser;
    private String name;
    private Date dateregistration;
    private String password;
}
