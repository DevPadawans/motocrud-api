package com.devpadawans.motocrudapi.commons.clients;

import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface HTTPClientService {

    <T> Mono<T> sendGetRequest(final String uri, final MultiValueMap<String, String> parameters, Class<T> clazz);

}
