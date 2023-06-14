package com.doderasoft.onlinebooking.dao;

import com.doderasoft.onlinebooking.model.Event;
import com.doderasoft.onlinebooking.model.User;
import com.doderasoft.onlinebooking.response.RequestResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements CrudOperations<User> {

    private DBContext dbContext;


    public UserDao(DBContext dbContext) {
        this.dbContext = dbContext;
    }


    @Override
    public int create(User entity) {
        UUID newUUID = UUID.randomUUID();
        dbContext.users.add(new User(newUUID, entity.getUsername(), entity.getPassword(), entity.getDob(), entity.getBalance()));

        return 1;
    }

    @Override
    public Optional<User> read(UUID id) {
        return dbContext.users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> readAll() {
        return dbContext.users;
    }

    @Override
    public int update(UUID id, User entity) {
        return read(id).map(u -> {
            int indexToUpdate = dbContext.users.indexOf(u);

            if(indexToUpdate >= 0)
            {
                dbContext.users.set(indexToUpdate, new User(id, entity.getUsername(), entity.getPassword(), entity.getDob(), entity.getBalance()));
                return 1;
            }

            return 0;

        }).orElse(0);
    }

    @Override
    public int delete(UUID id) {
        Optional<User> user = read(id);

        if(user.isEmpty())
            return 0;

        dbContext.users.remove(user.get());
        return 1;
    }


    public Optional<User> getUserByUsername(String username) {
        return dbContext.users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }


    // Login
    public int loginToAccount(HashMap<String, String> credentials) {
        return getUserByUsername(credentials.get("username")).map(user -> {
            return credentials.get("password").equals(user.getPassword()) ? 1 : 0;
        }).orElse(0);
    }


    // Balance
    public int setBalance(UUID id, float balance) {
        return read(id).map(user -> {
            user.setBalance(balance);

            return 1;
        }).orElse(0);
    }


    // Buy ticket
    public RequestResponse buyTicket(UUID userUUID, UUID eventUUID, int tickets) {
        return read(userUUID).map(user -> {
            Optional<Event> eventFound = dbContext.eventDao.read(eventUUID);

            if(!eventFound.isEmpty()) {
                Event eventObject = eventFound.get();
                float ticketsTotalPrice = tickets * eventObject.getTicketPrice();
                // Check if user has enough money
                if(!(user.getBalance() >= ticketsTotalPrice))
                    return RequestResponse.throwBadRequestResponse(String.format("User's balance is too low... Details: Balance: %s;" +
                            " Price per ticket: %s; Total Cost: %s",
                            user.getBalance(), eventObject.getTicketPrice(), ticketsTotalPrice));

                if(eventObject.getAvailableTickets() >= tickets) {
                    eventObject.setTicketsSold(eventObject.getTicketsSold() + tickets);
                    eventObject.setAvailableTickets(eventObject.getAvailableTickets() - tickets);

                    user.deductMoneyFromBalance(ticketsTotalPrice);

                    return RequestResponse.build()
                            .setValid(true)
                            .setHost("localhost")
                            .setMessage(String.format("%s tickets have been successfully purchased", tickets))
                            .setType("Valid Request");
                }

                return RequestResponse.throwBadRequestResponse(String.format("There were not %s tickets available for this event", tickets));
            }

            return RequestResponse.throwBadRequestResponse(String.format("Unable to find any event having the provided UUID: %s", eventUUID));
        }).orElse(RequestResponse.throwBadRequestResponse(String.format("Unable to find any user having the provided UUID: %s", userUUID)));
    }
}
