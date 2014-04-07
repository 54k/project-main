package org.project.persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.persistence.domain.EventEntity;
import org.project.persistence.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class PersistenceTest extends Assert {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testEventRepositoryCrudOperations() throws Exception {
        EventEntity notPersistedEntity = new EventEntity("Test title", "Test description", Collections.<String>emptySet(), true);

        EventEntity persistedEntity = eventRepository.saveAndFlush(notPersistedEntity);

        assertFalse(persistedEntity.isNew());
        assertNotNull(persistedEntity.getCreated());
        assertEquals(persistedEntity.getTitle(), notPersistedEntity.getTitle());
        assertEquals(persistedEntity.getDescription(), notPersistedEntity.getDescription());
        assertEquals(persistedEntity.getTags(), notPersistedEntity.getTags());
    }
}
