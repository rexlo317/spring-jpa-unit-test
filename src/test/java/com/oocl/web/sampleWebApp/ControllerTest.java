package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_ok () throws Exception {
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setName("Tom");
        singleEntity.setId(1L);
        singleEntityRepository.save(singleEntity);
        singleEntityRepository.flush();
        final MvcResult mvcResult = mockMvc.perform(get("/name")).andReturn();
        final String json = mvcResult.getResponse().getContentAsString();
        final ObjectMapper objectMapper = new ObjectMapper();
        final MessageResponse messageResponse = objectMapper.readValue(json, MessageResponse.class);

        assertEquals("Tom", messageResponse.getMessage());
    }

    @Test
    public void should_return_Hello () throws Exception {
        SingleEntity singleEntity = new SingleEntity();
        singleEntity.setId(1L);
        singleEntity.setName("Tom");
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(singleEntity);
        this.mockMvc.perform(post("/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andDo(print())
                .andExpect(status().isCreated());
        final MvcResult mvcResult = mockMvc.perform(post("/name")).andReturn();
        final String json = mvcResult.getResponse().getContentAsString();
        final ObjectMapper objectMapper = new ObjectMapper();
        final MessageResponse messageResponse = objectMapper.readValue(json, MessageResponse.class);

        assertEquals("Hello: " + singleEntity.getName(), messageResponse.getMessage());

    }
}
