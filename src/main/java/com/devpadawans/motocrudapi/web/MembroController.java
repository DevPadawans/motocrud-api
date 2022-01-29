package com.devpadawans.motocrudapi.web;

import com.devpadawans.motocrudapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

import static com.devpadawans.motocrudapi.commons.utils.PathUtils.RESOURCE_LIST;
import static com.devpadawans.motocrudapi.commons.utils.PathUtils.RESOURCE_MEMBROS;

@Slf4j
@RestController
@RequestMapping(RESOURCE_MEMBROS)
@RequiredArgsConstructor
public class MembroController implements Serializable {

    private final static long serialVersionUID = 1L;

    private final MembroService memberService;

    @GetMapping(path = RESOURCE_LIST)
    public ResponseEntity<?> getListMembers(@RequestParam MultiValueMap<String, String> params){
        this.memberService.findAll();
        return ResponseEntity.noContent().build();
    }
}
