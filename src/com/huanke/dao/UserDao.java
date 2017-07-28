package com.huanke.dao;

import java.sql.SQLException;
import java.util.List;

import com.huanke.mode.User;

public interface UserDao {

	public void addUser(User user);

	public void deletUser(User user);

	public List<User> getUserByUsername(String message);

	public boolean isExist(User user) throws SQLException;
}
