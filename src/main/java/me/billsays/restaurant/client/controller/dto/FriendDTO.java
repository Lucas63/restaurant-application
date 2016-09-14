package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Friend.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class FriendDTO {
    private Integer idfriend;
    private Double rating;
    private String status;
    private Integer dateChanged;
}
