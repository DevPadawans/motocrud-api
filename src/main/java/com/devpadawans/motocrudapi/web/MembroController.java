package com.devpadawans.motocrudapi.web;

import com.devpadawans.motocrudapi.dto.MembroDTO;
import com.devpadawans.motocrudapi.model.Membro;
import com.devpadawans.motocrudapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static com.devpadawans.motocrudapi.commons.utils.PathUtils.*;

@Slf4j
@RestController
@RequestMapping(RESOURCE_MEMBROS)
@RequiredArgsConstructor
public class MembroController implements Serializable {

    private final static long serialVersionUID = 1L;

    private final MembroService membroService;

    @GetMapping(path = RESOURCE_SEARCH)
    public ResponseEntity<List<Membro>> searchMembros(@RequestParam MultiValueMap<String, String> params){
        List<Membro> membros = membroService.findAll();
        return ResponseEntity.ok().body(membros);
    }

    @GetMapping(path = RESOURCE_FILTER)
    public ResponseEntity<?> filtrarMembros(@RequestParam MultiValueMap<String, String> params){
        //decidir o que vai usar na busca
        return ResponseEntity.ok().body("Filtrar membro por alguns campos");
    }

    @PostMapping(path = RESOURCE_ADD)
    public ResponseEntity<MembroDTO> adicionarMembro(@RequestBody MembroDTO membroDTO){
        Membro membroSalvo = membroService.save(membroDTO.toMembro());
        MembroDTO membroDTOSalvo = new MembroDTO(membroSalvo);
        return ResponseEntity.ok().body(membroDTOSalvo);
    }

    @GetMapping(path = RESOURCE_UPDATE)
    public ResponseEntity<?> atualizarMembro(@RequestBody MembroDTO membro){

        return ResponseEntity.ok().body("Atualizar membro");
    }

    @PutMapping(path = RESOURCE_REMOVE + "/{id}")
    public ResponseEntity<?> removerMembro(@PathVariable Long id){
//        memberService.delete(id);
        return new ResponseEntity("successfully", HttpStatus.OK);
    }

}
