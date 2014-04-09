package org.project.web.test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.persistence.PersistenceConfiguration;
import org.project.persistence.domain.EventEntity;
import org.project.persistence.service.EventRepositoryService;
import org.project.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WebConfiguration.class, PersistenceConfiguration.class})
@WebAppConfiguration
public class EventRestControllerTest extends Assert {
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private ObjectMapper mapper;
    private EventEntity eventEntity;
    private Long idForSearch;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        eventEntity = new EventEntity();
        eventEntity.setTitle("title");
        eventEntity.setDescription("description");
    }

    @Before
    public void fillData() {
        EventEntity entity = new EventEntity();
        entity.setTitle("title");
        entity.setDescription("description");
        entity = eventRepositoryService.getEventRepository().save(entity);
        idForSearch = entity.getId();
    }

    @After
    public void dropData() {
        eventRepositoryService.getEventRepository().deleteAll();
    }

    @Test
    public void testCreateEvent() throws Exception {
        mvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(mapper.writer().writeValueAsString(eventEntity))).andExpect(status().isCreated());
    }

    @Test
    public void testFindEventById() throws Exception {
        mvc.perform(get("/events/{id}", idForSearch)).andExpect(status().isOk());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        mvc.perform(delete("/events/{id}", idForSearch)).andExpect(status().isOk());
    }
}
