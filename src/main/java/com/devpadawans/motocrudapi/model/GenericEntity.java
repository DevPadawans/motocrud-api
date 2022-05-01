package com.devpadawans.motocrudapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericEntity<U extends RepresentationModel<? extends U>> extends RepresentationModel<U> implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by")
    protected U createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected U lastModifiedBy;

    @Basic
    @CreatedDate
    @Column(name = "created_at")
    protected Instant createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "updated_at")
    protected Instant updatedAt;

}
