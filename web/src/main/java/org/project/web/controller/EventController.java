package org.project.web.controller;

import org.project.persistence.domain.EventEntity;
import org.project.persistence.repository.EventRepository;
import org.project.web.exeprtion.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<EventEntity> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventEntity findById(@PathVariable Long id) {
        EventEntity eventEntity = repository.findOne(id);
        if (eventEntity != null) {
            return eventEntity;
        }

        throw new NotFoundException();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody EventEntity eventEntity) {
        repository.save(eventEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
