package com.users.dao;

import java.util.List;

public interface UserDao {
    public int insert(User user);

    public int update(User user);

    public int delete(int id);

    public User findUser(int id);

    public List<User> findAllUsers();
}
