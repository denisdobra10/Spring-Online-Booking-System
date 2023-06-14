package com.doderasoft.onlinebooking.service;

import com.doderasoft.onlinebooking.dao.DBContext;
import com.doderasoft.onlinebooking.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    private DBContext dbContext;

    @Autowired
    public EventService(@Qualifier("localContext") DBContext dbContext) {
        this.dbContext = dbContext;
    }




    public int addEvent(Event event) {
        return dbContext.eventDao.create(event);
    }

    public Optional<Event> selectEventById(UUID id) {
        return dbContext.eventDao.read(id);
    }

    public List<Event> getAllEvents() {
        return dbContext.eventDao.readAll();
    }

    public int updateEventById(UUID id, Event event) {
        return dbContext.eventDao.update(id, event);
    }

    public int deleteEventById(UUID id) {
        return dbContext.eventDao.delete(id);
    }
}
