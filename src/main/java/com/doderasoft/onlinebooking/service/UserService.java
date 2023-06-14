package com.doderasoft.onlinebooking.service;

import com.doderasoft.onlinebooking.dao.DBContext;
import com.doderasoft.onlinebooking.model.User;
import com.doderasoft.onlinebooking.response.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private DBContext dbContext;

    @Autowired
    public UserService(@Qualifier("localContext") DBContext dbContext) {
        this.dbContext = dbContext;
    }


    public int addUser(User user) {
        return dbContext.userDao.create(user);
    }

    public List<User> selectAllUsers() {
        return dbContext.userDao.readAll();
    }

    public Optional<User> selectUserById(UUID id) {
        return dbContext.userDao.read(id);
    }

    public int deleteUserById(UUID id) {
        return dbContext.userDao.delete(id);
    }

    public int updateUserById(UUID id, User user) {
        return dbContext.userDao.update(id, user);
    }

    // Login
    public int loginToAccount(HashMap<String, String> credentials) {
        return dbContext.userDao.loginToAccount(credentials);
    }


    // --- BALANCE ---
    public int setBalance(UUID id, float balance) {
        return dbContext.userDao.setBalance(id, balance);
    }

    public int addMoneyToBalance(UUID id, float amount) {
        float newBalance = selectUserById(id).get().getBalance() + amount;
        return dbContext.userDao.setBalance(id, newBalance);
    }

    public int deductMoneyFromBalance(UUID id, float amount) {
        float newBalance = selectUserById(id).get().getBalance() - amount;
        return dbContext.userDao.setBalance(id, newBalance);
    }


    // Buy tickets
    public RequestResponse buyTickets(UUID userId, UUID eventId, int ticketsAmount) {
        return dbContext.userDao.buyTicket(userId, eventId, ticketsAmount);
    }

}
