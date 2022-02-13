package com.devpadawans.motocrudapi.unit;

import com.devpadawans.motocrudapi.commons.utils.PathUtils;
import com.devpadawans.motocrudapi.dto.MembroDTO;
import com.devpadawans.motocrudapi.model.Membro;
import com.devpadawans.motocrudapi.repository.MembroRepository;
import com.devpadawans.motocrudapi.service.MembroService;
import com.devpadawans.motocrudapi.service.implmentation.MembroServiceImpl;
import com.devpadawans.motocrudapi.web.MembroController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@AutoConfigureMockMvc
class MembroControllerUnitTests {

    public static final long DOIS = 2l;
    public static final int STATUS_200 = 200;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MembroService membroService ;

    @Autowired
    private MembroController membroController;

    @Test
    public void deveriaRetornarUmaListaDeMembros() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(membroService.findAll()).thenReturn(Arrays.asList(new Membro(), new Membro()));

        MultiValueMap<String, String> paramsNull = new LinkedMultiValueMap<>();
        ResponseEntity<List<Membro>> responseEntity = membroController.searchMembros(paramsNull);

        List<Membro> body = responseEntity.getBody();
        assertEquals("Deveria retornar Status 200 OK", responseEntity.getStatusCodeValue(), STATUS_200);
        assertEquals("O tamanho da lista deveria ser 2", body.size(), DOIS);
    }

}
