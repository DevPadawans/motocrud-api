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
import java.util.Optional;

import static com.devpadawans.motocrudapi.commons.utils.PathUtils.*;

@Slf4j
@RestController
@RequestMapping(RESOURCE_MEMBROS)
@RequiredArgsConstructor
public class MembroController implements Serializable {

    private final static long serialVersionUID = 1L;

    private final MembroService memberService;

    @GetMapping(path = RESOURCE_LIST)
    public ResponseEntity<List<Membro>> getListMembers(@RequestParam MultiValueMap<String, String> params){
        //getById
        List<Membro> list = memberService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(path = RESOURCE_ADD)
    public ResponseEntity<?> adicionarMembro(@RequestBody MembroDTO membro){
//        return Optional.ofNullable(membro.toMembro())
//                .map(memberService::save)
//                .orElseThrow(() -> new IllegalArgumentException("Algum probs"));

        memberService.save(membro.toMembro());
        return new ResponseEntity("successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = RESOURCE_REMOVE + "/{id}")
    public ResponseEntity<?> removerMembro(@PathVariable Long id){
//        memberService.delete(id);
        return new ResponseEntity("successfully", HttpStatus.OK);
    }

}
