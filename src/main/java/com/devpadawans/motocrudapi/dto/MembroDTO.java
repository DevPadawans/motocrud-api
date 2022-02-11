package com.devpadawans.motocrudapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MembroDTO {

    private String apelido;

}
