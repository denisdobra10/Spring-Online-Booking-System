package com.doderasoft.onlinebooking.dao;

import com.doderasoft.onlinebooking.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventDao implements CrudOperations<Event> {

    private DBContext dbContext;

    public EventDao(DBContext dbContext) {
        this.dbContext = dbContext;
    }


    @Override
    public int create(Event entity) {
        UUID newId = UUID.randomUUID();
        dbContext.events.add(new Event(newId, entity.getTitle(), entity.getArtist(), entity.getLocation(),
                entity.getAvailableTickets(), entity.getTicketsSold(), entity.getTicketPrice(), entity.getDate()));
        return 1;
    }

    @Override
    public Optional<Event> read(UUID id) {
        return dbContext.events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Event> readAll() {
        return dbContext.events;
    }

    @Override
    public int update(UUID id, Event entity) {
        return read(id).map(e -> {
           int indexToUpdate = dbContext.events.indexOf(e);

           if(indexToUpdate >= 0) {
               dbContext.events.set(indexToUpdate, new Event(id, entity.getTitle(), entity.getArtist(), entity.getLocation(),
                       entity.getAvailableTickets(), entity.getTicketsSold(), entity.getTicketPrice(), entity.getDate()));

               return 1;
           }

           return 0;
        }).orElse(0);

    }

    @Override
    public int delete(UUID id) {
        Optional<Event> eventFound = read(id);

        if(!eventFound.isEmpty()) {
            dbContext.events.remove(eventFound.get());
            return 1;
        }

        return 0;
    }
}
