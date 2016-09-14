package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Mention.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class MentionDTO {
    private Integer idMention;
    private String comment;
    private Integer waitressRate;
    private Integer foodRate;
    private Integer musicRate;
    private CheckDTO check;
}
