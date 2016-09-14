package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class WaitressDTO {
    private Integer idWaitress;
    private String name;
    private LocationDTO location;
}
