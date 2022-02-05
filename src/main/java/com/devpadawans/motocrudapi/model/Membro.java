package com.devpadawans.motocrudapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membros")
public class Membro extends GenericEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "apelido")
    private String apelido;

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "padrinho")
    private String padrinho;

    @Basic
    @Column(name = "inicio")
    private LocalDate inicio;

    @Basic
    @Column(name = "nascimento")
    private LocalDate nascimento;
}
