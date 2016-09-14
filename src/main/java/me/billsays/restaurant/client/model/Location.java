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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

/**
 * Location.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "location1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Location {
    @Id
    private Integer idLocation;
    private Boolean isRegistred;
    private String name;
    private String address;
    @ManyToMany(mappedBy = "restaurants")
    private List<Owner> owners;
}
