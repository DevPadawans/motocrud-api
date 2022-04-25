package com.devpadawans.motocrudapi.repository;

import com.devpadawans.motocrudapi.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>, BaseRepository<Membro, Long> {

    Membro findByNome(String nome);

}
