package org.project.web.test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.persistence.PersistenceConfiguration;
import org.project.persistence.domain.EventEntity;
import org.project.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WebConfiguration.class, PersistenceConfiguration.class})
@WebAppConfiguration
public class EventControllerTest extends Assert {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private ObjectMapper mapper;
    private EventEntity eventEntity;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        eventEntity = new EventEntity();
        eventEntity.setTitle("title");
        eventEntity.setDescription("description");
    }

    @Test
    public void testCreateEvent() throws Exception {
        String content = mapper.writer().writeValueAsString(eventEntity);
        mvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }

    @Test
    public void testNotFoundEvent() throws Exception {
        mvc.perform(get("/events/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
