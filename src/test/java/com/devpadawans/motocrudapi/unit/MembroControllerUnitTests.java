package com.devpadawans.motocrudapi.unit;

import com.devpadawans.motocrudapi.dto.MembroDTO;
import com.devpadawans.motocrudapi.model.Membro;
import com.devpadawans.motocrudapi.repository.MembroRepository;
import com.devpadawans.motocrudapi.service.MembroService;
import com.devpadawans.motocrudapi.web.MembroController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@AutoConfigureMockMvc
class MembroControllerUnitTests {

    public static final long DOIS = 2l;
    public static final int STATUS_200 = 200;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MembroService membroService;

    @MockBean
    private MembroRepository membroRepository;

    @Autowired
    private MembroController membroController;

    @Test
    public void deveriaRetornarUmaListaDeMembros() {
        when(membroService.findAll()).thenReturn(Arrays.asList(new Membro(), new Membro()));

        MultiValueMap<String, String> paramsNull = new LinkedMultiValueMap<>();
        ResponseEntity<List<Membro>> responseEntity = membroController.searchMembros(paramsNull);

        List<Membro> body = responseEntity.getBody();
        assertEquals("Deveria retornar Status 200 OK", responseEntity.getStatusCodeValue(), STATUS_200);
        assertEquals("O tamanho da lista deveria ser 2", body.size(), DOIS);
    }

    @Test
    public void deveriaAdicionarUmMembro(){
        Membro membro = new Membro();
        membro.setId(1l);
        membro.setApelido("OGRO");
        membro.setNome("Handerson Frota");
        membro.setPadrinho("-----");

        MembroDTO membroDTO = new MembroDTO();
        membroDTO.setId(1l);
        membroDTO.setApelido("OGRO");
        membroDTO.setNome("Handerson Frota");
        membroDTO.setPadrinho("-----");

        when(membroService.save(membro)).thenReturn(membro);

//        ResponseEntity<MembroDTO> responseEntity = membroController.adicionarMembro(membroDTO);

//        assertEquals("Deveria retornar mensagem de sucesso!", responseEntity.getStatusCodeValue(), STATUS_200);
//        assertEquals("Deveria retornar o Membro salvo com o mesmo ID", responseEntity.getBody().getId(), membroDTO.getId());
//        assertEquals("Deveria retornar o Membro salvo com o mesmo NOME", responseEntity.getBody().getNome(), membroDTO.getNome());
//        assertEquals("Deveria retornar o Membro salvo com o mesmo APELIDO", responseEntity.getBody().getApelido(), membroDTO.getApelido());
    }

}
