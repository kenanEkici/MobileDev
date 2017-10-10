package com.example.kenan.calorify.dal.repos;

import com.example.kenan.calorify.dl.models.User;

import java.util.List;

/**
 * Created by Kenan on 7/10/2017.
 */

public class UserRepository {
    public void addUser(User user){
        user.save();
    }

    public User getUserById(long id){
        return User.findById(User.class, id);
    }

    public User getActiveUser(){
        for (User u: getAllUsers()) {
            if (u.isActive()){
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return User.listAll(User.class);
    }
}
