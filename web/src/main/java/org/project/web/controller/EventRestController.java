package org.project.web.controller;

import org.project.persistence.domain.EventEntity;
import org.project.persistence.service.EventRepositoryService;
import org.project.web.exeption.BadRequestException;
import org.project.web.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventRestController {

    @Autowired
    private EventRepositoryService eventRepositoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventEntity> findPublicEvents() {
        return eventRepositoryService.getEventRepository().findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventEntity findPublicEventById(@PathVariable Long id) {
        EventEntity eventEntity = eventRepositoryService.getEventRepository().findOne(id);
        if (eventEntity == null) {
            throw new NotFoundException();
        }
        return eventEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody EventEntity eventEntity) {
        if (!eventEntity.isNew()) {
            throw new BadRequestException();
        }
        eventRepositoryService.getEventRepository().save(eventEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@Valid @RequestBody EventEntity eventEntity) {
        if (eventEntity.isNew() || eventRepositoryService.getEventRepository().findOne(eventEntity.getId()) == null) {
            throw new BadRequestException();
        }
        eventRepositoryService.getEventRepository().save(eventEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        if (eventRepositoryService.getEventRepository().findOne(id) == null) {
            throw new NotFoundException();
        }
        eventRepositoryService.getEventRepository().delete(id);
    }
}
