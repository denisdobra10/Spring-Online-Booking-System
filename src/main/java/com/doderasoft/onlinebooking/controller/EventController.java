package com.doderasoft.onlinebooking.controller;

import com.doderasoft.onlinebooking.model.Event;
import com.doderasoft.onlinebooking.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }



    @PostMapping
    public int create(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping(path = "/all")
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

    @GetMapping(path = "{id}")
    public Event getById(@PathVariable UUID id) {
        return eventService.selectEventById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public int update(@PathVariable UUID id, @RequestBody Event event) {
        return eventService.updateEventById(id, event);
    }

    @DeleteMapping(path = "{id}")
    public int delete(@PathVariable UUID id) {
        return eventService.deleteEventById(id);
    }


}
