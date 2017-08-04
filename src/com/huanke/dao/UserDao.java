package com.huanke.dao;

import java.sql.SQLException;
import java.util.List;

import com.huanke.model.User;

public interface UserDao {

	public void addUser(User user);

	public void deletUser(User user);

	public List<User> getUserByUserName(String userName);

	public boolean isExist(User user) throws SQLException;
}
