package com.devpadawans.motocrudapi.dto;

import com.devpadawans.motocrudapi.model.Membro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.lang.reflect.Member;
import java.time.LocalDate;

import static java.util.Objects.nonNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MembroDTO {

    private String apelido;
    private String nome;
    private String padrinho;

    @JsonProperty("data_inicio")
    private LocalDate inicio;

    @JsonProperty("data_nascimento")
    private LocalDate nascimento;

    @JsonIgnore
    public MembroDTO(Membro membro){
        if (nonNull(membro) && nonNull(membro.getId())){
            this.apelido = membro.getApelido();
            this.nome = membro.getNome();
            this.padrinho = membro.getPadrinho();
        }
    }

    public Membro toMembro(){
        return new Membro(this.apelido, this.nome, this.padrinho);
    }

}
