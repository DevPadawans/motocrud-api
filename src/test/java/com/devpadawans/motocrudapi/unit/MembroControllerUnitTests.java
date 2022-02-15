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
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    void deveriaRetornarUmaListaDeMembros() {
        when(membroService.findAll()).thenReturn(Arrays.asList(new Membro(), new Membro()));

        MultiValueMap<String, String> paramsNull = new LinkedMultiValueMap<>();
        ResponseEntity<List<Membro>> responseEntity = membroController.procurarMembros(paramsNull);

        List<Membro> body = responseEntity.getBody();
        assertEquals("Deveria retornar Status 200 OK", responseEntity.getStatusCodeValue(), STATUS_200);
        assertEquals("O tamanho da lista deveria ser 2", body.size(), DOIS);
    }

    @Test
    void deveriaAdicionarUmMembro(){
        Membro membro = criarMembro(1l, "Ogro", "Handerson Frota", "Padrinho");

        MembroDTO membroDTO = criarMembroDTO(1l, "Ogro", "Handerson Frota", "Padrinho");

        when(membroService.save(membro)).thenReturn(membro);

        ResponseEntity<MembroDTO> responseEntity = membroController.adicionarMembro(membroDTO);

        assertEquals("Deveria retornar mensagem de sucesso!", responseEntity.getStatusCodeValue(), STATUS_200);
        assertEquals("Deveria retornar o Membro salvo com o mesmo ID", responseEntity.getBody().getId(), membroDTO.getId());
        assertEquals("Deveria retornar o Membro salvo com o mesmo NOME", responseEntity.getBody().getNome(), membroDTO.getNome());
        assertEquals("Deveria retornar o Membro salvo com o mesmo APELIDO", responseEntity.getBody().getApelido(), membroDTO.getApelido());
    }

    @Test
    void deveriaBuscarMembroPorID(){
        Membro membro = criarMembro(2L, "Ogro", "Handerson", "padrinho");
//        criarMembroDTO(2L,"Ogro", "Handerson", "padrinho");

        when(membroService.findById(2l)).thenReturn(java.util.Optional.of(membro));

        ResponseEntity<?> responseEntity = membroController.filtrarMembrosPorID(2l);

        Optional<Membro> body = (Optional<Membro>) responseEntity.getBody();

        assertTrue(body.isPresent());
        assertEquals("Deveria retornar mensagem de sucesso!", responseEntity.getStatusCodeValue(), STATUS_200);
    }

    private Membro criarMembro(Long id, String apelido, String nome, String padrinho){
        Membro membro = new Membro();
        membro.setId(id);
        membro.setApelido(apelido);
        membro.setNome(nome);
        membro.setPadrinho(padrinho);
        return membro;
    }

    private MembroDTO criarMembroDTO(Long id, String apelido, String nome, String padrinho){
        MembroDTO membroDTO = new MembroDTO();
        membroDTO.setId(id);
        membroDTO.setApelido(apelido);
        membroDTO.setNome(nome);
        membroDTO.setPadrinho(padrinho);
        return membroDTO;
    }

}
