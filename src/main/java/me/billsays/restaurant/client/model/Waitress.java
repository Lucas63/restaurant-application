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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Waitress.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "waitress1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Waitress {
    @Id
    private Integer idWaitress;
    private String name;
    @ManyToOne
    @JoinColumn(name = "location1id_location", nullable = false)
    private Location location;
}
