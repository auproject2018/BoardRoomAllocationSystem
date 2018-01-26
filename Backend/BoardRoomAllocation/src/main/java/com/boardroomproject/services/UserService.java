package com.boardroomproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.UserDao;
import com.boardroomproject.model.User;

@Service 
public class UserService implements IUserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);

	}

	@Override
	public void deleteUserById(int userId) {
		userDao.deleteUserById(userId);
	}

	@Override
	public List<User> getUserByLocation(int lId) {
		return userDao.getUserByLocation(lId);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public void changeUserType(int userId) {
		userDao.changeUserType(userId);
	}

	@Override
	public User validateUser(User user) {
		return userDao.validateUser(user);
	}
	
	
}