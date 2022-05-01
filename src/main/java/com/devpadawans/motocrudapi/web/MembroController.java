package com.devpadawans.motocrudapi.web;

import com.devpadawans.motocrudapi.dto.MembroDTO;
import com.devpadawans.motocrudapi.model.Membro;
import com.devpadawans.motocrudapi.service.MembroService;
import com.devpadawans.motocrudapi.specifications.SpecificationTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(RESOURCE_MEMBROS)
@RequiredArgsConstructor
public class MembroController implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MembroService membroService;

    @GetMapping
    public ResponseEntity<Page<Membro>> getAllMembros(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                      SpecificationTemplate.MembroSpecification spec){
        Page<Membro> membroModelPage = membroService.paginate(pageable, spec);
//        Teste Hateoas
        if(!membroModelPage.isEmpty()){
            for(Membro membro : membroModelPage.toList()){
                membro.add(linkTo(methodOn(MembroController.class).getMembroPorId(membro.getId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(membroModelPage);
    }

    @GetMapping(path = RESOURCE_SEARCH + "/{id}")
    public ResponseEntity<Object> getMembroPorId(@PathVariable(value = "id") Long id){
        Optional<Membro> membroOptional = membroService.findById(id);
        if (!membroOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(membroOptional.get());
        }
    }

    @GetMapping(path = RESOURCE_SEARCH)
    public ResponseEntity<List<Membro>> searchMembros(@RequestParam MultiValueMap<String, String> params){
        return ResponseEntity.status(HttpStatus.OK).body(membroService.findAll());
    }

    @GetMapping(path = RESOURCE_FILTER)
    public ResponseEntity<?> filtrarMembros(@RequestParam MultiValueMap<String, String> params){
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
        if (membroOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            var membroModel = membroOptional.get();
            membroService.update(membroModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(membroModel);
        }
    }

    @PutMapping(path = RESOURCE_UPDATE)
    public ResponseEntity<Object> atualizarMembro(@RequestBody Membro membro){
        log.debug("PUT updateMembro received {} ", membro.toString());
        Optional<Membro> membroOptional = membroService.findById(membro.getId());
        if (membroOptional.isEmpty()){
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
        if(membroOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro not found.");
        }else{
            membroService.delete(membroOptional.get());
            log.debug("Delete membro id deleted {} ", id);
            log.info("User delete successfully");
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        }
    }

}
