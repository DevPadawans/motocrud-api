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
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.status(HttpStatus.OK).body(membroService.findAll());
    }

    @GetMapping(path = RESOURCE_FILTER)
    public ResponseEntity<?> filtrarMembros(@RequestParam MultiValueMap<String, String> params){
        //decidir o que vai usar na busca, pageable?
        return ResponseEntity.ok().body("Filtrar membro por alguns campos");
    }

    @PostMapping(path = RESOURCE_ADD)
    public ResponseEntity<MembroDTO> adicionarMembro(@RequestBody MembroDTO membroDTO, UriComponentsBuilder uriBuilder){
        Membro membroSalvo = membroService.save(membroDTO.toMembro());

        URI uri = uriBuilder.path(RESOURCE_MEMBROS + SLASH + "{id}").buildAndExpand(membroSalvo.getId()).toUri();

        MembroDTO membroDTOSalvo = new MembroDTO(membroSalvo);
        return ResponseEntity.created(uri).body(membroDTOSalvo);
    }

//    Decidir outro SAVE de exemplo
    @PostMapping(path = RESOURCE_ADD + "/testePost")
    public ResponseEntity<Object> adicionarMembroOpcional(@RequestBody Membro membro){
        Optional<Membro> membroOptional = membroService.findById(membro.getId());
        if (!membroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            var membroModel = membroOptional.get();
            membroService.update(membroModel);
            return ResponseEntity.status(HttpStatus.OK).body(membroModel);
        }
    }

    @PutMapping(path = RESOURCE_UPDATE)
    public ResponseEntity<Object> atualizarMembro(@RequestBody Membro membro){
        log.debug("PUT updateMembro received {} ", membro.toString());
        Optional<Membro> membroOptional = membroService.findById(membro.getId());
        if (!membroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            var membroModel = membroOptional.get();
            membroModel.setApelido(membro.getApelido());
            membroModel.setPadrinho(membro.getPadrinho());
            membroService.update(membroModel);
            log.debug("PUT updateMembro saved {} ", membroModel.getApelido());
            log.info("User updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(membroModel);
        }
    }

    @DeleteMapping(path = RESOURCE_REMOVE + "/{id}")
    public ResponseEntity<Object> removerMembro(@PathVariable Long id){
        Optional<Membro> membroOptional = membroService.findById(id);
        if(!membroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            membroService.delete(membroOptional.get());
            log.debug("Delete membro id deleted {} ", id);
            log.info("User delete successfully");
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        }
    }

}
