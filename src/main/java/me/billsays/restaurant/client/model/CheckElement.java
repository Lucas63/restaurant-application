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
 * CheckElement.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "Checkelement1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class CheckElement {
    @Id
    private Integer idCheckElement;
    private String elementname;
    private Double elementcost;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "check1id_check", referencedColumnName = "id_check", nullable = false)
    private Check check;
}
