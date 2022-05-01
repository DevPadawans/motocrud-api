package com.devpadawans.motocrudapi.specifications;

import com.devpadawans.motocrudapi.model.Membro;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {
    @And({
        @Spec(path = "apelido", spec = Like.class),
        @Spec(path = "padrinho", spec = Like.class)
    })
    public interface MembroSpecification extends Specification<Membro> {}

}
