package com.devpadawans.motocrudapi.web;

import com.devpadawans.motocrudapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

import static com.devpadawans.motocrudapi.commons.utils.PathUtils.RESOURCE_LIST;
import static com.devpadawans.motocrudapi.commons.utils.PathUtils.RESOURCE_MEMBERS;

@Slf4j
@RestController
@RequestMapping(RESOURCE_MEMBERS)
@RequiredArgsConstructor
public class MembroController implements Serializable {

    private final static long serialVersionUID = 1L;

    @Autowired
    private final MembroService memberService;

    @GetMapping(path = RESOURCE_LIST)
    public ResponseEntity<?> getListMembers(@RequestParam MultiValueMap<String, String> params){
        return ResponseEntity.ok(this.memberService.findAll());
    }
}
