package com.devpadawans.motocrudapi.service;

import com.devpadawans.motocrudapi.commons.clients.HTTPClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BaseService {

    protected final HTTPClientService httpClientService;
    protected final ObjectMapper objectMapper;

//    protected List sendGetRequestReturningList(final String uri, final MultiValueMap<String, String> multiValueMap, Class<?> entityClass) {
//        log.info("Calling the URI {} to return list by map {} ", uri, multiValueMap);
//
//        multiValueMap.addIfAbsent("size", valueOf(MAX_VALUE));
//
//        BaseEmbeddedPersistenceResponseDTO response = this.httpClientService.sendGetRequest(
//                uri,
//                multiValueMap,
//                BaseEmbeddedPersistenceResponseDTO.class
//        ).block();
//
//        if (Objects.isNull(response)) {
//            return List.of();
//        }
//        return getList(response, entityClass);
//    }
//
//    private List getList(BaseEmbeddedPersistenceResponseDTO base, Class<?> entityClass) {
//        if (Objects.isNull(base.getEmbedded()) || CollectionUtils.isEmpty(base.getEmbedded().getList()))
//            return List.of();
//
//        return base.getEmbedded()
//                .getList()
//                .stream()
//                .map(o -> objectMapper.convertValue(o, entityClass))
//                .collect(Collectors.toList());
//    }

}
