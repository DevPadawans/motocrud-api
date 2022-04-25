package com.devpadawans.motocrudapi.commons.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErroFormularioDTO {

    private String campo;
    private String erro;

}
