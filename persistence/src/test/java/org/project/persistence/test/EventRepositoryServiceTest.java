package org.project.persistence.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.persistence.PersistenceConfiguration;
import org.project.persistence.domain.EventEntity;
import org.project.persistence.service.EventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class EventRepositoryServiceTest extends Assert {

    @Autowired
    private EventRepositoryService eventRepositoryService;
    private EventEntity eventEntity;

    @Before
    public void populateEventEntity() {
        eventEntity = new EventEntity();
        eventEntity.setTitle("title");
        eventEntity.setDescription("description");
        eventEntity.addTag("tag");
    }

    @After
    public void dropTestData() {
        eventRepositoryService.getEventRepository().deleteAll();
    }

    @Test
    public void testEventCrudOperations() throws Exception {
        EventEntity persistedEntity = eventRepositoryService.getEventRepository().save(eventEntity);

        assertFalse(persistedEntity.isNew());
        assertNotNull(persistedEntity.getCreated());
        assertEquals(persistedEntity.getTitle(), eventEntity.getTitle());
        assertEquals(persistedEntity.getDescription(), eventEntity.getDescription());
        assertEquals(persistedEntity.getTags(), eventEntity.getTags());

        assertEquals(eventRepositoryService.getEventRepository().findOne(persistedEntity.getId()), persistedEntity);

        persistedEntity.addTag("tag2");
        persistedEntity = eventRepositoryService.getEventRepository().save(persistedEntity);
        assertTrue(persistedEntity.getTags().contains("tag2"));

        eventRepositoryService.getEventRepository().delete(persistedEntity);
        assertNull(eventRepositoryService.getEventRepository().findOne(persistedEntity.getId()));
    }
}