package me.billsays.restaurant.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Friend.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "Friend1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Friend {
    @Id
    private Integer idfriend;
    private Double rating;
    private String status;
    private Integer dateChanged;
}
