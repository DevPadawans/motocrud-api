package com.devpadawans.motocrudapi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.ativo = false WHERE e.id = ?1")
    void softDelete(ID id);

}
