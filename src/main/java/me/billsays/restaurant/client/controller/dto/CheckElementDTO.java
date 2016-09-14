package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * CheckElement.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class CheckElementDTO {
    private Integer idCheckElement;
    private String elementname;
    private Double elementcost;
    private Integer amount;
    private CheckDTO check;
}
