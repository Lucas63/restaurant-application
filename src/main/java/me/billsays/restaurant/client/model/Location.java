package me.billsays.restaurant.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;
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
@EqualsAndHashCode(exclude = {"owners"})
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location1_id_location_seq")
    private Integer idLocation;
    private Boolean isRegistred;
    private String name;
    private String address;
    private Date dateCreation;
    @ManyToMany(mappedBy = "restaurants", fetch = FetchType.EAGER)
    private List<Owner> owners;
}
