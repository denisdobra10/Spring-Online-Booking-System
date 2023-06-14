package com.doderasoft.onlinebooking.dao;

import com.doderasoft.onlinebooking.model.Event;
import com.doderasoft.onlinebooking.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("localContext")
public class DBContext {

    public UserDao userDao = new UserDao(this);
    public EventDao eventDao = new EventDao(this);


    List<User> users = new ArrayList<>(List.of(
            new User(UUID.randomUUID(), "Andrew Tate", "therealword", LocalDate.now(), 1000),
            new User(UUID.randomUUID(), "Tristan Tate", "trw", LocalDate.now(), 1000),
            new User(UUID.randomUUID(), "Cowboy Yee", "itisunknown", LocalDate.now(), 1000)
    ));


    List<Event> events = new ArrayList<>();

}
