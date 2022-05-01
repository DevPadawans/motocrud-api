package com.devpadawans.motocrudapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membro")
public class Membro extends GenericEntity<Membro> {

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

    @Column(name = "ativo")
    protected Boolean ativo = true;

    public Membro(Long id, String apelido, String nome, String padrinho){
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.padrinho = padrinho;
    }
}
