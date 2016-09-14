package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Check.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CheckDTO {
    private Integer idCheck;
    private Date dateCreated;
    private Double totalCost;
    private String imageUrl;
}
