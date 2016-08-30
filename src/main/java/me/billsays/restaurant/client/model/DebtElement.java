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

/**
 * DebtElement.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "Debtelement1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class DebtElement {
    @Id
    private Integer idDeptElement;
    private Double valuedebt;
}
