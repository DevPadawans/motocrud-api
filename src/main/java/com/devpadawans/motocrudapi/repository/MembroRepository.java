package com.devpadawans.motocrudapi.repository;

import com.devpadawans.motocrudapi.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

public interface MembroRepository extends JpaRepository<Membro, Long>, BaseRepository<Membro, Long> {

}
