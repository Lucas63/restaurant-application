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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Mention.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Entity
@Table(name = "Mention1")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Mention {
    @Id
    private Integer idMention;
    private String comment;
    private Integer waitressRate;
    private Integer foodRate;
    private Integer musicRate;
    @OneToOne
    @JoinColumn(name = "id_mention", referencedColumnName = "id_check", nullable = false)
    private Check check;
}
